package gov.sfwmd.darwin;

import gov.sfwmd.darwin.breeding.BreedingStrategy;
import gov.sfwmd.darwin.breeding.CrossBreedStrategy;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GeneticAlgorithmCreationTest {
	private Toolbox<List<Integer>> toolbox;
	private int popSize;
	private float mutProb;
	private BreedingStrategy<List<Integer>> breedingStrategy;

	@Before
	public void setUp() throws Exception {
		toolbox = new TestToolbox(GoalFitness.MAX, false);
		popSize = 10;
		mutProb = .10f;
		breedingStrategy = new CrossBreedStrategy<Integer>();
	}

	/**
	 * Should throw an {@link IllegalArgumentException} when toolbox is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullToolbox() {
		new GeneticAlgorithm<List<Integer>>(null, popSize, mutProb, breedingStrategy);
	}

	/**
	 * Should throw an {@link IllegalArgumentException} when population size is
	 * 0
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPopSize0() {
		new GeneticAlgorithm<List<Integer>>(toolbox, 0, mutProb, breedingStrategy);
	}

	/**
	 * Should throw an {@link IllegalArgumentException} when population size is
	 * 1
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPopSize1() {
		new GeneticAlgorithm<List<Integer>>(toolbox, 1, mutProb, breedingStrategy);
	}

	/**
	 * Should throw an {@link IllegalArgumentException} when population size is
	 * 2
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPopSize2() {
		new GeneticAlgorithm<List<Integer>>(toolbox, 2, mutProb, breedingStrategy);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullBreedingAlgorithm() {
		new GeneticAlgorithm<List<Integer>>(toolbox, popSize, mutProb, null);
	}

}
