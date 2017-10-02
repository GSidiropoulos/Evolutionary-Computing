package evolutionary;

import java.util.List;

public class Crossover {

	// static??

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
			genomes[i] = genomes[i] / genomes.length;
			sigma[i] = sigma[i] / genomes.length;
		}

		return new Individual(genomes, sigma, indvs.get(0).getEvaluation(), indvs.get(0).getMutationType());
	}

}
