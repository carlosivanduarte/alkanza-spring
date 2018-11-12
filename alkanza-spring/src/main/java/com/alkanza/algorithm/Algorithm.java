package com.alkanza.algorithm;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.alkanza.model.Point;

@Component
public class Algorithm implements IAlgorithm {

	@Override
	public Double process(Data data) {
		Double measure = new Double(0.0);
		data.getUnbalancedPoints().forEach(new Consumer<Point>() {
			public void accept(Point unbalancedPoint) {
				data.getBalancedPoints().forEach(new Consumer<Point>() {
					public void accept(Point balancedPoint) {
						
					}
				});
			}
		});
		return null;
	}	
}
