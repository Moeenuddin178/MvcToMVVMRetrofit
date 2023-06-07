package com.codingwithmitch.myapplication;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingwithmitch.myapplication.adapter.RecyclerviewAdapter;
import com.codingwithmitch.myapplication.model.Posts;
import com.codingwithmitch.myapplication.network.ApiService;
import com.codingwithmitch.myapplication.network.RetrofitClientInstance;
import com.codingwithmitch.myapplication.viewModel.PostsListViewModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    private RecyclerviewAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    List<Posts> PostList;
    PostsListViewModel listViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
//        ApiService service = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
//
//        Call<List<Posts>> call = service.getAllPosts();
//        call.enqueue(new Callback<List<Posts>>() {
//
//            @Override
//            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
//                progressDoalog.dismiss();
//                generateDataList(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Posts>> call, Throwable t) {
//                progressDoalog.dismiss();
//                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });

        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new RecyclerviewAdapter(this,PostList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        listViewModel=new ViewModelProvider(this).get(PostsListViewModel.class);

        listViewModel.getMovielistObserver().observe(this, new Observer<List<Posts>>() {
            @Override
            public void onChanged(List<Posts> PostModels) {
                if(PostModels!=null) {

                    PostList=PostModels;
                    adapter.updatepostlist(PostModels);
                    progressDoalog.dismiss();



                }



                if(PostModels==null)
                {
//                    recview.setVisibility(View.GONE);
//                    noresfound.setVisibility(View.VISIBLE);
                }
            }
        });
        listViewModel.makeApiCall();
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Posts> photoList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new RecyclerviewAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
