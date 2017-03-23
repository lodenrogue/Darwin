package gov.sfwmd.darwin.examples;

import gov.sfwmd.darwin.GeneticAlgorithm;
import gov.sfwmd.darwin.GoalFitness;
import gov.sfwmd.darwin.Individual;
import gov.sfwmd.darwin.Results;
import gov.sfwmd.darwin.Toolbox;
import gov.sfwmd.darwin.breeding.BreedingStrategy;
import gov.sfwmd.darwin.breeding.CrossBreedStrategy;

import java.util.List;

public class SimpleExample {

	public static void main(String[] args) {
		Toolbox<List<Integer>> toolbox = new SimpleExampleToolbox(GoalFitness.MAX);
		int popSize = 10;
		float mutProb = .10f;
		int generations = 10;

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
