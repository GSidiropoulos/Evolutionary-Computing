package strategy;

import java.util.ArrayList;
import java.util.List;

import org.vu.contest.ContestEvaluation;

import evolutionary.Mutation.MutationType;
import evolutionary.Population;
import evolutionary.Crossover;
import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Selection;

public class EvolutionaryStrategyUnimodal extends EvolutionaryStrategy {



	public EvolutionaryStrategyUnimodal(int numOfPopulations, int populationSize, int evaluationsLimit,
			MutationType mutationType, ContestEvaluation evaluationType) {
		super(numOfPopulations, populationSize, evaluationsLimit, mutationType, evaluationType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evolve(int numOfCrIndv, int numOfMutIndv, int type) {

		switch (type) {
		case 1:
			evolve1(numOfCrIndv, numOfMutIndv);
			break;
		case 2:
			evolve2(numOfCrIndv, numOfMutIndv);
			break;
		}

	}

	public void evolve1(int numOfCrIndv, int numOfMutIndv) {

		Population population = populations.get(0);
		
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

	public void evolve2(int numOfCrIndv, int numOfMutIndv) {
		Population population = populations.get(0);

		int evals = populationSize;

		while (evals + 3*numOfMutIndv < evaluationsLimit) {

			List<Individual> mutated = new ArrayList<Individual>();
			List<Individual> crossovered = new ArrayList<Individual>();

			// Select individuals for crossover
			List<List<Individual>> cross = Selection.tournament(population.getPopulation(), 3, numOfCrIndv);

			// apply crossover
			for (List<Individual> pair : cross) {
				
				crossovered.addAll(Crossover.uniform(pair));
			}

			// apply mutation
			for (int i = 0; i < numOfMutIndv; i++) {
				mutated.add(Mutation.uncorrelatedMutation(crossovered.get(i)));
			}

			//mutated.addAll(population.getPopulation());

			// comma strategy
			List<Individual> keepIndv = Selection.plusStrategy(mutated, populationSize);

			// remove all parents from population
			population.removeFromPopulation(population.getPopulation());

			// replace parents with children
			population.setPopulation(keepIndv);

			evals = evals + 3* numOfMutIndv;
		}
	}

}
