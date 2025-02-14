package com.dhoopwale.response;


public class ApiResponse {
    private String message;
    private Boolean status;

    public ApiResponse() {
    	
    }

    public ApiResponse(String message, Boolean status) {
    	super();
        this.status=status;
        this.message=message;
    }


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}

    



	
}