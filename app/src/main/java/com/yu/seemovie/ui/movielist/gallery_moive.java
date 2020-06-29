package com.yu.seemovie.ui.movielist;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.yu.seemovie.DAO.MovieDAO;
import com.yu.seemovie.R;

import java.util.List;
import java.util.Map;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_SETTLING;

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
    private SwipeRefreshLayout mSwiperefresh;
    private int start;
    private int count;
    private List<Map<String, Object>> movies;
    private boolean havenewmovie;

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

    private galleryMoiverecycleviewAdapter gallery_moiverecycleviewadapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycleviewwithswiprefresh, container, false);
        mRecycleview = view.findViewById(R.id.recyclerview);
        mRecycleview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        gallery_moiverecycleviewadapter = new galleryMoiverecycleviewAdapter(getActivity());
        mRecycleview.setAdapter(gallery_moiverecycleviewadapter);

        mSwiperefresh = view.findViewById(R.id.swiperefresh);
        mSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                start = 0;
                movies.clear();
                movies = new MovieDAO(getContext()).getSectionMovie(start, count);
                start = movies.size();
                gallery_moiverecycleviewadapter.notifyDataSetChanged();
                mSwiperefresh.setRefreshing(false);
            }
        });
        mRecycleview.setOnScrollListener(new onLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                loadmore();
            }
        });


        return view;

    }


    public void loadmore() {
        List<Map<String, Object>> newmovies = new MovieDAO(getContext()).getSectionMovie(start, count);
        if (newmovies.size() == 0)
            havenewmovie = false;
        else {
            movies.addAll(newmovies);
            start = movies.size();
            gallery_moiverecycleviewadapter.notifyDataSetChanged();
            havenewmovie = true;
        }


    }


    class galleryMoiverecycleviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        Activity activity;


        private final static int TYPE_CONTENT = 0;//正常内容
        private final static int TYPE_FOOTER = 1;//下拉刷新

        public galleryMoiverecycleviewAdapter(Activity activity) {
            super();
            this.activity = activity;
            start = 0;
            count = 4;
            movies = new MovieDAO(activity).getSectionMovie(start, count);
            start = movies.size();

        }

        @Override
        public int getItemViewType(int position) {
            if (position == movies.size()) {
                return TYPE_FOOTER;
            }
            return TYPE_CONTENT;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_gallery_moivelist_item, null);
//
//            return new movieItemViewholder(v);

            if (viewType == TYPE_FOOTER) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_gallery_moivelist_item_foot, parent, false);
                return new FootViewHolder(view);
            } else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_gallery_moivelist_item, null);
                return new movieItemViewholder(view);
            }

        }


        @Override
        public int getItemCount() {
            return movies.size() + 1;
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewholder, final int position) {
            if (getItemViewType(position) == TYPE_FOOTER) {
                FootViewHolder holder = (FootViewHolder) viewholder;
                if (havenewmovie) {
                    holder.mLoadcomplere.setVisibility(LinearLayout.VISIBLE);
                    holder.mLoadmore.setVisibility(LinearLayout.INVISIBLE);
                } else {
                    holder.mLoadcomplere.setVisibility(LinearLayout.INVISIBLE);
                    holder.mLoadmore.setVisibility(LinearLayout.VISIBLE);
                }


            } else {

                final Map movie = movies.get(position);
                movieItemViewholder holder = (movieItemViewholder) viewholder;
                holder.mMoivepic.setImageResource((int) movie.get("img"));
                holder.mMoiveName.setText((String) movie.get("text"));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", (int) movie.get("id"));
//                    bundle.putInt("mode", 1);
                        navController.navigate(R.id.detailFragment, bundle);

                    }
                });
            }
        }

        public class movieItemViewholder extends RecyclerView.ViewHolder {

            public ImageView mMoivepic;
            public TextView mMoiveName;
            public TextView mTextView5;
            public TextView mTextView6;
            public TextView mTextView7;
            public TextView mTextView10;

            public movieItemViewholder(@NonNull View itemView) {
                super(itemView);

                mMoivepic = itemView.findViewById(R.id.moviepic);
                mMoiveName = itemView.findViewById(R.id.moive_name);
                mTextView5 = itemView.findViewById(R.id.textView5);
                mTextView6 = itemView.findViewById(R.id.textView6);
                mTextView7 = itemView.findViewById(R.id.textView7);
                mTextView10 = itemView.findViewById(R.id.textView10);


            }

        }

        private class FootViewHolder extends RecyclerView.ViewHolder {
            private ProgressBar mProgressBar;

            private LinearLayout mLoadmore;

            private LinearLayout mLoadcomplere;

            public FootViewHolder(View view) {
                super(view);

                mProgressBar = view.findViewById(R.id.progressBar);


                mLoadmore = view.findViewById(R.id.loadmore);

                mLoadcomplere = view.findViewById(R.id.loadcomplere);
            }
        }
    }

}


abstract class onLoadMoreListener extends RecyclerView.OnScrollListener {
    private int countItem;
    private int lastItem;
    private boolean isScolled = false;//是否可以滑动
    private RecyclerView.LayoutManager layoutManager;

    /**
     * 加载回调方法
     *
     * @param countItem 总数量
     * @param lastItem  最后显示的position
     */
    protected abstract void onLoading(int countItem, int lastItem);

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
    /* 测试这三个参数的作用
       if (newState==SCROLL_STATE_IDLE){
           Log.d("test","SCROLL_STATE_IDLE,空闲");
       }
       else if (newState==SCROLL_STATE_DRAGGING){
           Log.d("test","SCROLL_STATE_DRAGGING,拖拽");
       }
       else if (newState==SCROLL_STATE_SETTLING){
           Log.d("test","SCROLL_STATE_SETTLING,固定");
       }
       else{
           Log.d("test","其它");
       }*/
        //拖拽或者惯性滑动时isScolled设置为true
        if (newState == SCROLL_STATE_DRAGGING || newState == SCROLL_STATE_SETTLING) {
            isScolled = true;
        } else {
            isScolled = false;
        }

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            layoutManager = recyclerView.getLayoutManager();
            countItem = layoutManager.getItemCount();
            lastItem = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }
        if (isScolled && countItem != lastItem && lastItem == countItem - 1) {
            onLoading(countItem, lastItem);
        }
    }
}
