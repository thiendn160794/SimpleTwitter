package com.thiendn.coderschool.simpletwitter.adapters;

import android.content.Context;
import android.graphics.Picture;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.thiendn.coderschool.simpletwitter.R;
import com.thiendn.coderschool.simpletwitter.model.Tweet;

import java.util.List;

/**
 * Created by thiendn on 13/03/2017.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    private List<Tweet> mTweets;
    private Context mContext;

    public PictureAdapter(List<Tweet> tweets){
        mTweets = tweets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ivPicture.setVisibility(View.GONE);
        if (mTweets.get(position).getEntity().getMedia() != null
                && mTweets.get(position).getEntity().getMedia().size() > 0){
            if (mTweets.get(position).getEntity().getMedia().get(0).getMediaUrl()!= null &&
                    !mTweets.get(position).getEntity().getMedia().get(0).getMediaUrl().equals("")){
                holder.ivPicture.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(mTweets.get(position).getEntity().getMedia().get(0).getMediaUrl())
                        .into(holder.ivPicture);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPicture;
        public ViewHolder(View itemView) {
            super(itemView);
            ivPicture = (ImageView) itemView.findViewById(R.id.ivPicture);
        }
    }
}
