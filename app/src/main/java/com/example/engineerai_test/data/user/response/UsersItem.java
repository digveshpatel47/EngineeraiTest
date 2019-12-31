package com.example.engineerai_test.data.user.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class UsersItem implements Serializable {

	@SerializedName("name")
	private String name;

	@SerializedName("image")
	private String image;

	@SerializedName("items")
	private List<String> items;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setItems(List<String> items){
		this.items = items;
	}

	public List<String> getItems(){
		return items;
	}
}