package utils;

import evolutionary.Individual;

public class Calculate {

	public static double euclideanDistance(Individual indv1, Individual indv2) {
		double sum = 0.0;

		double[] genomes1 = indv1.getGenomes();
		double[] genomes2 = indv2.getGenomes();

		for (int i = 0; i < indv1.getGenomes().length; i++) {
			sum += Math.pow((genomes1[i] - genomes2[i]), 2);
		}

		return Math.sqrt(sum);
	}

}
