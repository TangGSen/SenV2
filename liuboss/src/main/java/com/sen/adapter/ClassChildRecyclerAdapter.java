package com.sen.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sen.liuboss.R;
import com.sen.mode.ClassChildItemBean;
import com.sen.uitls.ImageLoadOptions;

import java.util.List;

/**
 * Created by Sen on 2016/2/22.
 */
public class ClassChildRecyclerAdapter extends RecyclerView.Adapter<ClassChildRecyclerAdapter.ViewHolder> {
    private List<ClassChildItemBean> mData;
    private Context mContext;

    public ClassChildRecyclerAdapter(Context context, List<ClassChildItemBean> data) {
        mContext = context;
        mData = data;
    }

    private OnItemClickListener onItemClickListener = null;


    //Item click thing
    public  interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public  void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener =onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_new_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);

        ViewGroup.LayoutParams params = holder.mImageView.getLayoutParams();
        params.height = (int) (dm.widthPixels * 0.5);
        params.width = (int) (dm.widthPixels * 0.5);
        holder.mImageView.setLayoutParams(params);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClickListener.onItemClick(holder.itemView, position);
                }

            });
        }
        holder.mImageView.setTag(mData.get(position));
        holder.mTextView.setText("" + position + mData.get(position).getDes());
        ImageLoader.getInstance().displayImage(mData.get(position).getUrl(), holder.mImageView, ImageLoadOptions.getBannerImageOptions());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView mTextView;
        public AppCompatImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (AppCompatTextView) view.findViewById(R.id.des_text);
            mImageView = (AppCompatImageView) view.findViewById(R.id.new_imageview);
        }


    }
}
