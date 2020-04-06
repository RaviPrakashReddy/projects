package com.candela.forte.integration.db.model;

import lombok.Data;

@Data
public class RestResponse {

	private int status=0;
	private Object response;
	private String errorMessage;

}
