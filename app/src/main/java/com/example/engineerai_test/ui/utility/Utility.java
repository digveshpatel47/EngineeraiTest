package com.example.engineerai_test.ui.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.engineerai_test.R;

public class Utility {

    private Utility() {//Private constructor
    }


    /**
     * checks network availability and shows toast if not available
     *
     * @param context current context
     * @return true if available, false otherwise
     */
    public static boolean isNetworkAvailable(final Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return (networkInfo != null) && networkInfo.isConnected();
        }
        return false;
    }



    public static void loadCircularImage(ImageView view, String imageUrl) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(view.getContext());
        circularProgressDrawable.setStrokeWidth(2f);
        circularProgressDrawable.setCenterRadius(20f);
        circularProgressDrawable.setColorSchemeColors(R.color.colorAccent);
        circularProgressDrawable.start();
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().circleCrop())
                .placeholder(circularProgressDrawable)
                .into(view);
    }

    public static void loadImage(ImageView view, String imageUrl) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(view.getContext());
        circularProgressDrawable.setStrokeWidth(3f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.setColorSchemeColors(R.color.colorAccent);
        circularProgressDrawable.start();

        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(circularProgressDrawable)
                .into(view);
    }

}