package strategy;

import java.util.ArrayList;
import java.util.List;

import org.vu.contest.ContestEvaluation;

import evolutionary.Crossover;
import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Selection;
import evolutionary.Mutation.MutationType;

public class EvolutionaryStrategyMultimodal extends EvolutionaryStrategy {

	public EvolutionaryStrategyMultimodal(int populationSize, int evaluationsLimit, MutationType mutationType,
			ContestEvaluation evaluationType) {
		super(populationSize, evaluationsLimit, mutationType, evaluationType);
		// TODO Auto-generated constructor stub
	}

	// @Override
	// public void evolve(int numOfCrIndv, int numOfMutIndv) {
	//
	// int evals = 0;
	// while (evals+numOfMutIndv < evaluationsLimit) {
	//
	// if (evals % (200 * populationSize) == 0) {
	// population.reInitializePopulation();
	// }
	//
	// // Select individuals for mutation
	// List<Individual> mutated = Selection.uniform(population.getPopulation(),
	// numOfMutIndv);
	//
	// // Apply mutation operators
	// List<Individual> newPop = new ArrayList<Individual>();
	//
	// for (Individual indv : mutated) {
	// newPop.add(Mutation.uncorrelatedMutationN(indv));
	// }
	//
	// newPop.addAll(population.getPopulation());
	// // System.out.println("Population Size: " + newPop.size());
	//
	// List<Individual> keepIndv = Selection.plusStrategy(newPop, populationSize);
	//
	// // remove all parents from population
	// population.removeFromPopulation(population.getPopulation());
	//
	// // replace parents with children
	// population.setPopulation(keepIndv);
	//
	// evals = evals + numOfMutIndv;
	//
	// }
	//
	// }

	// @Override
	// public void evolve(int numOfCrIndv, int numOfMutIndv) {
	//
	// int evals = populationSize;
	// while (evals +(numOfCrIndv + numOfMutIndv) < evaluationsLimit) {
	//
	// //List<Individual> newIndvs = new ArrayList<>();
	// List<Individual> mutatedIndvs = new ArrayList<>();
	// // crossover
	// population.shareFitness(0.1);
	//
	// // mutation
	// for (int i = 0; i < numOfMutIndv; i++) {
	// mutatedIndvs.add(Mutation.uncorrelatedMutationN(Selection.uniform(population.getPopulation(),
	// 1).get(0)));
	// }
	//
	// mutatedIndvs.addAll(population.getPopulation());
	//
	// List<Individual> keepIndv = Selection.plusStrategy(mutatedIndvs,
	// populationSize);
	//
	// // remove all parents from population
	// population.removeFromPopulation(population.getPopulation());
	//
	// // replace parents with children
	// population.setPopulation(keepIndv);
	//
	// evals = evals + (numOfCrIndv + numOfMutIndv);
	//
	// }
	//
	//
	// }

	// @Override
	public void evolve(int numOfCrIndv, int numOfMutIndv) {

		int evals = populationSize;
		while (evals + (numOfCrIndv + numOfMutIndv) < evaluationsLimit) {

			List<Individual> newIndvs = new ArrayList<>();
			List<Individual> mutatedIndvs = new ArrayList<>();
			// crossover
			// population.shareFitness(0.1);
			for (int i = 0; i < numOfCrIndv; i++) {
				// Select individuals for crossover
				List<Individual> crossover = Selection.uniform(population.getPopulation(), 5);

				newIndvs.add(Crossover.average(crossover));
			}

			// mutation
			for (int i = 0; i < numOfMutIndv; i++) {
				// mutatedIndvs.add(Mutation.uncorrelatedMutationN(Selection.uniform(population.getPopulation(),
				// 1).get(0)));

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

}
