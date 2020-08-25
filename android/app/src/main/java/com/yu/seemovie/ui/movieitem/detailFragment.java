package com.yu.seemovie.ui.movieitem;

import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.bumptech.glide.Glide;
import com.yu.seemovie.dao.IMovieDataLoad;
import com.yu.seemovie.dao.Movie;
import com.yu.seemovie.dao.MovieDAO;
import com.yu.seemovie.dao.MovieDataManage;
import com.yu.seemovie.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link detailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detailFragment extends Fragment implements IMovieDataLoad {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public static int READ = 0, WRITE = 1, ADD = 3, ALTER = 4, DEL = 5;
    // TODO: Rename and change types of parameters
//    Map movie;
    MovieDAO moviedao;

    int position;
    private ImageView mMoivepic;
    private TextView mMoiveName;
    private TextView mRatingBar;
    private Button mWant;
    private Button mRate;

    Movie movie;
    private TextView mMovieArea;
    private TextView mMovieReleasetime;
    private TextView mMovieStory;
    private TextView mMovieCategory;

    public detailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static detailFragment newInstance(String param1, String param2) {
        detailFragment fragment = new detailFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //这里设置另外的menu

//        menu.clear();

        super.onCreateOptionsMenu(menu, inflater);

        int group1 = 1;

        menu.add(group1, 1, 1, "增加新记录");
        menu.add(group1, 2, 2, "修改此页记录");
        menu.add(group1, 3, 3, "删除此页记录");


//        inflater.inflate(R.menu.moviemenu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Bundle bundle = new Bundle();
        int mode = 0;
        if (item.getItemId() == R.id.add)
            mode = ADD;
        if (item.getItemId() == R.id.change)
            mode = ALTER;
        if (item.getItemId() == R.id.del)
            mode = DEL;

//            LayoutInflater layoutInflater = getLayoutInflater();
//            View view = layoutInflater.inflate(R.layout.add_moive,null);

        bundle.putInt("mode", mode);
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.addMoiveFragment, bundle);


        return super.onOptionsItemSelected(item);
    }

    private MovieDataManage movieDataManage;

    public void RefreshMovieData() {
        movie = movieDataManage.movies.get(0);
        mMoiveName.setText(movie.getTitle());
        mMoivepic.setImageResource(R.drawable.cover__1_);
        Glide.with(this)
                .load(MovieDAO.rooturl + "upload/movie/cover/cover" + movie.getId() + ".jpg")
                .into(mMoivepic);
        mRatingBar.setText(movie.getScore());
        mMovieCategory.setText(movie.getClassid());
        mMovieStory.setText(movie.getStory());
        mMovieArea.setText(movie.getRegionid() + movie.getDuration());
        mMovieReleasetime.setText(movie.getReleasetime());


//        mRatingBar.setRating(new Random().nextFloat() * 5);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home_moiveitem_detail, container, false);
        if (getArguments() != null) {
//            moviedao=new MovieDAO(null);
//            moviedao.MovieLoadById(getArguments().getInt("id"),this);
            movieDataManage = new MovieDataManage(this);
            MovieDAO.getMovieById(getArguments().getInt("id"), movieDataManage);


//            movie = new MovieDAODB(getContext()).getMovieById(getArguments().getInt("id"));


        }


        mMoivepic = view.findViewById(R.id.movie_pic);
        mMoiveName = view.findViewById(R.id.moive_name);
        mRatingBar = view.findViewById(R.id.movie_score);

        mWant = view.findViewById(R.id.btn_want);
        mRate = view.findViewById(R.id.btn_rate);

        mMovieArea = view.findViewById(R.id.movie_area);
        mMovieReleasetime = view.findViewById(R.id.movie_releasetime);
        mMovieStory = view.findViewById(R.id.movie_story);
        mMovieCategory = view.findViewById(R.id.movie_category);

        mRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                Navigation.findNavController(view).navigate(R.id.ratingFragment, bundle);
            }
        });

        mWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWant.getText().equals("想看")) {
                    Toast.makeText(getActivity(), "已加入想看", Toast.LENGTH_SHORT).show();
                    mWant.setText("已想看");
                } else {
                    Toast.makeText(getActivity(), "已取消", Toast.LENGTH_SHORT).show();
                    mWant.setText("想看");
                }

            }
        });


        return view;


    }

}
