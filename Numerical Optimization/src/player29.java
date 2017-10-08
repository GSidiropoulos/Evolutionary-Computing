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
			EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(17, evaluations_limit_,
					MutationType.UNCORRELATED, evaluation_);
			strategy.evolve(0, 10);
		} else {
			if (hasStructure) {
				EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(120, evaluations_limit_,
						MutationType.UNCORRELATED_N, evaluation_);
				strategy.evolve(50, 80);

			} else {
				EvolutionaryStrategy strategy = new EvolutionaryStrategyKatsuura(120, evaluations_limit_,
						MutationType.UNCORRELATED_N, evaluation_);
				strategy.evolve(50, 120);
			}
		}

	}

	// for parameter tuning
	public static void main(String args[]) {

		if (args[0].equals("bent")) {

			double bestScore = -1;
			int bestPopSize = 0;
			int bestMutSize = 0;

			for (int popSize = 5; popSize < 20; popSize++) {
				for (int mutSize = 1; mutSize <= popSize; mutSize++) {
					ContestEvaluation evaluation = new BentCigarFunction();
					Properties props = evaluation.getProperties();
					int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
					// init
					EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(popSize, evaluations_limit_,
							MutationType.UNCORRELATED, evaluation);

					// evolve population
					try {
						strategy.evolve(0, popSize);
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

			EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(60, evaluations_limit_,
					MutationType.UNCORRELATED_N, evaluation);

			try {

				strategy.evolve(50, 50);
			} catch (Exception e) {
				System.out.println(e.toString());
				System.out.println(evaluation.getFinalResult());
			}

			System.out.println("Best result: " + evaluation.getFinalResult());

		} else {

			ContestEvaluation evaluation = new KatsuuraEvaluation();
			Properties props = evaluation.getProperties();
			int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));

			EvolutionaryStrategy strategy = new EvolutionaryStrategyKatsuura(120, evaluations_limit_,
					MutationType.UNCORRELATED_N, evaluation);

			try {

				strategy.evolve(50, 120);
			} catch (Exception e) {
				System.out.println(e.toString());
				System.out.println(evaluation.getFinalResult());
			}

			System.out.println("Best result: " + evaluation.getFinalResult());
		}

	}

}
