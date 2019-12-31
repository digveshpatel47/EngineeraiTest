package com.example.engineerai_test.ui.user;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
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

public class UserActivity extends AppCompatActivity implements UserFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, UserFragment.newInstance());
        transaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
