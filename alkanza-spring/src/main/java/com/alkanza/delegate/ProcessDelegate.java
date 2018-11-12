package com.alkanza.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkanza.model.rest.PlacesRequest;
import com.alkanza.model.rest.Response;
import com.alkanza.repository.ResultRepository;

import static com.alkanza.model.rest.Response.OK;

@Service
public class ProcessDelegate {

	private ResultRepository resultRepository;
	
	@Autowired
	public ProcessDelegate(ResultRepository resultRepository) {
		this.resultRepository = resultRepository;
	}
	
	public Response process(PlacesRequest placesRequest) {
		return OK;
	}
}
