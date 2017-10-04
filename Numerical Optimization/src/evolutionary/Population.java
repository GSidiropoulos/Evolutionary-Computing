package evolutionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.vu.contest.ContestEvaluation;

import evolutionary.Mutation.MutationType;
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

}
