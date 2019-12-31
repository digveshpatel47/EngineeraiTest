package com.example.engineerai_test.ui.user;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.engineerai_test.R;
import com.example.engineerai_test.data.user.User;
import com.example.engineerai_test.data.user.request.UserListRequest;
import com.example.engineerai_test.data.user.response.Data;
import com.example.engineerai_test.data.user.response.UsersItem;
import com.example.engineerai_test.ui.utility.CustomErrorItem;
import com.example.engineerai_test.ui.utility.Utility;

import java.util.ArrayList;

import ru.alexbykov.nopaginate.paginate.NoPaginate;

import static com.example.engineerai_test.data.user.User.USER_LIST_PAGE_LIMIT;

public class UserActivity extends AppCompatActivity {

    private SwipeRefreshLayout srLayout;
    private int pageNumber = 1;
    private ArrayList<UsersItem> usersItemArrayList =  new ArrayList<>();
    private NoPaginate noPaginate;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initControls();
    }

    private void initControls() {
        srLayout = findViewById(R.id.srLayout);
        RecyclerView rvUserList = findViewById(R.id.rvUserList);
        userAdapter =  new UserAdapter(usersItemArrayList);
        rvUserList.setAdapter(userAdapter);

        noPaginate = NoPaginate.with(rvUserList)
                .setOnLoadMoreListener(this::getUserList)
                .setCustomErrorItem(new CustomErrorItem())
                .build();

        srLayout.setOnRefreshListener(() -> {
            if(Utility.isNetworkAvailable(UserActivity.this)){
                pageNumber = 1;
                srLayout.setRefreshing(false);
                noPaginate.setNoMoreItems(false);
                usersItemArrayList.clear();
                userAdapter.setUsersItemArrayList(usersItemArrayList);
            }else{
                pageNumber = 1;
                srLayout.setRefreshing(false);
                noPaginate.showError(true);
                usersItemArrayList.clear();
                userAdapter.setUsersItemArrayList(usersItemArrayList);
            }
        });

    }

    public void getUserList(){
        UserListRequest userListRequest  = new UserListRequest(pageNumber, USER_LIST_PAGE_LIMIT);
        User user =  new User();
        noPaginate.showLoading(true);
        user.getUserList(UserActivity.this,userListRequest, (isSuccess, object) -> {
            noPaginate.showLoading(false);
            if(srLayout.isRefreshing()){
                srLayout.setRefreshing(false);
            }
            if(isSuccess){
                Data userData = (Data) object;
                if(pageNumber == 1){
                    usersItemArrayList.clear();
                }
                noPaginate.showError(false);
                usersItemArrayList.addAll(userData.getUsers());
                noPaginate.setNoMoreItems(!userData.isHasMore());
                userAdapter.setUsersItemArrayList(usersItemArrayList);
                pageNumber ++;
            }else{
                noPaginate.showError(true);
            }
        });

    }
}
