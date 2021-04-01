package com.yu.seemovie.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.yu.seemovie.R;


public class SlideshowFragment extends Fragment {


    private LinearLayout mLayoutUserpanel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow_my, container, false);
//        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
//        final TextView textView = root.root.findViewById(R.id.text_slideshow);


        mLayoutUserpanel = root.findViewById(R.id.layout_userpanel);
        mLayoutUserpanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.userFragment);

            }
        });

        return root;
    }
}
