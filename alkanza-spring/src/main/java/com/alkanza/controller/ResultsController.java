package com.alkanza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alkanza.delegate.IProcessDelegate;
import com.alkanza.model.PlacesRequest;
import com.alkanza.model.ProcessResponse;

@RestController
public class ResultsController {
	
	private IProcessDelegate processDelegate;
	
	@Autowired
	public ResultsController(IProcessDelegate processDelegate) {
		this.processDelegate = processDelegate;
	}
	@PostMapping("/process")
	public ProcessResponse process(@RequestBody PlacesRequest placesRequest) {
		return new ProcessResponse(processDelegate.process(placesRequest));
	}
}
