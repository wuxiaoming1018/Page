package com.ming.page.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ming.page.R;
import com.ming.page.bean.DataBean;

public class MyAdapter extends PagedListAdapter<DataBean, MyAdapter.MyViewHolder> {


    public MyAdapter(@NonNull DiffUtil.ItemCallback<DataBean> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recently_used, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataBean bean = getItem(position);
        holder.idText.setText(bean.id + "");
        holder.contentText.setText(bean.content);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView idText;
        public TextView contentText;

        public MyViewHolder(View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.image_icon);
//            idText.setTextColor(Color.RED);
            contentText = itemView.findViewById(R.id.text_name);
//            contentText.setTextColor(Color.BLUE);
        }
    }
}
