package com.example.engineerai_test.ui.user;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


public class UserFragment extends Fragment {

    public static final String TAG = "UserFragment";
    private SwipeRefreshLayout srLayout;
    private int pageNumber = 1;
    private ArrayList<UsersItem> usersItemArrayList = new ArrayList<>();
    private NoPaginate noPaginate;
    private UserAdapter userAdapter;


    private OnFragmentInteractionListener mListener;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initControls();
    }

    private void initControls() {
        srLayout = getView().findViewById(R.id.srLayout);
        RecyclerView rvUserList = getView().findViewById(R.id.rvUserList);
        userAdapter = new UserAdapter(usersItemArrayList);
        rvUserList.setAdapter(userAdapter);

        noPaginate = NoPaginate.with(rvUserList)
                .setOnLoadMoreListener(this::getUserList)
                .setCustomErrorItem(new CustomErrorItem())
                .build();

        srLayout.setOnRefreshListener(() -> {
            pageNumber = 1;
            srLayout.setRefreshing(false);
            noPaginate.setNoMoreItems(false);
            usersItemArrayList.clear();
            userAdapter.setUsersItemArrayList(usersItemArrayList);

            if (!Utility.isNetworkAvailable(requireActivity())) {
                noPaginate.showError(true);
            }

        });

    }

    public void getUserList() {
        UserListRequest userListRequest = new UserListRequest(pageNumber, USER_LIST_PAGE_LIMIT);
        User user = new User();
        noPaginate.showLoading(true);
        user.getUserList(requireActivity(), userListRequest, (isSuccess, object) -> {
            noPaginate.showLoading(false);
            if (srLayout.isRefreshing()) {
                srLayout.setRefreshing(false);
            }
            if (isSuccess) {
                Data userData = (Data) object;
                if (pageNumber == 1) {
                    usersItemArrayList.clear();
                }
                noPaginate.showError(false);
                usersItemArrayList.addAll(userData.getUsers());
                noPaginate.setNoMoreItems(!userData.isHasMore());
                userAdapter.setUsersItemArrayList(usersItemArrayList);
                pageNumber++;
            } else {
                noPaginate.showError(true);
            }
        });

    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
