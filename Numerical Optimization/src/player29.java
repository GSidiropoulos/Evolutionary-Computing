import org.vu.contest.ContestSubmission;

import evolutionary.Crossover;
import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Population;
import evolutionary.Selection;

import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
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

		// init population
		int popSize = 10;
		Population pop = new Population(popSize, evaluation_, Mutation.MutationType.UNCORRELATED);

		// initialize mutation
		Mutation.init(popSize, Mutation.MutationType.UNCORRELATED);

		int evals = 0;
		while (evals < evaluations_limit_) {

			// Select parents
			List<Integer> parentIds = Selection.selectParentsRandom(10, 10);

			// Apply crossover / mutation operators
			List<Individual> newPop = new ArrayList<Individual>();

			for (int i : parentIds) {
				newPop.add(Mutation.uncorrelatedMutation(pop.getPopulation().get(i)));
			}
			newPop.addAll(pop.getPopulation());
			System.out.println(newPop.size());
			List<Individual> lol = Selection.plusStrategy(newPop, 10);

			// remove all parents from population
			pop.removeFromPopulation(pop.getPopulation());

			// replace parents with children
			pop.setPopulation(lol);

			for (Individual i : pop.getPopulation()) {
				System.out.println(i.toString());
			}

			System.out.println("----------------------------------------");

			evals = evals + 10;

		}

	}

}
