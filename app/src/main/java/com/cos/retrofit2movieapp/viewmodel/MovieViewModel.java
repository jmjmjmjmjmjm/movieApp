package com.cos.retrofit2movieapp.viewmodel;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cos.retrofit2movieapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieViewModel extends ViewModel {

    private static final String TAG="MovieViewModel";
    private MutableLiveData<List<Movie>> mtMovie = new MutableLiveData<>();

    public MovieViewModel() {
        List<Movie> movies = new ArrayList<>();
        mtMovie.setValue(movies);
    }

    public MutableLiveData<List<Movie>> subscribe(){
        return mtMovie;
    }

    public void change(List<Movie> movies){
        mtMovie.setValue(movies);
    }

    public void save(){

    }

    public void delete(){

    }
}
