package com.yu.seemovie.view.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yu.seemovie.R;
import com.yu.seemovie.rest.IMovieDataAdapter;
import com.yu.seemovie.entity.Movie;
import com.yu.seemovie.rest.MovieRestCallback;
import com.yu.seemovie.rest.MovieRestDao;

//public class LastestMovieRecycleViewFragment extends Fragment {
//
//    private RecyclerView mRecycleview;
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.recycleview, container, false);
//
//
//        mRecycleview = view.findViewById(R.id.recycleview);
//        mRecycleview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
//        mRecycleview.setAdapter(new MyRecycleViewAdapter(getContext()));
//
//        return view;
//    }
//
//}

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> implements IMovieDataAdapter {


//    List<Map<String, Object>> movies;
//    List<Movie> movies;

    //    MovieDAO moviedao;
    MovieRestCallback movieRestCallback;
    Context context;

    MyRecycleViewAdapter(Context context) {
        this.context = context;
//        movies = new MovieDAODB(context).getLastestMovies(5);
//        movies=new Vector<Movie>();
//         moviedao = new MovieDAO();
        movieRestCallback = new MovieRestCallback(this);
        MovieRestDao.getLastestMovie(5, movieRestCallback);
//        movies=movieload.movies;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_home_moivelist_item, viewGroup, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {

        Movie movie = movieRestCallback.movies.get(position);
        holder.mImageView4.setImageResource(R.drawable.cover__1_);
        Glide.with(context)
                .load(MovieRestDao.rooturl + "upload/movie/cover/cover" + movie.getId() + ".jpg")
                .into(holder.mImageView4);
        holder.mTextView4.setText(movie.getTitle());
        final int id = movie.getId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(IndexFragment.activity, R.id.nav_host_fragment);
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                navController.navigate(R.id.detailFragment, bundle);

            }
        });
    }

    public int getItemCount() {
        //  2020/6/29 已解决

        return movieRestCallback.movies.size();
    }

    @Override
    public void RefreshMovieData() {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView mImageView4;
        private TextView mTextView4;
        private TextView mMovieTitle;

        public ViewHolder(View convertView) {
            super(convertView);

            mImageView4 = convertView.findViewById(R.id.movie_pic);
            mTextView4 = convertView.findViewById(R.id.movie_title);


        }
    }
}