package com.codingwithmitch.myapplication.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import com.codingwithmitch.myapplication.R;
import com.codingwithmitch.myapplication.model.Posts;


import java.util.List;


public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.CustomViewHolder> {

    private List<Posts> dataList;
    private Context context;

    public RecyclerviewAdapter(Context context, List<Posts> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void updatepostlist(List<Posts> list) {
        this.dataList = list;
        notifyDataSetChanged();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle, txtBody, tvposition;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtTitle = mView.findViewById(R.id.title);
            tvposition = mView.findViewById(R.id.position);
            txtBody = mView.findViewById(R.id.description);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getTitle());
        holder.txtBody.setText(dataList.get(position).getBody());
        holder.tvposition.setText(position + 1 + "");

    }

    @Override
    public int getItemCount() {
        if (this.dataList != null)
            return this.dataList.size();
        else
            return 0;
    }


}