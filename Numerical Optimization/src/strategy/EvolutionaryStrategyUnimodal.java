package strategy;

import java.util.ArrayList;
import java.util.List;

import org.vu.contest.ContestEvaluation;

import evolutionary.Mutation.MutationType;
import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Selection;

public class EvolutionaryStrategyUnimodal extends EvolutionaryStrategy {

	public EvolutionaryStrategyUnimodal(int populationSize, int evaluationsLimit, MutationType mutationType,
			ContestEvaluation evaluationType) {
		super(populationSize, evaluationsLimit, mutationType, evaluationType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evolve(int numOfCrIndv, int numOfMutIndv) {

		int evals = populationSize;
		while (evals + numOfMutIndv < evaluationsLimit) {
			List<Individual> newPop = new ArrayList<Individual>();

			// Select individuals for mutation
			List<Individual> mutated = Selection.uniform(population.getPopulation(), numOfMutIndv);

			// Mutate offspring
			for (Individual indv : mutated) {
				newPop.add(Mutation.uncorrelatedMutation(indv));
			}

			newPop.addAll(population.getPopulation());

			List<Individual> keepIndv = Selection.plusStrategy(newPop, populationSize);

			// remove all parents from population
			population.removeFromPopulation(population.getPopulation());

			// replace parents with children
			population.setPopulation(keepIndv);

			evals = evals + numOfMutIndv;

		}

	}

}
