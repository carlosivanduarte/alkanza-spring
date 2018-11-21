package com.alkanza.algorithm;

import java.util.Comparator;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class Algorithm implements IAlgorithm {

	@Override
	public Double process(Data data) {

		return data.getUnbalancedDistances()
				.stream()
				.map(calculateFunction(data))
				.min(new MinComparator())
				.orElse(0.0);
	}
	
	private Function<Double, Double> calculateFunction(Data data) {
		return new Function<Double, Double>() {
			@Override
			public Double apply(Double unbalancedDistance) {
				return data.getBalancedDistances().stream()
						.mapToDouble(balancedDistance -> Math.abs(unbalancedDistance - balancedDistance)).sum();
			}
		};
	}
	
	class MinComparator implements Comparator<Double> {

		@Override
		public int compare(Double o1, Double o2) {
			return o1.compareTo(o2);
		}		
	}
}