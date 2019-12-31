package com.example.engineerai_test.data.user.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Data implements Serializable {

	@SerializedName("users")
	private List<UsersItem> users;

	@SerializedName("has_more")
	private boolean hasMore;

	public void setUsers(List<UsersItem> users){
		this.users = users;
	}

	public List<UsersItem> getUsers(){
		return users;
	}

	public void setHasMore(boolean hasMore){
		this.hasMore = hasMore;
	}

	public boolean isHasMore(){
		return hasMore;
	}
}