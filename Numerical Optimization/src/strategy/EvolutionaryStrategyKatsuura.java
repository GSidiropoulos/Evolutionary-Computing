package strategy;

import java.util.ArrayList;
import java.util.List;

import org.vu.contest.ContestEvaluation;

import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Mutation.MutationType;
import evolutionary.Selection;

public class EvolutionaryStrategyKatsuura extends EvolutionaryStrategy {

	public EvolutionaryStrategyKatsuura(int populationSize, int evaluationsLimit, MutationType mutationType,
			ContestEvaluation evaluationType) {
		super(populationSize, evaluationsLimit, mutationType, evaluationType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evolve(int numOfCrIndv, int numOfMutIndv, int type) {
		evolve(numOfCrIndv, numOfMutIndv);

	}

	public void evolve(int numOfCrIndv, int numOfMutIndv) {

		int evals = 0;
		while (evals + numOfMutIndv < evaluationsLimit) {

			if (evals % (200 * populationSize) == 0) {
				population.reInitializePopulation();
			}

			// Select individuals for mutation
			List<Individual> mutated = Selection.uniform(population.getPopulation(), numOfMutIndv);

			// Apply mutation operators
			List<Individual> newPop = new ArrayList<Individual>();

			for (Individual indv : mutated) {
				newPop.add(Mutation.uncorrelatedMutationN(indv));
			}

			newPop.addAll(population.getPopulation());
			// System.out.println("Population Size: " + newPop.size());

			List<Individual> keepIndv = Selection.plusStrategy(newPop, populationSize);

			// remove all parents from population
			population.removeFromPopulation(population.getPopulation());

			// replace parents with children
			population.setPopulation(keepIndv);

			evals = evals + numOfMutIndv;

		}

	}

}
