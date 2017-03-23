package gov.sfwmd.darwin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestToolbox implements Toolbox<List<Integer>> {
	private Random rand = new Random();
	private GoalFitness goalFitness;
	private boolean generateSame;

	public TestToolbox(GoalFitness goalFitness, boolean generateSame) {
		this.goalFitness = goalFitness;
		this.generateSame = generateSame;
	}

	@Override
	public Individual<List<Integer>> generate() {
		Individual<List<Integer>> indv = new Individual<List<Integer>>();

		if (generateSame) {
			indv.setChromosomes(createSameChromosomes(4));
		}
		else {
			indv.setChromosomes(createRandomChromosomes(4));
		}
		return indv;
	}

	@Override
	public float getFitness(Individual<List<Integer>> indv) {
		int fitness = 0;
		for (int i : indv.getChromosomes()) {
			fitness += i;
		}
		return fitness;
	}

	@Override
	public Individual<List<Integer>> mutate(Individual<List<Integer>> indv) {
		int randChromo = rand.nextInt(indv.getChromosomes().size());
		indv.getChromosomes().set(randChromo, rand.nextInt(10));
		return indv;
	}

	@Override
	public void setGoalFitness(GoalFitness goalFittness) {
		this.goalFitness = goalFittness;
	}

	@Override
	public GoalFitness getGoalFitness() {
		return goalFitness;
	}

	private List<Integer> createRandomChromosomes(int size) {
		List<Integer> chromosomes = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			chromosomes.add(rand.nextInt(10));
		}
		return chromosomes;
	}

	private List<Integer> createSameChromosomes(int size) {
		List<Integer> chromosomes = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			chromosomes.add(1);
		}
		return chromosomes;
	}

}
