package strategy;

import java.util.ArrayList;
import java.util.List;

import org.vu.contest.ContestEvaluation;

import evolutionary.Crossover;
import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Population;
import evolutionary.Mutation.MutationType;
import evolutionary.Selection;

public class EvolutionaryStrategyKatsuura extends EvolutionaryStrategy {

	public EvolutionaryStrategyKatsuura(int numOfPopulations, int populationSize, int evaluationsLimit,
			MutationType mutationType, ContestEvaluation evaluationType) {
		super(numOfPopulations, populationSize, evaluationsLimit, mutationType, evaluationType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evolve(int numOfCrIndv, int numOfMutIndv, int type) {
		evolve1(numOfCrIndv, numOfMutIndv);

	}

	public void evolve1(int numOfCrIndv, int numOfMutIndv) {
		System.out.println("Begin");

		int evals = numOfPopulations * populationSize;
		int generations = 0;
		while (evals + (numOfCrIndv + numOfMutIndv) * numOfPopulations < evaluationsLimit) {

			for (Population population : populations) {

				if ((generations % 25) == 0) {
					migrate(10);

				}

				List<Individual> newIndvs = new ArrayList<>();
				List<Individual> mutatedIndvs = new ArrayList<>();

				// crossover
				for (int i = 0; i < numOfCrIndv; i++) {
					// Select individuals for crossover
					List<Individual> crossover = Selection.uniform(population.getPopulation(), 2);
					newIndvs.add(Crossover.blend(crossover, 0.5));
				}

				// mutation
				for (int i = 0; i < numOfMutIndv; i++) {
					mutatedIndvs.add(Mutation.cauchyMutation(newIndvs.get(i), 1, evals, evaluationsLimit));
				}

				// mutatedIndvs.addAll(population.getPopulation());

				List<Individual> keepIndv = Selection.commaStrategy(mutatedIndvs, populationSize);

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
			// if (evals % (200 * populationSize) == 0) {
			// population.reInitializePopulation();
			// }
			List<Individual> newIndvs = new ArrayList<>();
			List<Individual> mutatedIndvs = new ArrayList<>();

			// population.shareFitness(0.001);
			// crossover
			for (int i = 0; i < numOfCrIndv; i++) {
				// Select individuals for crossover
				List<Individual> crossover = Selection.uniform(population.getPopulation(), 2);
				newIndvs.add(Crossover.blend(crossover, 0.5));
			}

			// mutation
			for (int i = 0; i < numOfMutIndv; i++) {
				mutatedIndvs.add(Mutation.cauchyMutation(newIndvs.get(i), 1, evals, evaluationsLimit));
			}

			// mutatedIndvs.addAll(population.getPopulation());

			List<Individual> keepIndv = Selection.commaStrategy(mutatedIndvs, populationSize);

			// remove all parents from population
			population.removeFromPopulation(population.getPopulation());

			// replace parents with children
			population.setPopulation(keepIndv);

			evals = evals + (numOfCrIndv + numOfMutIndv);

		}

	}
}
