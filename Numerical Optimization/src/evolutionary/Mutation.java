package evolutionary;

import java.util.concurrent.ThreadLocalRandom;

public class Mutation {

	// static class

	static double tau;
	static final double EPSILON_MACHINE = Math.ulp(1.0);

	public static void init(int popSize) {
		tau = 1.0 / Math.sqrt(popSize);
	}

	public static Individual uncorrelatedMutation(Individual indv) {

		double sigma = indv.getSigma();
		double sigmaNew = sigma * Math.exp(tau * ThreadLocalRandom.current().nextGaussian());

		// boundary rule

		if (sigmaNew < EPSILON_MACHINE) {
			sigmaNew = EPSILON_MACHINE;
		}

		double[] genomes = indv.getGenomes();
		double[] genomesNew = new double[10];

		for (int i = 0; i < genomes.length; i++) {
			genomesNew[i] = genomes[i] + sigmaNew * ThreadLocalRandom.current().nextGaussian();
		}

		//return new Individual(genomesNew, sigmaNew, indv.getEvaluation());

		// test
		 return new Individual(genomesNew, sigmaNew);
	}

}
