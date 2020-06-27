package com.yu.seemovie.ui.movielist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yu.seemovie.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    List titles;
    List fragements;
    private GalleryViewModel galleryViewModel;
    private TextView mTextView13;
    private ViewPager mGalleryViewpager;
    private TabLayout mTablayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });


        mTextView13 = root.findViewById(R.id.textView13);
        mTablayout = root.findViewById(R.id.tablayout);
        mGalleryViewpager = root.findViewById(R.id.gallery_viewpager);

        fragements = new ArrayList<Fragment>();
        fragements.add(new gallery_moive());

        titles = new ArrayList<>();
        titles.add("热映");
        titles.add("待映");
//        titles.add("Three");
        FragmentPagerAdapter fragmentPagerAdapter = new fragmentPagerAdapter(getChildFragmentManager(), fragements, titles);

        mGalleryViewpager.setAdapter(fragmentPagerAdapter);
        mTablayout.setupWithViewPager(mGalleryViewpager);
        return root;

    }
}

class fragmentPagerAdapter extends FragmentPagerAdapter {
    List fragment;
    List title;

    public fragmentPagerAdapter(@NonNull FragmentManager fm, List fragment, List title) {
        super(fm);
        this.fragment = fragment;
        this.title = title;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return (Fragment) fragment.get(position);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (String) title.get(position);
    }
}
