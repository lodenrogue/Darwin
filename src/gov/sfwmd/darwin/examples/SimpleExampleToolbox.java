package gov.sfwmd.darwin.examples;

import gov.sfwmd.darwin.GoalFitness;
import gov.sfwmd.darwin.Individual;
import gov.sfwmd.darwin.Toolbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleExampleToolbox implements Toolbox<List<Integer>> {
	private Random rand = new Random();
	private GoalFitness goalFitness;

	public SimpleExampleToolbox(GoalFitness goalFitness) {
		this.goalFitness = goalFitness;
	}

	@Override
	public Individual<List<Integer>> generate() {
		List<Integer> chromosomes = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			chromosomes.add(rand.nextInt(2));
		}

		Individual<List<Integer>> indv = new Individual<List<Integer>>();
		indv.setChromosomes(chromosomes);
		return indv;
	}

	@Override
	public float getFitness(Individual<List<Integer>> indv) {
		int fitness = 0;
		for (int chromosome : indv.getChromosomes()) {
			fitness += chromosome;
		}
		return fitness;
	}

	@Override
	public Individual<List<Integer>> mutate(Individual<List<Integer>> indv) {
		int randChromo = rand.nextInt(indv.getChromosomes().size());
		indv.getChromosomes().set(randChromo, rand.nextInt(2));
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

}
