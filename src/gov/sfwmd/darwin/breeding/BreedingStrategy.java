package gov.sfwmd.darwin.breeding;

import gov.sfwmd.darwin.Individual;

public interface BreedingStrategy<T> {
	
	public Individual<T> breed(Individual<T> parentA, Individual<T> parentB);
	

}
