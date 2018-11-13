package com.alkanza.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProcessResponse {
	
	private List<ProcessResult> processResult;
}
