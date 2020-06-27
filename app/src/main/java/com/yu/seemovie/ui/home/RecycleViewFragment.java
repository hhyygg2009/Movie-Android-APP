package com.yu.seemovie.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yu.seemovie.DAO.MovieDAO;
import com.yu.seemovie.R;

import java.util.Map;

public class RecycleViewFragment extends Fragment {
    private ImageView mMoivepic;
    private TextView mMoiveName;
    private RatingBar mRatingBar;
    private TextView mTextView5;
    private TextView mTextView6;
    private TextView mTextView7;
    private Button mWant;
    private Button mRate;
    private TextView mTextView8;
    private TextView mTextView9;
    private TextView mInfo;
    private TextView mTextView11;
    private RecyclerView mRecycleview;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycleview, container, false);


        mRecycleview = view.findViewById(R.id.recycleview);
        mRecycleview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        mRecycleview.setAdapter(new MyRecycleViewAdapter());

        return view;
    }
}

class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {


    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_home_moivelist_item, viewGroup, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        Map movie = MovieDAO.movies.get(position);
        holder.mImageView4.setImageResource((int) movie.get("img"));
        holder.mTextView4.setText((String) movie.get("text"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(HomeFragment.activity, R.id.nav_host_fragment);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                navController.navigate(R.id.detailFragment, bundle);

            }
        });
    }

    public int getItemCount() {
        return MovieDAO.movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView mImageView4;
        private TextView mTextView4;

        public ViewHolder(View convertView) {
            super(convertView);

            mImageView4 = convertView.findViewById(R.id.imageView4);
            mTextView4 = convertView.findViewById(R.id.textView4);
        }
    }
}