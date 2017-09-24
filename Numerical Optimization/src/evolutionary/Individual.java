package evolutionary;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import org.vu.contest.ContestEvaluation;

public class Individual {

	private double[] genomes = new double[10];
	private double fitness;
	private ContestEvaluation evaluation;
	private static int origin = -5;
	private static int bound = 5;

	public Individual(ContestEvaluation evaluation) {
		super();
		this.evaluation = evaluation;
		setGenomes();
		calculateFitness();
	}

	private void setGenomes() {

		for (int i = 0; i < genomes.length; i++) {
			genomes[i] = ThreadLocalRandom.current().nextDouble(origin, bound);
		}

	}

	public double[] getGenomes() {
		return genomes;
	}

	public double getFitness() {
		return fitness;
	}

	private void calculateFitness() {
		this.fitness = (double) evaluation.evaluate(genomes);
	}

	@Override
	public String toString() {
		return "Individual [genomes=" + Arrays.toString(genomes) + ", fitness=" + fitness + "]";
	}

}
