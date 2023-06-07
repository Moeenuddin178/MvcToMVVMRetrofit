package com.codingwithmitch.myapplication.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codingwithmitch.myapplication.model.Posts;
import com.codingwithmitch.myapplication.network.ApiService;
import com.codingwithmitch.myapplication.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostsListViewModel extends ViewModel {
    private MutableLiveData<List<Posts>> Postlist;

    public PostsListViewModel() {
        Postlist = new MutableLiveData<>();
    }

    public MutableLiveData<List<Posts>> getMovielistObserver() {
        return Postlist;
    }

    public void makeApiCall() {
        ApiService apiServices = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<List<Posts>> call = apiServices.getAllPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                Postlist.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Postlist.postValue(null);
                Log.e("Error :", t.getMessage().toString());
            }
        });


    }
}
