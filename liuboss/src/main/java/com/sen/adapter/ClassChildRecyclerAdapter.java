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
        void onItemClick(View view, int position,ClassChildItemBean childItemBean);
    }

    public  void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener =onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_found_class_layout, parent, false);
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

                    onItemClickListener.onItemClick(holder.itemView, position,mData.get(position));
                }

            });
        }
        ClassChildItemBean classChildItemBean = mData.get(position);
        holder.mImageView.setTag(classChildItemBean.getImgUrl());
        holder.mDesTxtView.setText(classChildItemBean.getDesTxt());
        holder.mPriceTxtView.setText("$"+classChildItemBean.getPriceTxt());
        holder.mLoveCountTxtVeiw.setText(classChildItemBean.getLoveCount()+"");
        ImageLoader.getInstance().displayImage(classChildItemBean.getImgUrl(), holder.mImageView, ImageLoadOptions.getBannerImageOptions());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView mDesTxtView,mPriceTxtView,mLoveCountTxtVeiw;
        public AppCompatImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mDesTxtView = (AppCompatTextView) view.findViewById(R.id.item_des_text);
            mPriceTxtView = (AppCompatTextView) view.findViewById(R.id.item_txt_price);
            mLoveCountTxtVeiw = (AppCompatTextView) view.findViewById(R.id.item_txt_love);
            mImageView = (AppCompatImageView) view.findViewById(R.id.item_imageview);
        }


    }
}
