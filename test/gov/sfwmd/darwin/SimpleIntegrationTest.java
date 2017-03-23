package gov.sfwmd.darwin;

import gov.sfwmd.darwin.breeding.BreedingStrategy;
import gov.sfwmd.darwin.breeding.CrossBreedStrategy;

import java.util.List;

public class SimpleIntegrationTest {

	public static void main(String[] args) {
		Toolbox<List<Integer>> toolbox = new TestToolbox(GoalFitness.MAX, false);
		int popSize = 10;
		float mutProb = .10f;
		BreedingStrategy<List<Integer>> breedingStrategy = new CrossBreedStrategy<Integer>();

		GeneticAlgorithm<List<Integer>> geneticAlgorithm = new GeneticAlgorithm<List<Integer>>(toolbox, popSize, mutProb, breedingStrategy);
		Results<List<Integer>> results = geneticAlgorithm.evolve(78);

		System.out.println(results.getGenerations());
		for (Individual<List<Integer>> indv : results.getPopulation()) {
			System.out.println(indv.getChromosomes() + " : " + indv.getFitness());
		}

	}

}
