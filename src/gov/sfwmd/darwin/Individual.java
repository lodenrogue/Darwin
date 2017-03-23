package gov.sfwmd.darwin;

public class Individual<T> implements Comparable<Individual<T>> {
	private float fitness;
	private T chromosomes;

	public float getFitness() {
		return fitness;
	}

	public void setFitness(float fitness) {
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
