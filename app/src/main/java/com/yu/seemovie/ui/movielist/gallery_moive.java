package com.yu.seemovie.ui.movielist;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yu.seemovie.DAO.MovieDAO;
import com.yu.seemovie.R;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link gallery_moive#newInstance} factory method to
 * create an instance of this fragment.
 */
public class gallery_moive extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mRecycleview;

    public gallery_moive() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment gallery_moive.
     */
    // TODO: Rename and change types and number of parameters
    public static gallery_moive newInstance(String param1, String param2) {
        gallery_moive fragment = new gallery_moive();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycleviewwithswiprefresh, container, false);
        mRecycleview = view.findViewById(R.id.recyclerview);
        mRecycleview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mRecycleview.setAdapter(new gallery_moive_recycle_view(getActivity()));


        return view;

    }


    class gallery_moive_recycle_view extends RecyclerView.Adapter<gallery_moive_recycle_view.viewholder> {

        Activity activity;

        public gallery_moive_recycle_view(Activity activity) {
            super();
            this.activity = activity;
        }

        @NonNull
        @Override
        public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_gallery_moivelist_item, null);

            return new viewholder(v);
        }


        @Override
        public void onBindViewHolder(@NonNull final viewholder holder, final int position) {


            Map movie = MovieDAO.movies.get(position);
            holder.mMoivepic.setImageResource((int) movie.get("img"));
            holder.mMoiveName.setText((String) movie.get("text"));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", position);
                    bundle.putInt("mode", 1);
                    navController.navigate(R.id.detailFragment, bundle);

                }
            });
        }


        @Override
        public int getItemCount() {
            return new MovieDAO(getActivity()).movies.size();
        }

        public class viewholder extends RecyclerView.ViewHolder {

            public ImageView mMoivepic;
            public TextView mMoiveName;
            public TextView mTextView5;
            public TextView mTextView6;
            public TextView mTextView7;
            public TextView mTextView10;

            public viewholder(@NonNull View itemView) {
                super(itemView);

                mMoivepic = itemView.findViewById(R.id.moviepic);
                mMoiveName = itemView.findViewById(R.id.moive_name);
                mTextView5 = itemView.findViewById(R.id.textView5);
                mTextView6 = itemView.findViewById(R.id.textView6);
                mTextView7 = itemView.findViewById(R.id.textView7);
                mTextView10 = itemView.findViewById(R.id.textView10);


            }

        }
    }

}


