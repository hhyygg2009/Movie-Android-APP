package com.yu.seemovie.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.yu.seemovie.DAO.staticdata;
import com.yu.seemovie.R;

import java.util.List;

public class HomeFragment extends Fragment {

    static Activity activity;
    private HomeViewModel homeViewModel;
    private ViewPager mHomeBanner;
    private TextView mTextView2;
    private RecyclerView mHomeMoiveList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
////                textView.setText(s);
//            }
//        });

        activity = getActivity();
        mHomeBanner = root.findViewById(R.id.home_banner);
//        mHomeMoiveList = root.findViewById(R.id.home_moive_list);
//        mTextView2 = getView().findViewById(R.id.textView2);
//        mHomeMoiveList = getView().findViewById(R.id.home_moive_list);


        initView();


        return root;

    }


    void initView() {
        mHomeBanner.setAdapter(new bannerAdapter());


    }


    class bannerAdapter extends PagerAdapter {


        private List<Integer> banners = staticdata.banners;

//        private ImageView mHomebanner;


        @Override
        public int getCount() {
            return banners.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {

            ImageView imageView = new ImageView(container.getContext());
            imageView.setImageResource(banners.get(position));
            container.addView(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("title", "moive");
                    bundle.putInt("pic", banners.get(position));
                    Navigation.findNavController(getView()).navigate(R.id.bannerDetail, bundle);
                }
            });
            return imageView;

        }
    }

}
