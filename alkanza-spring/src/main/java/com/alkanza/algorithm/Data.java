package com.alkanza.algorithm;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Data {
	private List<Double> unbalancedDistances;
	private List<Double> balancedDistances;
}
