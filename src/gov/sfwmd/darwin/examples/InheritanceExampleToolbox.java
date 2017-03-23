package gov.sfwmd.darwin.examples;

import gov.sfwmd.darwin.GoalFitness;
import gov.sfwmd.darwin.Individual;
import gov.sfwmd.darwin.Toolbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InheritanceExampleToolbox implements Toolbox<List<Integer>> {
	private Random rand = new Random();
	private GoalFitness goalFitness;
	private float[] values;
	private int inheritors;

	public InheritanceExampleToolbox(GoalFitness goalFitness, float[] values, int inheritors) {
		this.goalFitness = goalFitness;
		this.values = values;
		this.inheritors = inheritors;
	}

	@Override
	public Individual<List<Integer>> generate() {
		List<Integer> chromosomes = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i++) {
			chromosomes.add(rand.nextInt(inheritors));
		}

		Individual<List<Integer>> indv = new Individual<List<Integer>>();
		indv.setChromosomes(chromosomes);
		return indv;
	}

	@Override
	public float getFitness(Individual<List<Integer>> indv) {
		float[] sums = new float[inheritors];
		List<Integer> chromosomes = indv.getChromosomes();

		for (int i = 0; i < chromosomes.size(); i++) {
			sums[chromosomes.get(i)] += values[i];
		}

		float total = 0;
		for (int i = 0; i < sums.length; i++) {
			total += sums[i];
		}

		float average = total / sums.length;
		float fitness = 0;
		for (int i = 0; i < sums.length; i++) {
			fitness += Math.abs(sums[i] - average);
		}

		return fitness;
	}

	@Override
	public Individual<List<Integer>> mutate(Individual<List<Integer>> indv) {
		int randChromo = rand.nextInt(indv.getChromosomes().size());
		indv.getChromosomes().set(randChromo, rand.nextInt(inheritors));
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
