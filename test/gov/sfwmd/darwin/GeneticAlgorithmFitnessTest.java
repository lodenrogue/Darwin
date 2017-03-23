package gov.sfwmd.darwin;

import static org.junit.Assert.assertTrue;
import gov.sfwmd.darwin.breeding.BreedingStrategy;
import gov.sfwmd.darwin.breeding.CrossBreedStrategy;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GeneticAlgorithmFitnessTest {
	private Toolbox<List<Integer>> toolbox;
	private int popSize;
	private float mutProb;
	private BreedingStrategy<List<Integer>> breedingStrategy;

	private GeneticAlgorithm<List<Integer>> geneticAlgorithm;
	private List<Individual<List<Integer>>> population;

	@Before
	public void setUp() throws Exception {
		toolbox = new TestToolbox(GoalFitness.MAX, false);
		popSize = 10;
		mutProb = .10f;
		breedingStrategy = new CrossBreedStrategy<Integer>();

		geneticAlgorithm = new GeneticAlgorithm<List<Integer>>(toolbox, popSize, mutProb, breedingStrategy);
		population = geneticAlgorithm.generatePopulation();
	}

	/**
	 * Should return the correct fitness value for all individuals
	 */
	@Test
	public void testShouldReturnCorrectFitness() {
		toolbox = new TestToolbox(GoalFitness.MAX, true);
		geneticAlgorithm = new GeneticAlgorithm<List<Integer>>(toolbox, popSize, mutProb, breedingStrategy);
		population = geneticAlgorithm.generatePopulation();

		List<Individual<List<Integer>>> evaluated = geneticAlgorithm.getFitness(population);
		for (Individual<List<Integer>> indv : evaluated) {
			assertTrue(indv.getFitness() == 4);
		}
	}

	@Test
	public void testSortInDescendingOrderWhenFitnessGoalIsMax() {
		toolbox.setGoalFitness(GoalFitness.MAX);
		population = geneticAlgorithm.getFitness(population);

		List<Individual<List<Integer>>> sortedPopulation = geneticAlgorithm.sortByFitness(population);

		float previousFitness = 100f;
		for (Individual<List<Integer>> indv : sortedPopulation) {
			float fitness = indv.getFitness();
			assertTrue(fitness <= previousFitness);
			previousFitness = fitness;
		}
	}

	@Test
	public void testSortInAscendingOrderWhenFitnessGoalIsMin() {
		toolbox.setGoalFitness(GoalFitness.MIN);
		population = geneticAlgorithm.getFitness(population);

		List<Individual<List<Integer>>> sortedPopulation = geneticAlgorithm.sortByFitness(population);

		float previousFitness = 0f;
		for (Individual<List<Integer>> indv : sortedPopulation) {
			float fitness = indv.getFitness();
			assertTrue(fitness >= previousFitness);
			previousFitness = fitness;
		}
	}

}
