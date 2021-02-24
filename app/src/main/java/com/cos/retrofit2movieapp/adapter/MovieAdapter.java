package com.cos.retrofit2movieapp.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.retrofit2movieapp.MainActivity;
import com.cos.retrofit2movieapp.R;
import com.cos.retrofit2movieapp.databinding.CardItemBinding;
import com.cos.retrofit2movieapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private static final String TAG = "MovieAdapter";

    private List<Movie> movies = new ArrayList<>();
    private MainActivity mContext;

    public MovieAdapter(MainActivity mainActivity) {
        this.mContext = mContext;
    }


    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardItemBinding cardItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.card_item, parent, false);


        return new MovieViewHolder(cardItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.cardItemBinding.setMovie(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    // 카드 아이템 디자인이 연결
    class MovieViewHolder extends RecyclerView.ViewHolder {

        private CardItemBinding cardItemBinding;

        public MovieViewHolder(@NonNull CardItemBinding cardItemBinding) {
            super(cardItemBinding.getRoot());
            this.cardItemBinding = cardItemBinding;
        }
    }
}
