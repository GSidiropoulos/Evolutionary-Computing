package evolutionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import utils.IndividualComparator;

public class Selection {

	static ThreadLocalRandom rand = ThreadLocalRandom.current();
	static Population population;

	// Parent selection

	// tournament selection
	public static List<List<Individual>> tournament(List<Individual> indvs, int k, int numOfPairs) {
		// set of pairs
		HashSet<HashSet<Integer>> setPairs = new HashSet<>();

		// iterate over number of pairs
		int createdPairs = 0;
		while (createdPairs < numOfPairs) {
			// set that will include the pair
			HashSet<Integer> pair = new HashSet<>();

			// iterate over pair
			int numOfSelected = 0;
			while (numOfSelected < k) {
				// choose randomly k
				int[] ids = rand.ints(k, 0, indvs.size()).distinct().toArray();

				// get the fittest
				int selected = -1;
				for (int i : ids) {
					if (selected == -1) {
						selected = i;
					} else if (indvs.get(selected).getFitness() < indvs.get(i).getFitness()) {
						selected = i;
					}
				}
				if (!pair.contains(selected)) {
					pair.add(selected);
					numOfSelected++;
				}
			}

			if (!setPairs.contains(pair)) {
				setPairs.add(pair);
				createdPairs++;
			}

		}

		// ------- HashSet<HashSet<Integer>> transform to List<List<Individual>>
		// ---------

		// set of pairs
		List<List<Individual>> listPairs = new ArrayList<>();

		for (HashSet<Integer> pair : setPairs) {
			List<Individual> parents = new ArrayList<>();
			for (int p : pair) {
				parents.add(indvs.get(p));
			}
			listPairs.add(parents);
		}
		return listPairs;
	}

	// select uniformly randomly
	public static List<Integer> uniform(int popSize, int numOfParents) {

		List<Integer> parentId = new ArrayList<Integer>();
		for (int i = 0; i < numOfParents; i++) {
			parentId.add(rand.nextInt(0, popSize));// SELECT DISTINCT
		}

		return parentId;

	}

	// Survivor selection

	// (μ,λ)
	public static List<Individual> commaStrategy(List<Individual> indvs, int mu) {
		Collections.sort(indvs, new IndividualComparator());
		return indvs.subList(0, mu - 1);

	}

	public static List<Individual> plusStrategy(List<Individual> indvs, int mu) {
		Collections.sort(indvs, new IndividualComparator());
		return indvs.subList(0, mu);

	}
}
