package utils;

import evolutionary.Mutation.MutationType;
import strategy.EvolutionaryStrategy;
import strategy.EvolutionaryStrategyKatsuura;
import strategy.EvolutionaryStrategyMultimodal;
import strategy.EvolutionaryStrategyUnimodal;

import org.vu.contest.ContestEvaluation;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Tuner {

	public static void bentCigar(ContestEvaluation evaluation) {
		Random rnd_ = new Random();
		rnd_.setSeed(1);

		double bestScore = -1;
		int bestPopSize = 0;
		int bestMutSize = 0;

		for (int popSize = 5; popSize < 50; popSize++) {
			// greed search for the naive approach
			for (int mutSize = 1; mutSize < popSize; mutSize++) {

				// // greed search for the tournament selection
				// int mutSize = popSize * 3;
				Properties props = evaluation.getProperties();
				int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));

				// init
				EvolutionaryStrategy strategy = new EvolutionaryStrategyUnimodal(1, popSize, evaluations_limit_,
						MutationType.UNCORRELATED, evaluation, rnd_);

				// evolve population
				try {
					strategy.evolve(mutSize, mutSize, 1);
				} catch (Exception e) {
					System.out.println(e.toString());
					System.out.println(evaluation.getFinalResult());
				}
				double bestCurrentScore = evaluation.getFinalResult();
				System.out.println("Best result " + popSize + ": " + bestCurrentScore);

				if (bestCurrentScore > bestScore) {
					bestScore = bestCurrentScore;
					bestPopSize = popSize;
					bestMutSize = mutSize;
				}
			}
		}

		System.out.println("Pop: " + bestPopSize + " Mut: " + bestMutSize + " Score:" + bestScore);
	}

	public static void schaffers(ContestEvaluation evaluation) {

		Random rnd_ = new Random();
		rnd_.setSeed(1);

		// find best setup for the best evolutionary strategy
		double bestScore = -1;
		int bestPopSize = 0;
		int bestMutSize = 0;

		List<Integer> count30 = new ArrayList<Integer>();
		List<Integer> countOver20 = new ArrayList<Integer>();

		for (int popSize = 30; popSize < 60; popSize++) {
			int mutSize = popSize * 3;
			int count = 0;
			for (int i = 0; i < 30; i++) {

				Properties props = evaluation.getProperties();
				int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));

				EvolutionaryStrategy strategy = new EvolutionaryStrategyMultimodal(1, popSize, evaluations_limit_,
						MutationType.UNCORRELATED_N, evaluation, rnd_);

				try {

					strategy.evolve(mutSize, mutSize, 3);
				} catch (Exception e) {
					System.out.println(e.toString());
					System.out.println(evaluation.getFinalResult());
				}

				double bestCurrentScore = evaluation.getFinalResult();

				if (bestCurrentScore == 10.0) {
					count++;
				}
				if (bestCurrentScore > bestScore) {
					bestScore = bestCurrentScore;
					bestPopSize = popSize;
					bestMutSize = mutSize;
				}
			}
			if (count == 30) {
				count30.add(popSize);
			} else if (count > 20 && count < 30) {
				countOver20.add(popSize);
			}
		}

		System.out.println("Count 30" + count30.toString());
		System.out.println("Count 20" + countOver20.toString());

	}

	public static void katsuura(ContestEvaluation evaluation) {
		Random rnd_ = new Random();
		rnd_.setSeed(1);

		// Properties props = evaluation.getProperties();
		// int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
		//
		// EvolutionaryStrategy strategy = new EvolutionaryStrategyKatsuura(1, 50,
		// evaluations_limit_,
		// MutationType.UNCORRELATED_N, evaluation);
		// strategy.evolve(41, 41, 2);
		// System.out.println(evaluation.getFinalResult());
		// strategy.evolve(mutSize, mutSize, 3);

		double bestScore = -1;
		int bestPopSize = 0;
		int bestMutSize = 0;

		for (int popSize = 30; popSize < 40; popSize++) {
			// greed search for the naive approach
			int mutSize = popSize * 3;
			double totalScore = 0;
			for (int i = 0; i < 5; i++) {

				Properties props = evaluation.getProperties();
				int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));

				EvolutionaryStrategy strategy = new EvolutionaryStrategyKatsuura(5, popSize, evaluations_limit_,
						MutationType.UNCORRELATED_N, evaluation, rnd_);
				strategy.evolve(mutSize, mutSize, 1);

				System.out.println("Best result: " + evaluation.getFinalResult());
				double bestCurrentScore = evaluation.getFinalResult();
				System.out.println("Best result " + popSize + ": " + bestCurrentScore);
				totalScore += bestCurrentScore;

			}
			totalScore = totalScore / 5;
			if (totalScore > bestScore) {
				bestScore = totalScore;
				bestPopSize = popSize;
				bestMutSize = mutSize;
			}
		}

		System.out.println("Pop: " + bestPopSize + " Mut: " + bestMutSize + " Score:" + bestScore);

	}

}
