package gov.sfwmd.darwin.examples;

import gov.sfwmd.darwin.GeneticAlgorithm;
import gov.sfwmd.darwin.GoalFitness;
import gov.sfwmd.darwin.Individual;
import gov.sfwmd.darwin.Results;
import gov.sfwmd.darwin.Toolbox;
import gov.sfwmd.darwin.breeding.BreedingStrategy;
import gov.sfwmd.darwin.breeding.CrossBreedStrategy;

import java.util.List;

public class InheritanceExample {

	/*
	 * Given a number of inheritors and an array of values for items, how do we
	 * evenly split up these items so that the difference in value per inheritor
	 * is minimized?
	 */

	public static void main(String[] args) {
		int inheritors = 3;
		float[] values = { 100.76f, 22.12f, 90, 150, 70, 100, 22.9f, 8, 150, 220, 75, 69, 104.46f };

		Toolbox<List<Integer>> toolbox;
		toolbox = new InheritanceExampleToolbox(GoalFitness.MIN, values, inheritors);

		int popSize = 100;
		float mutProb = .60f;
		int generations = 100;

		BreedingStrategy<List<Integer>> breedingStrategy;
		breedingStrategy = new CrossBreedStrategy<Integer>();

		GeneticAlgorithm<List<Integer>> geneticAlgorithm;
		geneticAlgorithm = new GeneticAlgorithm<List<Integer>>(toolbox, popSize, mutProb, breedingStrategy);

		Results<List<Integer>> results = geneticAlgorithm.evolve(generations);
		printResults(results);
	}

	private static void printResults(Results<List<Integer>> results) {
		System.out.println("Generations: " + results.getGenerations());
		
		for (Individual<List<Integer>> indv : results.getPopulation()) {
			System.out.println(indv.getChromosomes() + " : " + indv.getFitness());
		}
	}

}
