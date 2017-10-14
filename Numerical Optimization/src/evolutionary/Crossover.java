package evolutionary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Crossover {

	static ThreadLocalRandom rand = ThreadLocalRandom.current();

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
				if (rand.nextInt(0, 2) == 0) {
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

				if (rand.nextInt(0, 2) == 0) {
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

	public static Individual blend(List<Individual> indvs, double alpha) {

		Individual x, y = null;
		boolean a = rand.nextBoolean();
		double crossRate = 0;
		double[] genomesNew = new double[indvs.get(0).getGenomes().length];
		double[] sigmaNew = new double[indvs.get(0).getSigma().length];

		if (indvs.get(0).getFitness() < indvs.get(1).getFitness()) {
			x = indvs.get(0);
			y = indvs.get(1);
		} else {
			x = indvs.get(1);
			y = indvs.get(0);
		}

		for (int i = 0; i < indvs.get(0).getGenomes().length; i++) {
			double genome, sigma;
			if (rand.nextDouble() <= y.getCrossRate()) {
				double rangeMin = x.getGenomes()[i] - alpha * (y.getGenomes()[i] - x.getGenomes()[i]);
				double rangeMax = y.getGenomes()[i] - alpha * (y.getGenomes()[i] - x.getGenomes()[i]);
				double randomNumber = rand.nextDouble();

				genome = rangeMin + (rangeMax - rangeMin) * randomNumber;
				sigma = (x.getSigma()[i] + y.getSigma()[i]) / 2;
				crossRate = (x.getCrossRate() + y.getCrossRate()) / 2;
			} else {
				if (a) {
					genome = x.getGenomes()[i];
					sigma = x.getSigma()[i];
					crossRate = x.getCrossRate();
				} else {
					genome = y.getGenomes()[i];
					sigma = y.getSigma()[i];
					crossRate = y.getCrossRate();
				}
			}

			genomesNew[i] = genome;
			sigmaNew[i] = sigma;
		}

		return new Individual(genomesNew, sigmaNew, indvs.get(0).getEvaluation(), indvs.get(0).getMutationType());
	}

}
