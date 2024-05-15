package com.testsdemo.testcrud.dto;

public class ResponseDto {
    public boolean status;
    public String message;
    public Object data;
    
    public ResponseDto() {
    	
    }
    
    public ResponseDto(boolean status , String message , Object data){
    	this.status = status;
    	this.message = message;
    	this.data = data;
    }
    
    public ResponseDto(boolean status , String message ){
    	this.status = status;
    	this.message = message;
    	this.data = null;
    }
}
