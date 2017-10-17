import org.vu.contest.ContestSubmission;

import evolutionary.Crossover;
import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Mutation.MutationType;
import evolutionary.Population;
import evolutionary.Selection;
import strategy.EvolutionaryStrategy;
import strategy.EvolutionaryStrategyKatsuura;
import strategy.EvolutionaryStrategyMultimodal;
import strategy.EvolutionaryStrategyUnimodal;

import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class player29 implements ContestSubmission {
	Random rnd_;
	ContestEvaluation evaluation_;
	private int evaluations_limit_;

	public player29() {
		rnd_ = new Random();
	}

	public void setSeed(long seed) {
		// Set seed of algorithms random process
		rnd_.setSeed(seed);
	}

	public void setEvaluation(ContestEvaluation evaluation) {
		// Set evaluation problem used in the run
		evaluation_ = evaluation;

		// Get evaluation properties
		Properties props = evaluation.getProperties();
		// Get evaluation limit
		evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
		// Property keys depend on specific evaluation
		// E.g. double param = Double.parseDouble(props.getProperty("property_name"));
		boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
		boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
		boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

		// Do sth with property values, e.g. specify relevant settings of your algorithm
		if (isMultimodal) {
			// Do sth
		} else {
			// Do sth else
		}
	}

	public void run() {
		// Get evaluation properties
		Properties props = evaluation_.getProperties();
		// Get evaluation limit
		evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
		// Property keys depend on specific evaluation
		// E.g. double param = Double.parseDouble(props.getProperty("property_name"));
		boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
		boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
		boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

		if (!isMultimodal) {
			EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(1, 26, evaluations_limit_,
					MutationType.UNCORRELATED, evaluation_);
			strategy.evolve(15, 15, 1);

			// EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(1, 10,
			// evaluations_limit_,
			// MutationType.UNCORRELATED, evaluation_);
			// strategy.evolve(30, 30, 2);
		} else {
			if (hasStructure) {
				EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(1, 52, evaluations_limit_,
						MutationType.UNCORRELATED_N, evaluation_);

				strategy.evolve(156, 156, 3);
				
//				EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(1, 37, evaluations_limit_,
//						MutationType.UNCORRELATED_N, evaluation_);
//
//				strategy.evolve(21, 21, 1);
				
			} else {
				// EvolutionaryStrategy strategy = new EvolutionaryStrategyKatsuura(1, 120,
				// evaluations_limit_,
				// MutationType.UNCORRELATED_N, evaluation_);
				// strategy.evolve(50, 120, 0);

				EvolutionaryStrategy strategy = new EvolutionaryStrategyKatsuura(1, 50, evaluations_limit_,
						MutationType.UNCORRELATED_N, evaluation_);
				strategy.evolve(50, 50, 3);
			}
		}

	}

	// for parameter tuning
	public static void main(String args[]) {

		if (args[0].equals("bent")) {
			// ContestEvaluation evaluation = new BentCigarFunction();
			// Properties props = evaluation.getProperties();
			// int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
			//
			// EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(1, 10,
			// evaluations_limit_,
			// MutationType.UNCORRELATED, evaluation);
			// strategy.evolve(30, 30, 2);

			double bestScore = -1;
			int bestPopSize = 0;
			int bestMutSize = 0;

			for (int popSize = 5; popSize < 50; popSize++) {
				// greed search for the naive approach
				for (int mutSize = 1; mutSize < popSize; mutSize++) {

					// // greed search for the tournament selection
					// int mutSize = popSize * 3;
					ContestEvaluation evaluation = new BentCigarFunction();
					Properties props = evaluation.getProperties();
					int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));

					// init
					EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(1, popSize, evaluations_limit_,
							MutationType.UNCORRELATED, evaluation);

					// evolve population
					try {
						strategy.evolve(mutSize, mutSize, 1);
					} catch (Exception e) {
						System.out.println(e.toString());
						System.out.println(evaluation.getFinalResult());
					}
					double bestCurrentScore = evaluation.getFinalResult();
					System.out.println("Best result " + popSize + ": " + bestCurrentScore);

					if (bestCurrentScore > bestScore) {
						bestScore = bestCurrentScore;
						bestPopSize = popSize;
						bestMutSize = mutSize;
					}
				}
			}

			System.out.println("Pop: " + bestPopSize + " Mut: " + bestMutSize + " Score:" + bestScore);

		} else if (args[0].equals("schaf")) {
			ContestEvaluation evaluation = new SchaffersEvaluation();
			Properties props = evaluation.getProperties();
			int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
			EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(1, 37, evaluations_limit_,
					MutationType.UNCORRELATED_N, evaluation);

			strategy.evolve(21, 21, 1);
			System.out.println(evaluation.getFinalResult());

			////////////////////////////////////////////////////
			//
			// // find best setup for the best evolutionary strategy
			// double bestScore = -1;
			// int bestPopSize = 0;
			// int bestMutSize = 0;
			//
			// List<Integer> count30 = new ArrayList<Integer>();
			// List<Integer> countOver20 = new ArrayList<Integer>();
			//
			// for (int popSize = 30; popSize < 60; popSize++) {
			// int mutSize = popSize * 3;
			// int count = 0;
			// for (int i = 0; i < 30; i++) {
			// ContestEvaluation evaluation = new SchaffersEvaluation();
			// Properties props = evaluation.getProperties();
			// int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
			//
			// EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(1,
			//////////////////////////////////////////////////// popSize,
			//////////////////////////////////////////////////// evaluations_limit_,
			// MutationType.UNCORRELATED_N, evaluation);
			//
			// try {
			//
			// strategy.evolve(mutSize, mutSize, 3);
			// } catch (Exception e) {
			// System.out.println(e.toString());
			// System.out.println(evaluation.getFinalResult());
			// }
			//
			// double bestCurrentScore = evaluation.getFinalResult();
			//
			// if (bestCurrentScore == 10.0) {
			// count++;
			// }
			// if (bestCurrentScore > bestScore) {
			// bestScore = bestCurrentScore;
			// bestPopSize = popSize;
			// bestMutSize = mutSize;
			// }
			// }
			// if (count == 30) {
			// count30.add(popSize);
			// } else if (count > 20 && count <30) {
			// countOver20.add(popSize);
			// }
			// }
			//
			// System.out.println("Count 30" + count30.toString());
			// System.out.println("Count 20" + countOver20.toString());
			/////////////////////////////////////////////////

		} else {

			ContestEvaluation evaluation = new KatsuuraEvaluation();
			Properties props = evaluation.getProperties();
			int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));

			// EvolutionaryStrategy strategy = new EvolutionaryStrategyKatsuura(120,
			// evaluations_limit_,
			// MutationType.UNCORRELATED_N, evaluation);
			EvolutionaryStrategy strategy = new EvolutionaryStrategyKatsuura(1, 50, evaluations_limit_,
					MutationType.UNCORRELATED_N, evaluation);
			strategy.evolve(50, 50, 3);
			// EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(60,
			// evaluations_limit_,
			// MutationType.UNCORRELATED_N, evaluation);
			System.out.println("Best result: " + evaluation.getFinalResult());
		}

	}

}
