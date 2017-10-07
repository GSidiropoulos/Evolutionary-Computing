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
//		strategy.evolve(50);

		EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(10, evaluations_limit_,
				MutationType.UNCORRELATED, evaluation_);

		strategy.evolve(10);
	}
	
	
	// public static void main(String args[]) {
	// }

}
