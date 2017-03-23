package gov.sfwmd.darwin;

public interface Toolbox<T> {

	public Individual<T> generate();

	public float getFitness(Individual<T> indv);

	public Individual<T> mutate(Individual<T> indv);
	
	public void setGoalFitness(GoalFitness goalFittness);

	public GoalFitness getGoalFitness();

}
