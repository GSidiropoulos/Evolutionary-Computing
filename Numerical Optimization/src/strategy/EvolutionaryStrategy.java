package strategy;

import org.vu.contest.ContestEvaluation;

import evolutionary.Mutation;
import evolutionary.Population;
import evolutionary.Mutation.MutationType;

public abstract class EvolutionaryStrategy {

	protected int populationSize;
	protected int evaluationsLimit;
	protected MutationType mutationType;
	protected ContestEvaluation evaluationType;
	protected Population population;

	public EvolutionaryStrategy(int populationSize, int evaluationsLimit, MutationType mutationType,
			ContestEvaluation evaluationType) {
		super();
		this.populationSize = populationSize;
		this.evaluationsLimit = evaluationsLimit;
		this.mutationType = mutationType;
		this.evaluationType = evaluationType;

		// initialize population
		population = new Population(populationSize, evaluationType, mutationType);
		
		// initialize mutation
		Mutation.init(populationSize, mutationType);
	}

	public abstract void evolve(int lambda);

}
