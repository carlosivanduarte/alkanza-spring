package com.alkanza.delegate;

import java.util.List;

import com.alkanza.model.PlacesRequest;
import com.alkanza.model.Point;
import com.alkanza.model.ProcessResult;

public interface IProcessDelegate {
	
	List<ProcessResult> process(PlacesRequest placesRequest);
	
	List<Point> getBalancedPoints(PlacesRequest placesRequest);
}
