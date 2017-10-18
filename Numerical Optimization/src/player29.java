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

			EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(1, 10, evaluations_limit_,
					MutationType.UNCORRELATED, evaluation_, rnd_);
			strategy.evolve(30, 30, 2);
	
			// EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(1, 26,
			// evaluations_limit_,
			// MutationType.UNCORRELATED, evaluation_, rnd_);
			// strategy.evolve(15, 15, 1);

		} else {
			if (hasStructure) {
			
				EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(1, 52, evaluations_limit_,
						MutationType.UNCORRELATED_N, evaluation_, rnd_);

				strategy.evolve(156, 156, 3);

				// EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(1, 37,
				// evaluations_limit_,
				// MutationType.UNCORRELATED_N, evaluation_, rnd_);
				//
				// strategy.evolve(21, 21, 1);

			} else {
				EvolutionaryStrategy strategy = new EvolutionaryStrategyKatsuura(1, 50, evaluations_limit_,
						MutationType.UNCORRELATED_N, evaluation_, rnd_);
				strategy.evolve(41, 41, 2);

				// EvolutionaryStrategy strategy = new EvolutionaryStrategyKatsuura(5, 37,
				// evaluations_limit_,
				// MutationType.UNCORRELATED_N, evaluation_, rnd_);
				// strategy.evolve(111, 111, 1);

				// EvolutionaryStrategy strategy = new EvolutionaryStrategyKatsuura(1, 50,
				// evaluations_limit_,
				// MutationType.UNCORRELATED_N, evaluation_);
				// strategy.evolve(50, 50, 2);
			}
		}

	}

	// for parameter tuning and various statistics
	public static void main(String args[]) {

		if (args[0].equals("bent")) {

			double total = 0;
			for (int i = 0; i < 5; i++) {
				Random rnd_ = new Random();
				rnd_.setSeed(i);

				ContestEvaluation evaluation = new BentCigarFunction();
				Properties props = evaluation.getProperties();
				int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));

				// // best approach
				// EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(1, 26,
				// evaluations_limit_,
				// MutationType.UNCORRELATED, evaluation, rnd_);
				// strategy.evolve(15, 15, 1);

				// second approach
				EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(1, 10, evaluations_limit_,
						MutationType.UNCORRELATED, evaluation, rnd_);
				strategy.evolve(30, 30, 2);

				System.out.println(evaluation.getFinalResult());
				total += evaluation.getFinalResult();
			}

			System.out.println("Avg for 5 runs: " + total / 5);

		} else if (args[0].equals("schaf")) {

			double total = 0;
			for (int i = 0; i < 5; i++) {

				Random rnd_ = new Random();
				rnd_.setSeed(i);

				ContestEvaluation evaluation = new SchaffersEvaluation();
				Properties props = evaluation.getProperties();
				int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));

				// EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(1, 52,
				// evaluations_limit_,
				// MutationType.UNCORRELATED_N, evaluation, rnd_);
				//
				// strategy.evolve(156, 156, 3);
				//

				EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(1, 37, evaluations_limit_,
						MutationType.UNCORRELATED_N, evaluation, rnd_);
				strategy.evolve(21, 21, 1);

				System.out.println(evaluation.getFinalResult());
				total += evaluation.getFinalResult();
			}

			System.out.println("Avg for 5 runs: " + total / 5);

		} else {

			double total = 0;
			for (int i = 0; i < 5; i++) {

				Random rnd_ = new Random();
				rnd_.setSeed(i);

				ContestEvaluation evaluation = new KatsuuraEvaluation();
				Properties props = evaluation.getProperties();
				int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));

				// EvolutionaryStrategy strategy = new EvolutionaryStrategyKatsuura(1, 50,
				// evaluations_limit_,
				// MutationType.UNCORRELATED_N, evaluation, rnd_);
				// strategy.evolve(41, 41, 2);

				EvolutionaryStrategy strategy = new EvolutionaryStrategyKatsuura(5, 37, evaluations_limit_,
						MutationType.UNCORRELATED_N, evaluation, rnd_);
				strategy.evolve(111, 111, 1);

				System.out.println(evaluation.getFinalResult());
				total += evaluation.getFinalResult();
			}

			System.out.println("Avg for 5 runs: " + total / 5);

		}

	}

}
