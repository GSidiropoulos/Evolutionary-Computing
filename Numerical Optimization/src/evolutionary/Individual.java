package evolutionary;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import org.vu.contest.ContestEvaluation;

import evolutionary.Mutation.MutationType;

public class Individual {

	private double[] genomes = new double[10];
	private double[] sigma;
	private double[] nrmlDstrN = new double[10];
	private double fitness;

	private MutationType mutationType;
	private ContestEvaluation evaluation;
	private ThreadLocalRandom rand = ThreadLocalRandom.current();

	static final int ORIGIN = -5;
	static final int BOUND = 5;

	public Individual(ContestEvaluation evaluation, MutationType mutationType) {
		super();
		this.evaluation = evaluation;
		this.mutationType = mutationType;

		sigma = new double[this.mutationType.getNumOfSigmas()];
		for (int i = 0; i < sigma.length; i++) {
			sigma[i] = 1.0;
		}

		// init genomes
		setGenomes();
		// init value draw from normal distribution N_i
		setNrmlDstrN();
		// calculate fitness
		calculateFitness();
	}

	public Individual(double[] genomes, double[] sigma, ContestEvaluation evaluation, MutationType mutationType) {
		super();
		this.genomes = validateGenomesNaive(genomes);
		this.sigma = sigma;
		this.mutationType = mutationType;
		this.evaluation = evaluation;
		setNrmlDstrN();
		calculateFitness();
	}

	private void setGenomes() {
		for (int i = 0; i < genomes.length; i++) {
			genomes[i] = rand.nextDouble(ORIGIN, BOUND);
		}
	}

	public ContestEvaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(ContestEvaluation evaluation) {
		this.evaluation = evaluation;
	}

	public double[] getSigma() {
		return sigma;
	}

	public double[] getGenomes() {
		return genomes;
	}

	public MutationType getMutationType() {
		return mutationType;
	}

	public double getFitness() {
		return fitness;
	}

	private void calculateFitness() {
		this.fitness = (double) evaluation.evaluate(genomes);
	}

	public double getNrmlDstrN(int i) {
		return nrmlDstrN[i];
	}

	private void setNrmlDstrN() {
		for (int i = 0; i < nrmlDstrN.length; i++) {
			nrmlDstrN[i] = rand.nextGaussian();
		}
	}

	@Override
	public String toString() {
		return "Individual [genomes=" + Arrays.toString(genomes) + ", fitness=" + fitness + "]";
	}

	private double[] validateGenomesNaive(double[] genomes) {

		// naive approach
		for (int i = 0; i < genomes.length; i++) {

			if (genomes[i] < ORIGIN) {
				genomes[i] = ORIGIN;
			} else if (genomes[i] > BOUND) {
				genomes[i] = BOUND;
			}
		}

		return genomes;
	}

}
