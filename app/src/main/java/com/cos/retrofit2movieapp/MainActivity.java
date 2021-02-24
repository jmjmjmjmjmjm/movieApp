package com.cos.retrofit2movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.cos.retrofit2movieapp.adapter.MovieAdapter;
import com.cos.retrofit2movieapp.model.Movie;
import com.cos.retrofit2movieapp.service.MovieService;
import com.cos.retrofit2movieapp.viewmodel.MovieViewModel;

import java.util.List;

import lombok.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMovie;
    private MovieAdapter movieAdapter;
    private MovieViewModel movieViewModel;
    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        init();
        initObserve();
    }

    private initListener(){
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Log.d(TAG,"onSwiped:"+ RecyclerView.ViewHolder.getAdapterPosition);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvUserList);
    }

    private void init(){
        rvMovie = findViewById(R.id.rv_move);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMovie.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter(MainActivity.this);
        rvMovie.setAdapter(movieAdapter);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
    }

    private void initData(){
        Call<List<Movie>> call = MovieService.retrofit.create(MovieService.class).findAll();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movies = response.body();
                movieViewModel.change(movies);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.d(TAG,"onFailure"+t.getMessage());
            }
        });
    }

    private void initObserve(){
        movieViewModel.subscribe().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter.setMovies(movies);
            }
        });
    }

}
