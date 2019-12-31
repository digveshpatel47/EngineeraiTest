package com.example.engineerai_test.data.user.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Response implements Serializable {

	@SerializedName("status")
	private boolean status;

	@SerializedName("message")
	private String message;

	@SerializedName("data")
	private Data data;

	public boolean isStatus(){
		return status;
	}


	public String getMessage(){
		return message;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}
}