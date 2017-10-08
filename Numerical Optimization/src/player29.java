import org.vu.contest.ContestSubmission;

import evolutionary.Crossover;
import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Mutation.MutationType;
import evolutionary.Population;
import evolutionary.Selection;
import strategy.EvolutionaryStrategy;
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

//		EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(60, evaluations_limit_,
//				MutationType.UNCORRELATED_N, evaluation_);
//
//		strategy.evolve(50, 30);

		 EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(10,
		 evaluations_limit_,
		 MutationType.UNCORRELATED, evaluation_);
		
		 strategy.evolve(0,10);
	}

	// for parameter tuning
//	public static void main(String args[]) {
//
//		if (args[0].equals("bent")) {
//
//			double bestScore = -1;
//			int pos;
//			ContestEvaluation evaluation = new SchaffersEvaluation();
//			Properties props = evaluation.getProperties();
//			int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
//			
//			for (int i = 10; i < 100; i++) {
//				// init
//				EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(i, evaluations_limit_,
//						MutationType.UNCORRELATED, evaluation);
//
//				// evolve population
//				try {
//					strategy.evolve(0, i);
//				} catch (Exception e) {
//					System.out.println(e.toString());
//					System.out.println(evaluation.getFinalResult());
//				}
//				double bestCurrentScore = evaluation.getFinalResult();
//				System.out.println("Best result " + i + ": " + bestCurrentScore);
//
//				if (bestCurrentScore > bestScore) {
//					bestScore = bestCurrentScore;
//					pos = i;
//				}
//
//			}
//
//			System.out.println("i " + bestScore);
//
//		} else if (args[0].equals("schaf")) {
//
//			ContestEvaluation evaluation = new SchaffersEvaluation();
//			Properties props = evaluation.getProperties();
//			int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
//
//			EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(60, evaluations_limit_,
//					MutationType.UNCORRELATED_N, evaluation);
//
//			System.out.println("Here");
//			try {
//
//				strategy.evolve(50, 30);
//			} catch (Exception e) {
//				System.out.println(e.toString());
//				System.out.println(evaluation.getFinalResult());
//			}
//			System.out.println("Best result: " + evaluation.getFinalResult());
//		} else {
//
//		}
//
//	}

}
