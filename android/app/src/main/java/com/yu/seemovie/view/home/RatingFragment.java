package com.yu.seemovie.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.yu.seemovie.R;
import com.yu.seemovie.dao.MovieDao;
import com.yu.seemovie.entity.Movie;
import com.yu.seemovie.rest.IMovieDataAdapter;
import com.yu.seemovie.rest.MovieRestCallback;
import com.yu.seemovie.rest.MovieRestDao;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RatingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RatingFragment extends Fragment implements IMovieDataAdapter {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView mMoivepic;
    private TextView mMoiveName;
    private TextView mTextView3;
    private RatingBar mRatingBar;
    private EditText mEditText;
    private Button mPost;

    public RatingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RatingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RatingFragment newInstance(String param1, String param2) {
        RatingFragment fragment = new RatingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private MovieRestCallback movieRestCallback;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home_moiveitem_rating, container, false);


        mMoivepic = view.findViewById(R.id.movie_pic);
        mMoiveName = view.findViewById(R.id.moive_name);
        mTextView3 = view.findViewById(R.id.tv_rating);
        mRatingBar = view.findViewById(R.id.movie_score);
        mEditText = view.findViewById(R.id.editText);
        mPost = view.findViewById(R.id.post);

        int position = getArguments().getInt("position");
//        Map movie = new MovieDao(getActivity()).getMovieById(position);

        movieRestCallback = new MovieRestCallback(this);
        MovieRestDao.getMovieById(position, movieRestCallback);



        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "已提交，谢谢评分", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigateUp();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void RefreshMovieData() {
        Movie movie=movieRestCallback.movies.get(0);

        mMoiveName.setText((String) movie.getTitle());
        mMoivepic.setImageResource(Integer.parseInt(movie.getCustompicpos()));
    }
}
