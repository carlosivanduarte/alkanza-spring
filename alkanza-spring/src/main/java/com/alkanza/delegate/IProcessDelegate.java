package com.alkanza.delegate;

import com.alkanza.model.PlacesRequest;
import com.alkanza.model.Response;

public interface IProcessDelegate {
	
	Response process(PlacesRequest placesRequest);
}
