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
		List<HashSet<Integer>> setPairs = new ArrayList<>();

		// iterate over number of pairs
		int createdPairs = 0;
		while (createdPairs < numOfPairs) {
			// set that will include the pair
			HashSet<Integer> pair = new HashSet<>();

			// iterate over pair
			int numOfSelected = 0;
			while (numOfSelected < k) {
				// choose randomly k
				List<Integer> ids = chooseRandom(k, 0, indvs.size());

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

			// if (!setPairs.contains(pair)) {
			// setPairs.add(pair);
			// createdPairs++;
			// }
			setPairs.add(pair);
			createdPairs++;

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
	public static List<Individual> uniform(List<Individual> indvs, int numOfParents) {

		List<Integer> parentId = new ArrayList<>();
		while (parentId.size() < numOfParents) {
			int id = rand.nextInt(0, indvs.size());

			if (!parentId.contains(id)) {
				parentId.add(id);
			}
		}

		List<Individual> selected = new ArrayList<>();
		for (int id : parentId) {
			selected.add(indvs.get(id));
		}

		return selected;

	}

	// Survivor selection

	// (μ,λ), (μ+λ)
	public static List<Individual> plusStrategy(List<Individual> indvs, int mu) {
		Collections.sort(indvs, new IndividualComparator());
		return indvs.subList(0, mu);

	}

	//
	private static List<Integer> chooseRandom(int numOfParents, int origin, int bound) {
		List<Integer> parentId = new ArrayList<>();
		while (parentId.size() < numOfParents) {
			int id = rand.nextInt(origin, bound);

			if (!parentId.contains(id)) {
				parentId.add(id);
			}
		}
		return parentId;
	}
}
