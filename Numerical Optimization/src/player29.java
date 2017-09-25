import org.vu.contest.ContestSubmission;

import evolutionary.Individual;
import evolutionary.Population;

import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.Properties;

public class player29 implements ContestSubmission {
	Random rnd_;
	ContestEvaluation evaluation_;
	private int evaluations_limit_;

	public player29() {
		rnd_ = new Random();
	}

	public void setSeed(long seed) {
		// Set seed of algortihms random process
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
		Population pop = new Population(10, evaluation_);

		// calculate fitness
		int evals = 0;
		while (evals < evaluations_limit_) {
			// Select parents
			// Apply crossover / mutation operators

			// Check fitness of unknown function
			// create individual
			// Select survivors

			evals++;

		}

	}

	public static void main(String args[]) {

		System.out.println(Math.ulp(1.0)+10);
	}

}
