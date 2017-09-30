package evolutionary;

import java.util.concurrent.ThreadLocalRandom;

public class Mutation {

	// static class

	static double[] tau; // if there are two learning rates, overall learning rate in position 0 and
							// coordinate wise learning rate in 1
	static final double EPSILON_MACHINE = Math.ulp(1.0);
	static MutationType type;

	public static enum MutationType {
		UNCORRELATED(1, 1), UNCORRELATED_N(10, 2);

		private final int numOfSigmas; // number of sigmas
		private final int numOfTau; // number of learning rates

		MutationType(int numOfSigmas, int numOfTau) {
			this.numOfSigmas = numOfSigmas;
			this.numOfTau = numOfTau;
		}

		private int numOfSigmas() {
			return numOfSigmas;
		}

		private int radius() {
			return numOfTau;
		}

	}

	public static void init(int popSize, MutationType t) {
		if (t == MutationType.UNCORRELATED) {
			type = t;
			tau = new double[1];
			tau[0] = 1.0 / Math.sqrt(popSize);
		}
	}

	// Uncorrelated mutation with one σ
	public static Individual uncorrelatedMutation(Individual indv) {

		double sigma = indv.getSigma();
		double sigmaNew = sigma * Math.exp(tau[0] * ThreadLocalRandom.current().nextGaussian());

		// boundary rule
		if (sigmaNew < EPSILON_MACHINE) {
			sigmaNew = EPSILON_MACHINE;
		}

		double[] genomes = indv.getGenomes();
		double[] genomesNew = new double[10];

		for (int i = 0; i < genomes.length; i++) {
			genomesNew[i] = genomes[i] + sigmaNew * ThreadLocalRandom.current().nextGaussian();
		}

		return new Individual(genomesNew, sigmaNew, indv.getEvaluation());

		// // test
		// return new Individual(genomesNew, sigmaNew);
	}

	// Uncorrelated mutation with n σ’s
	public static void ucorrelatedMutationN() {

	}

}
