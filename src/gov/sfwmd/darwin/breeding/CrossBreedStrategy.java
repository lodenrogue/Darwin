package gov.sfwmd.darwin.breeding;

import gov.sfwmd.darwin.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrossBreedStrategy<T> implements BreedingStrategy<List<T>> {
	private Random rand;

	public CrossBreedStrategy() {
		rand = new Random();
	}

	@Override
	public Individual<List<T>> breed(Individual<List<T>> parentA, Individual<List<T>> parentB) {
		int cutOff = rand.nextInt(parentA.getChromosomes().size());

		List<T> parentAChromos = parentA.getChromosomes();
		List<T> parentBChromos = parentB.getChromosomes();
		List<T> newBornChromos = new ArrayList<T>();

		newBornChromos.addAll(parentAChromos.subList(0, cutOff + 1));
		newBornChromos.addAll(parentBChromos.subList(cutOff + 1, parentBChromos.size()));

		Individual<List<T>> newborn = new Individual<List<T>>();
		newborn.setChromosomes(newBornChromos);

		return newborn;
	}
}
