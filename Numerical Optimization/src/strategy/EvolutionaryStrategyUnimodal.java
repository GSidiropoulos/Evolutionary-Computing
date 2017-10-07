package strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.vu.contest.ContestEvaluation;

import evolutionary.Mutation.MutationType;
import utils.IndividualComparator;
import evolutionary.Crossover;
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
		// TODO Auto-generated method stub
		
	}



//	@Override
//	public void evolve(int lambda) {
//
//		int evals = 0;
//		while (evals < evaluationsLimit - 1) {
//
//			// Apply crossover / mutation operators
//			List<Individual> newPop = new ArrayList<Individual>();
//
//			for (int i = 0; i < lambda; i++) {
//				// Select individuals for crossover
//				List<Individual> cross = Selection.uniform(population.getPopulation(), lambda);
//
//				// Apply crossover
//				List<Individual> indvs = Crossover.uniform(cross);
//				newPop.addAll(indvs);
//
//				// Mutate offspring
//				for (Individual indv : indvs) {
//					newPop.add(Mutation.uncorrelatedMutationN(indv));
//				}
//			}
//
//			//newPop.addAll(population.getPopulation());
//			List<Individual> keepIndv = Selection.plusStrategy(newPop, populationSize);
//
//			// remove all parents from population
//			population.removeFromPopulation(population.getPopulation());
//
//			// replace parents with children
//			population.setPopulation(keepIndv);
//
//			evals = evals + lambda;
//
//		}
//
//	}


}
