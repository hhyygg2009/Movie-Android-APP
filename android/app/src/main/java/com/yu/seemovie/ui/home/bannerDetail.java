package com.yu.seemovie.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.yu.seemovie.R;

public class bannerDetail extends Fragment {
    private ImageView mAppBarImage;
    private Toolbar mToolbar;
    private AppBarLayout mAppbar;
    private TextView mTextView28;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_banner_detail, container, false);

        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        int pic = bundle.getInt("pic");


        mAppBarImage = view.findViewById(R.id.app_bar_image);
        mToolbar = view.findViewById(R.id.toolbar);
        mAppbar = view.findViewById(R.id.appbar);

        mToolbar.setTitle(title);
        mAppBarImage.setImageResource(pic);

        mTextView28 = view.findViewById(R.id.story);
        mTextView28.setText("美丽的云顶村，热情为民的年轻村长方春天，在筹备莅临本村的一场国际山地马拉松分站赛过程中，遇到...");

        return view;
    }
}
