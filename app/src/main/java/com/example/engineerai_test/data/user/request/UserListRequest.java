package com.example.engineerai_test.data.user.request;

public class UserListRequest {

    private int pageNo;
    private int limit;

    public UserListRequest(int pageNo, int limit) {
        this.pageNo = pageNo;
        this.limit = limit;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getLimit() {
        return limit;
    }

}


