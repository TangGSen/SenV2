package com.sen.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sen.liuboss.R;
import com.sen.uitls.ImageLoadOptions;

import java.util.List;

/**
 * Created by Sen on 2016/2/22.
 */
public class HomeNewRecyclerAdapter extends RecyclerView.Adapter<HomeNewRecyclerAdapter.ViewHolder> {
    private List<String> mData;
    public HomeNewRecyclerAdapter(List<String> data){
        mData = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_new_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(""+position);
        ImageLoader.getInstance().displayImage(mData.get(position), holder.mImageView, ImageLoadOptions.getBannerImageOptions());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView mTextView;
        public AppCompatImageView mImageView;
        public ViewHolder(View view){
            super(view);
            mTextView = (AppCompatTextView) view.findViewById(R.id.txt_class);
            mImageView = (AppCompatImageView) view.findViewById(R.id.new_imageview);
        }
    }
}
