package evolutionary;

import java.util.concurrent.ThreadLocalRandom;

import strategy.EvolutionaryStrategy;

public class Mutation {

	// static class

	static double[] tau; // if there are two learning rates, overall learning rate in position 0 and
							// coordinate wise learning rate in 1
	static final double EPSILON_MACHINE = Math.ulp(1.0);
	static MutationType type;
	//static ThreadLocalRandom rand = ThreadLocalRandom.current();

	public static enum MutationType {
		UNCORRELATED(1, 1), UNCORRELATED_N(10, 2);

		private final int numOfSigmas; // number of sigmas
		private final int numOfTau; // number of learning rates

		MutationType(int numOfSigmas, int numOfTau) {
			this.numOfSigmas = numOfSigmas;
			this.numOfTau = numOfTau;
		}

		public int getNumOfSigmas() {
			return numOfSigmas;
		}

		public int getNumOfTau() {
			return numOfTau;
		}

	}

	public static void init(int popSize, MutationType t) {
		if (t == MutationType.UNCORRELATED) {
			type = t;
			tau = new double[1];
			tau[0] = 1.0 / Math.sqrt(10);

		} else if (t == MutationType.UNCORRELATED_N) {
			type = t;
			tau = new double[2];
			tau[0] = 1.0 / Math.sqrt(2 * 10);
			tau[1] = 1.0 / Math.sqrt(2 * Math.sqrt(10));
		}
	}

	// Uncorrelated mutation with one σ
	public static Individual uncorrelatedMutation(Individual indv) {

		double sigma = indv.getSigma()[0];
		double sigmaNew = sigma * Math.exp(tau[0] * EvolutionaryStrategy.rand.nextGaussian());

		// boundary rule
		if (sigmaNew < EPSILON_MACHINE) {
			sigmaNew = EPSILON_MACHINE;
		}

		double[] genomes = indv.getGenomes();
		double[] genomesNew = new double[10];

		for (int i = 0; i < genomes.length; i++) {
			genomesNew[i] = genomes[i] + sigmaNew * EvolutionaryStrategy.rand.nextGaussian();
		}

		double[] sigmaNewArray = new double[1];
		sigmaNewArray[0] = sigmaNew;

		return new Individual(genomesNew, sigmaNewArray, indv.getEvaluation(), type);
	}

	// Uncorrelated mutation with n σ’s
	public static Individual uncorrelatedMutationN(Individual indv) {
		double[] sigma = indv.getSigma();
		double[] sigmaNew = new double[sigma.length];

		double n = EvolutionaryStrategy.rand.nextGaussian();
		for (int i = 0; i < sigmaNew.length; i++) {
			sigmaNew[i] = sigma[i] * Math.exp(tau[0] * n + tau[1] * EvolutionaryStrategy.rand.nextGaussian());
		}

		double[] genomes = indv.getGenomes();
		double[] genomesNew = new double[10];

		for (int i = 0; i < genomes.length; i++) {
			genomesNew[i] = genomes[i] + sigmaNew[i] * EvolutionaryStrategy.rand.nextGaussian();
		}

		return new Individual(genomesNew, sigmaNew, indv.getEvaluation(), type);

	}

	public static Individual deterministicMutation(Individual indv, double numOfEvals, double evalsLimit) {

		double[] sigma = indv.getSigma();

		double[] genomes = indv.getGenomes();
		double[] genomesNew = new double[10];

		for (int i = 0; i < indv.getGenomes().length; i++) {

			sigma[i] = 1.0 - (numOfEvals / evalsLimit);
			sigma[i] = Math.pow(sigma[i], 4);
			genomesNew[i] = genomes[i] + sigma[i] * EvolutionaryStrategy.rand.nextGaussian();

		}

		return new Individual(genomesNew, sigma, indv.getEvaluation(), type);
	}

}