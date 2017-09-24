package evolutionary;

import java.util.ArrayList;
import java.util.List;

import org.vu.contest.ContestEvaluation;

public class Population {

	private List<Individual> population;
	private ContestEvaluation evaluation;
	private int popSize;

	public Population(int popSize, ContestEvaluation evaluation) {
		super();
		this.popSize = popSize;
		this.evaluation = evaluation;
		population = new ArrayList<Individual>();

		for (int i = 0; i < popSize; i++) {

			population.add(new Individual(evaluation));
		}
	}

	public void removeFromPopulation(Individual indv) {
		population.remove(indv);
//		avoid memory overload, if there is no reference to the object
//		it will be deleted by the garbage collector
		indv = null;
	}

	public void removeFromPopulation(List<Individual> indvs) {

	}

}
