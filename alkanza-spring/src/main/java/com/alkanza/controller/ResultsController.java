package com.alkanza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alkanza.delegate.ProcessDelegate;
import com.alkanza.model.rest.PlacesRequest;
import com.alkanza.model.rest.ProcessResponse;

@RestController
public class ResultsController {
	
	private ProcessDelegate processDelegate;
	
	@Autowired
	public ResultsController(ProcessDelegate processDelegate) {
		this.processDelegate = processDelegate;
	}
	@PostMapping("/process")
	public ProcessResponse process(@RequestBody PlacesRequest placesRequest) {
		return new ProcessResponse(processDelegate.process(placesRequest));
	}
}
