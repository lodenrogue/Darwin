package gov.sfwmd.darwin;

import java.util.List;

public class Results<T> {
	private int generations;
	private List<Individual<T>> population;

	public int getGenerations() {
		return generations;
	}

	public void setGenerations(int generations) {
		this.generations = generations;
	}

	public List<Individual<T>> getPopulation() {
		return population;
	}

	public void setPopulation(List<Individual<T>> population) {
		this.population = population;
	}

}
