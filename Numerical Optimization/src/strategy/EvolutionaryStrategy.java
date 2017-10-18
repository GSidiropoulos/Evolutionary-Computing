package strategy;

import java.util.ArrayList;
import java.util.List;

import org.vu.contest.ContestEvaluation;

import evolutionary.Individual;
import evolutionary.Mutation;
import evolutionary.Population;
import utils.Calculate;
import utils.IO;
import utils.Statistics;
import evolutionary.Mutation.MutationType;

public abstract class EvolutionaryStrategy {

	protected int populationSize;
	protected int numOfPopulations;
	protected int evaluationsLimit;
	protected MutationType mutationType;
	protected ContestEvaluation evaluationType;
	protected List<Population> populations = new ArrayList<>();

	public EvolutionaryStrategy(int numOfPopulations, int populationSize, int evaluationsLimit,
			MutationType mutationType, ContestEvaluation evaluationType) {
		super();
		this.populationSize = populationSize;
		this.numOfPopulations = numOfPopulations;
		this.evaluationsLimit = evaluationsLimit;
		this.mutationType = mutationType;
		this.evaluationType = evaluationType;

		// initialize population
		for (int i = 0; i < numOfPopulations; i++) {
			populations.add(new Population(populationSize, evaluationType, mutationType));

		}
		// initialize mutation
		Mutation.init(populationSize, mutationType);
	}

	public abstract void evolve(int numOfCrIndv, int numOfMutIndv, int type);

	public void migrate(int migrants) {

		for (int i = 0; i < numOfPopulations; i++) {
			int[] ids = Calculate.chooseRandom(migrants, 0, populations.get(i).getPopSize());

			List<Individual> migrantList = new ArrayList<>();
			for (int m = 0; m < ids.length; m++) {
				migrantList.add(populations.get(i).getPopulation().get(m));
			}
			if(i+1==numOfPopulations) {
				populations.get(0).addIndividual(migrantList);

			}
			else {
				populations.get(i+1).addIndividual(migrantList);

			}
		}

	}
	
	public void createStatsFile(double[] fitness) {
		String path = "/home/yorgos/Desktop/log.txt";

		String stdDev = String.valueOf(Statistics.getStdDev(fitness));
		String mean = String.valueOf(Statistics.getMean(fitness));
		String max = String.valueOf(Statistics.getMax(fitness));

		String string_ = max + " " + mean + " " + stdDev;

		IO.appendToFile(string_, path);
	}

}
