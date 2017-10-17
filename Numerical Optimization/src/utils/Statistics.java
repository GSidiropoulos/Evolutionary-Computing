package utils;

import java.util.Arrays;

public class Statistics {

	public static double getMean(double[] data) {
		int size = data.length;
		double sum = 0.0;

		for (double a : data) {
			sum += a;
		}

		return sum / size;
	}

	public static double getVariance(double[] data) {
		int size = data.length;
		double mean = getMean(data);
		double temp = 0;

		for (double a : data) {
			temp += (a - mean) * (a - mean);
		}
		return temp / (size - 1);
	}

	public static double getStdDev(double[] data) {
		return Math.sqrt(getVariance(data));
	}

	public static double median(double[] data) {
		Arrays.sort(data);

		if (data.length % 2 == 0) {
			return (data[(data.length / 2) - 1] + data[data.length / 2]) / 2.0;
		}
		return data[data.length / 2];
	}

	public static double getMax(double[] data) {
		double maxValue;
		maxValue = -1;

		for (int i = 0; i < data.length; i++) {
			if (data[i] > maxValue) {
				maxValue = data[i];
			}
		}

		return maxValue;
	}
}
