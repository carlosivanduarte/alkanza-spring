package com.alkanza.algorithm;

import java.util.List;

import com.alkanza.model.Point;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public interface IAlgorithm {
	
	Double process(Data data);
}

@AllArgsConstructor
@Getter
@Setter
class Data {
	private List<Point> unbalancedPoints;
	private List<Point> balancedPoints;
}
