package evolutionary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import strategy.EvolutionaryStrategy;

public class Crossover {

	//static ThreadLocalRandom rand = ThreadLocalRandom.current();

	public static Individual average(List<Individual> indvs) {

		double[] genomes = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		double[] sigma = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };

		for (Individual indv : indvs) {
			double[] parentGenomes = indv.getGenomes();
			double[] parentSigma = indv.getSigma();

			for (int i = 0; i < parentGenomes.length; i++) {

				genomes[i] = genomes[i] + parentGenomes[i];
				sigma[i] = sigma[i] + parentSigma[i];

			}
		}

		for (int i = 0; i < genomes.length; i++) {
			genomes[i] = genomes[i] / indvs.size();
			sigma[i] = sigma[i] / indvs.size();

			// genomes[i] = genomes[i] / genomes.length;
			// sigma[i] = sigma[i] / genomes.length;

		}

		return new Individual(genomes, sigma, indvs.get(0).getEvaluation(), indvs.get(0).getMutationType());
	}

	public static List<Individual> uniform(List<Individual> indvs) {

		int numOfGenomes = indvs.get(0).getGenomes().length;
		double[] c1Genomes = new double[numOfGenomes];
		double[] c2Genomes = new double[numOfGenomes];

		int numOfSigmas = indvs.get(0).getSigma().length;
		double[] c1Sigma = new double[numOfSigmas];
		double[] c2Sigma = new double[numOfSigmas];

		if (numOfSigmas == 1) {

			for (int i = 0; i < c1Genomes.length; i++) {
				if (EvolutionaryStrategy.rand.nextInt(2) == 0) {
					//
					c1Genomes[i] = indvs.get(0).getGenomes()[i];
					c2Genomes[i] = indvs.get(1).getGenomes()[i];
				} else {
					c1Genomes[i] = indvs.get(1).getGenomes()[i];
					c2Genomes[i] = indvs.get(0).getGenomes()[i];
				}
			}
			c1Sigma = indvs.get(0).getSigma();
			c2Sigma = indvs.get(1).getSigma();
		} else {
			for (int i = 0; i < c1Genomes.length; i++) {

				if (EvolutionaryStrategy.rand.nextInt(2) == 0) {
					//
					c1Genomes[i] = indvs.get(0).getGenomes()[i];
					c2Genomes[i] = indvs.get(1).getGenomes()[i];

					//
					c1Sigma[i] = indvs.get(0).getSigma()[i];
					c2Sigma[i] = indvs.get(1).getSigma()[i];
				} else {
					c1Genomes[i] = indvs.get(1).getGenomes()[i];
					c2Genomes[i] = indvs.get(0).getGenomes()[i];

					c1Sigma[i] = indvs.get(1).getSigma()[i];
					c2Sigma[i] = indvs.get(0).getSigma()[i];
				}
			}

		}
		List<Individual> childs = new ArrayList<>();
		childs.add(new Individual(c1Genomes, c1Sigma, indvs.get(0).getEvaluation(), indvs.get(0).getMutationType()));
		childs.add(new Individual(c2Genomes, c2Sigma, indvs.get(0).getEvaluation(), indvs.get(0).getMutationType()));

		return childs;
	}

}
