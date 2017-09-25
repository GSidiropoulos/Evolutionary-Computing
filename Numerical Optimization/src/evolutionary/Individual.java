package evolutionary;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import org.vu.contest.ContestEvaluation;

public class Individual {

	private double[] genomes = new double[10];
	private double sigma;
	private double fitness;
	private ContestEvaluation evaluation;
	static final int ORIGIN = -5;
	static final int BOUND = 5;

	public Individual(ContestEvaluation evaluation) {
		super();
		this.evaluation = evaluation;
		setGenomes();
		calculateFitness();
	}

	public Individual(double[] genomes, double sigma, ContestEvaluation evaluation) {
		super();
		this.genomes = genomes;
		this.sigma = sigma;
		this.evaluation = evaluation;
	}

	private void setGenomes() {

		for (int i = 0; i < genomes.length; i++) {
			genomes[i] = ThreadLocalRandom.current().nextDouble(ORIGIN, BOUND);
		}

	}
	
	

	public ContestEvaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(ContestEvaluation evaluation) {
		this.evaluation = evaluation;
	}

	public double getSigma() {
		return sigma;
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
