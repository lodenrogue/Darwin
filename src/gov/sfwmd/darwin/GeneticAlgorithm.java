package gov.sfwmd.darwin;

import gov.sfwmd.darwin.breeding.BreedingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm<T> {
	private Toolbox<T> toolbox;
	private int popSize;
	private float mutProb;
	private BreedingStrategy<T> breedingStrategy;
	private Random rand;
	private boolean verbose;

	public GeneticAlgorithm(Toolbox<T> toolbox, int popSize, float mutProb, BreedingStrategy<T> breedingStrategy) throws IllegalArgumentException {
		if (toolbox == null) {
			throw new IllegalArgumentException("Toolbox cannot be null");
		}

		if (popSize <= 2) {
			String message = "Population size must be greater than 2. Current size: " + popSize;
			throw new IllegalArgumentException(message);
		}

		if (breedingStrategy == null) {
			throw new IllegalArgumentException("Breeding algorithm cannot be null");
		}

		this.toolbox = toolbox;
		this.popSize = popSize;
		this.mutProb = mutProb;
		this.breedingStrategy = breedingStrategy;

		this.verbose = false;
		rand = new Random();
	}

	public Results<T> evolve(int generations) {
		List<Individual<T>> population = generatePopulation();

		for (int i = 0; i < generations; i++) {
			population = getFitness(population);
			population = sortByFitness(population);

			if (isVerbose()) printUpdate(population, i);

			population = breed(population);
		}

		population = getFitness(population);
		population = sortByFitness(population);

		if (isVerbose()) printUpdate(population, generations);

		Results<T> results = getResults(population, generations);
		return results;
	}

	public List<Individual<T>> generatePopulation() {
		List<Individual<T>> population = new ArrayList<Individual<T>>();
		for (int i = 0; i < popSize; i++) {
			population.add(toolbox.generate());
		}
		return population;
	}

	public List<Individual<T>> getFitness(List<Individual<T>> population) {
		for (Individual<T> indv : population) {
			indv.setFitness(toolbox.getFitness(indv));
		}
		return population;
	}

	public List<Individual<T>> sortByFitness(List<Individual<T>> population) {
		Collections.sort(population);

		if (toolbox.getGoalFitness().equals(GoalFitness.MAX)) {
			Collections.reverse(population);
		}
		return population;
	}

	public List<Individual<T>> breed(List<Individual<T>> population) {

		// Select best individuals and remove bottom half of population
		int breeders = population.size() / 2;
		List<Individual<T>> newPopulation = population.subList(0, breeders);

		while (newPopulation.size() != popSize) {
			int parentAIndex = rand.nextInt(breeders);
			int parentBIndex = parentAIndex;

			while (parentAIndex == parentBIndex) {
				parentBIndex = rand.nextInt(breeders);
			}

			// Get parents
			Individual<T> parentA = population.get(parentAIndex);
			Individual<T> parentB = population.get(parentBIndex);

			// Create newborn
			Individual<T> newborn = breedingStrategy.breed(parentA, parentB);

			// Mutate newborn
			if (rand.nextFloat() <= mutProb) {
				newborn = toolbox.mutate(newborn);
			}
			newPopulation.add(newborn);
		}
		return newPopulation;
	}

	public Results<T> getResults(List<Individual<T>> population, int generations) {
		Results<T> results = new Results<T>();
		results.setGenerations(generations);
		results.setPopulation(population);
		return results;
	}

	private void printUpdate(List<Individual<T>> population, int generation) {
		float fittestScore = population.get(0).getFitness();
		float sum = 0;
		for (int i = 0; i < population.size(); i++) {
			sum += population.get(i).getFitness();
		}

		float average = sum / population.size();

		StringBuilder sb = new StringBuilder();
		sb.append("Generation: " + generation + " ");
		sb.append("Fittest: " + fittestScore + " ");
		sb.append("Average: " + average);

		System.out.println(sb.toString());
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

}
