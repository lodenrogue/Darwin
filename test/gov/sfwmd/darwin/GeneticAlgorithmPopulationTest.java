package gov.sfwmd.darwin;

import static org.junit.Assert.*;
import gov.sfwmd.darwin.breeding.BreedingStrategy;
import gov.sfwmd.darwin.breeding.CrossBreedStrategy;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GeneticAlgorithmPopulationTest {
	private Toolbox<List<Integer>> toolbox;
	private int popSize;
	private float mutProb;
	private BreedingStrategy<List<Integer>> breedingStrategy;
	
	private GeneticAlgorithm<List<Integer>> geneticAlgorithm;
	private List<Individual<List<Integer>>> population;

	@Before
	public void setUp() throws Exception {
		toolbox = new TestToolbox(GoalFitness.MAX, true);
		popSize = 10;
		mutProb = .10f;
		breedingStrategy = new CrossBreedStrategy<Integer>();
		
		geneticAlgorithm = new GeneticAlgorithm<List<Integer>>(toolbox, popSize, mutProb, breedingStrategy);
		population = geneticAlgorithm.generatePopulation(); 
	}

	/**
	 * Should return a population of type {@link List}
	 */
	@Test
	public void testReturnList() {
		assertTrue(population instanceof List);
	}
	
	/**
	 * Should return a population of the same size as popSize
	 */
	@Test
	public void testPopulationSize(){
		assertEquals(popSize, population.size());
	}
	
	@Test
	public void testSameIndividualChromosomes() {
		for(Individual<List<Integer>> indv : population) {
			List<Integer> chromos = indv.getChromosomes();
			
			for(int i : chromos) {
				assertEquals(1, i);
			}
		}
	}

}
