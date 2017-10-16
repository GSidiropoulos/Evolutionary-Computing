package strategy;

import java.util.ArrayList;
import java.util.List;

import org.vu.contest.ContestEvaluation;

import evolutionary.Crossover;
import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Population;
import evolutionary.Selection;
import evolutionary.Mutation.MutationType;

public class EvolutionaryStrategyMultimodal extends EvolutionaryStrategy {

	public EvolutionaryStrategyMultimodal(int numOfPopulations, int populationSize, int evaluationsLimit,
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
		case 3:
			evolve3(numOfCrIndv, numOfMutIndv);
			break;
			
		case 4:
			evolve4(numOfCrIndv, numOfMutIndv);
			break;
		}

	}

	public void evolve1(int numOfCrIndv, int numOfMutIndv) {
		Population population = populations.get(0);

		int evals = 0;
		while (evals + numOfMutIndv < evaluationsLimit) {

			if (evals % (200 * populationSize) == 0) {
				System.out.println("here");
				population.reInitializePopulation();
			}

			// Select individuals for mutation
			List<Individual> mutated = new ArrayList<Individual>();
			for (int i = 0; i < numOfMutIndv; i++) {
				mutated.addAll(Selection.uniform(population.getPopulation(), 1));
			}

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

	public void evolve2(int numOfCrIndv, int numOfMutIndv) {
		Population population = populations.get(0);

		int evals = populationSize;
		while (evals + (numOfCrIndv + numOfMutIndv) < evaluationsLimit) {

			// List<Individual> newIndvs = new ArrayList<>();
			List<Individual> mutatedIndvs = new ArrayList<>();
			// crossover
			population.shareFitness(0.1);

			// mutation
			for (int i = 0; i < numOfMutIndv; i++) {
				mutatedIndvs
						.add(Mutation.uncorrelatedMutationN(Selection.uniform(population.getPopulation(), 1).get(0)));
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

	public void evolve3(int numOfCrIndv, int numOfMutIndv) {
		Population population = populations.get(0);

		int evals = populationSize;
		while (evals + (numOfCrIndv + numOfMutIndv) < evaluationsLimit) {

			List<Individual> newIndvs = new ArrayList<>();
			List<Individual> mutatedIndvs = new ArrayList<>();

			// crossover
			for (int i = 0; i < numOfCrIndv; i++) {
				// Select individuals for crossover
				List<Individual> crossover = Selection.uniform(population.getPopulation(), 5);

				newIndvs.add(Crossover.average(crossover));
			}

			// mutation
			for (int i = 0; i < numOfMutIndv; i++) {
				mutatedIndvs.add(Mutation.uncorrelatedMutationN(newIndvs.get(i)));
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

	public void evolve4(int numOfCrIndv, int numOfMutIndv) {

		System.out.println("Begin");

		int evals = numOfPopulations * populationSize;
		int generations = 0;
		while (evals + (numOfCrIndv + numOfMutIndv) * numOfPopulations < evaluationsLimit) {

			for (Population population : populations) {

				if ((generations % 25) == 0) {
					migrate(5);

				}

				List<Individual> newIndvs = new ArrayList<>();
				List<Individual> mutatedIndvs = new ArrayList<>();

				// crossover
				for (int i = 0; i < numOfCrIndv; i++) {
					// Select individuals for crossover
					List<Individual> crossover = Selection.uniform(population.getPopulation(), 5);

					newIndvs.add(Crossover.average(crossover));
				}

				// mutation
				for (int i = 0; i < numOfMutIndv; i++) {
					mutatedIndvs.add(Mutation.uncorrelatedMutationN(newIndvs.get(i)));
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

}
