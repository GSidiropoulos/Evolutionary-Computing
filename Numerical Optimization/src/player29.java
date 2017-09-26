import org.vu.contest.ContestSubmission;

import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Population;

import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Arrays;
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
		// Run your algorithm here

		// init population
		int popSize = 10;
		Population pop = new Population(popSize, evaluation_);

		// mutation
		Mutation.init(popSize);

		// calculate fitness
		int evals = 0;
		while (evals < evaluations_limit_) {
			// Select parents
			// Apply crossover / mutation operators

			for (Individual indv : pop.getPopulation()) {

			}

			// Check fitness of unknown function
			// create individual
			// Select survivors

			evals++;

		}

	}

	// testing purpose
	public static void main(String args[]) {

		// init population
		int popSize = 3;
		Population pop = new Population(popSize);

		Mutation.init(popSize);
		int evals = 0;
		int evaluations_limit = 1;
		while (evals < evaluations_limit) {
			// Select parents
			// Apply crossover / mutation operators

			for (Individual indv : pop.getPopulation()) {

				System.out.println(Arrays.toString(indv.getGenomes()));
				System.out.println(indv.getSigma());
				Individual newIndv = Mutation.uncorrelatedMutation(indv);
				System.out.println(Arrays.toString(newIndv.getGenomes()));
				System.out.println(newIndv.getSigma());
				
				System.out.println("-----------------------------------");
			}

			// Check fitness of unknown function
			// create individual
			// Select survivors

			evals++;

		}

	}

}
