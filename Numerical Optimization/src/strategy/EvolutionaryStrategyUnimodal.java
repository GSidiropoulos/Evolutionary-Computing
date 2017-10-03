package strategy;

import java.util.ArrayList;
import java.util.List;

import org.vu.contest.ContestEvaluation;

import evolutionary.Mutation.MutationType;
import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Population;
import evolutionary.Selection;

public class EvolutionaryStrategyUnimodal extends EvolutionaryStrategy {

	public EvolutionaryStrategyUnimodal(int populationSize, int evaluationsLimit, MutationType mutationType,
			ContestEvaluation evaluationType) {
		super(populationSize, evaluationsLimit, mutationType, evaluationType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evolve(int lambda) {
		
		int evals = 0;
		while (evals < evaluationsLimit) {

			// Select individuals for mutation
			List<Integer> mutatedIds = Selection.uniform(population.getPopSize(), lambda);

			// Apply crossover / mutation operators
			List<Individual> newPop = new ArrayList<Individual>();

			for (int i : mutatedIds) {
				newPop.add(Mutation.uncorrelatedMutation(population.getPopulation().get(i)));
			}

			newPop.addAll(population.getPopulation());
			System.out.println("Population Size: " + newPop.size());

			List<Individual> keepIndv = Selection.plusStrategy(newPop, lambda);

			// remove all parents from population
			population.removeFromPopulation(population.getPopulation());

			// replace parents with children
			population.setPopulation(keepIndv);

			for (Individual i : population.getPopulation()) {
				System.out.println(i.toString());
			}

			System.out.println("----------------------------------------");

			evals = evals + lambda;

		}

	}

}
