package evolutionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.vu.contest.ContestEvaluation;

import evolutionary.Mutation.MutationType;
import utils.Calculate;
import utils.IndividualComparator;

public class Population {

	private List<Individual> population;
	private ContestEvaluation evaluation;
	private int popSize;
	private MutationType mutationType;

	public Population(int popSize, ContestEvaluation evaluation, MutationType mutationType) {
		super();
		this.popSize = popSize;
		this.evaluation = evaluation;
		this.mutationType = mutationType;
		population = new ArrayList<Individual>();

		for (int i = 0; i < popSize; i++) {
			population.add(new Individual(evaluation, mutationType));
		}
	}

	public void removeFromPopulation(Individual indv) {
		population.remove(indv);
		// avoid memory overload, if there is no reference to the object
		// it will be deleted by the garbage collector
		indv = null;
	}

	public void removeFromPopulation(List<Individual> indvs) {
		population.removeAll(indvs);

		for (Individual indv : indvs) {
			indv = null;
		}

	}

	public List<Individual> getPopulation() {
		return population;
	}

	public void setPopulation(List<Individual> population) {
		this.population = population;
	}

	public ContestEvaluation getEvaluation() {
		return evaluation;
	}

	public int getPopSize() {
		return popSize;
	}

	public void addIndividual(Individual indv) {
		population.add(indv);
	}

	public void sortPopulation() {
		// sort population in descending order w.r.t fitness
		Collections.sort(population, new IndividualComparator());
	}

	public void reInitializePopulation() {
		removeFromPopulation(population);

		for (int i = 0; i < popSize; i++) {
			population.add(new Individual(evaluation, mutationType));
		}

	}

	public void shareFitness(double sigmaShare) {

		List<Double> fitnessPrime = new ArrayList<>();

		for (Individual i : population) {
			double sumOfSh = 0;

			for (Individual j : population) {
				// calculate distance between genomes
				double distance = Calculate.euclideanDistance(i, j);

				double sh;
				if (distance <= sigmaShare) {
					sh = 1 - distance / sigmaShare;
				} else {
					sh = 0;
				}
				sumOfSh += sh;
			}

			fitnessPrime.add(i.getFitness() / sumOfSh);
		}

		// update fitness values
		for (int i = 0; i < popSize; i++) {
			population.get(i).setFitness(fitnessPrime.get(i));
		}

	}

}
