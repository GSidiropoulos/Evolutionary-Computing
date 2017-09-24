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

}
