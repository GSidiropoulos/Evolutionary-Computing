package strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.vu.contest.ContestEvaluation;

import evolutionary.Crossover;
import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Population;
import evolutionary.Mutation.MutationType;
import evolutionary.Selection;

public class EvolutionaryStrategyKatsuura extends EvolutionaryStrategy {

	public EvolutionaryStrategyKatsuura(int numOfPopulations, int populationSize, int evaluationsLimit,
			MutationType mutationType, ContestEvaluation evaluationType, Random rand) {
		super(numOfPopulations, populationSize, evaluationsLimit, mutationType, evaluationType, rand);
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
		System.out.println("Begin");

		int evals = numOfPopulations * populationSize;
		int generations = 0;
		while (evals + (numOfCrIndv + numOfMutIndv) * numOfPopulations < evaluationsLimit) {

			for (Population population : populations) {

				if ((generations % 50) == 0) {
					migrate(5);

				}

				List<Individual> newIndvs = new ArrayList<>();
				List<Individual> mutatedIndvs = new ArrayList<>();

				// crossover
				for (int i = 0; i < numOfCrIndv; i++) {
					// Select individuals for crossover
					List<Individual> crossover = Selection.uniform(population.getPopulation(), 2);
					newIndvs.add(Crossover.average(crossover));
				}

				// mutation
				for (int i = 0; i < numOfMutIndv; i++) {
					mutatedIndvs.add(Mutation.deterministicMutation(newIndvs.get(i), evals, evaluationsLimit));
				}

				mutatedIndvs.addAll(population.getPopulation());

				List<Individual> keepIndv = Selection.plusStrategy(mutatedIndvs, populationSize);

				// remove all parents from population
				population.removeFromPopulation(population.getPopulation());

				// replace parents with children
				population.setPopulation(keepIndv);
			}

			generations++;
			evals = evals + (numOfCrIndv + numOfMutIndv) * numOfPopulations;

		}

	}

	public void evolve2(int numOfCrIndv, int numOfMutIndv) {
		Population population = populations.get(0);

		int evals = populationSize;
		while (evals + (numOfCrIndv + numOfMutIndv) < evaluationsLimit) {

			List<Individual> mutatedIndvs = new ArrayList<>();

			// Select individuals for mutation
			List<Individual> mutIndvs = Selection.uniform(population.getPopulation(), numOfMutIndv);

			// mutation
			for (int i = 0; i < numOfMutIndv; i++) {
				mutatedIndvs.add(Mutation.deterministicMutation(mutIndvs.get(i), evals, evaluationsLimit));
			}

			mutatedIndvs.addAll(population.getPopulation());

			List<Individual> keepIndv = Selection.plusStrategy(mutatedIndvs, populationSize);

			// remove all parents from population
			population.removeFromPopulation(population.getPopulation());

			// replace parents with children
			population.setPopulation(keepIndv);

			evals = evals + (numOfCrIndv + numOfMutIndv);

		}

	}
}
