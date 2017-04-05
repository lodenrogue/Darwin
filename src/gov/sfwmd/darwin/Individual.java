package gov.sfwmd.darwin;

public class Individual<T> implements Comparable<Individual<T>> {
	private Float fitness;
	private T chromosomes;

	public Float getFitness() {
		return fitness;
	}

	public void setFitness(Float fitness) {
		this.fitness = fitness;
	}

	public T getChromosomes() {
		return chromosomes;
	}

	public void setChromosomes(T chromosomes) {
		this.chromosomes = chromosomes;
	}

	@Override
	public int compareTo(Individual<T> compareIndv) {
		return Float.compare(getFitness(), compareIndv.getFitness());
	}

}
