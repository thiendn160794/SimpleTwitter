package com.thiendn.coderschool.simpletwitter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thiendn.coderschool.simpletwitter.R;
import com.thiendn.coderschool.simpletwitter.model.Tweet;
import com.thiendn.coderschool.simpletwitter.util.DateUtil;

import java.util.List;

/**
 * Created by thiendn on 05/03/2017.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ViewHolder> {
    private List<Tweet> mTweets;
    private Context context;
    public TimeLineAdapter(List<Tweet> tweets){
        mTweets = tweets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvUsername.setText(mTweets.get(position).getUser().getName());
        holder.tvScreenName.setText("@" + mTweets.get(position).getUser().getScreenName());
        String relativeTimestamp = DateUtil.getRelativeTimeAgo(mTweets.get(position).getCreatedDate());
        holder.tvCreateDate.setText(relativeTimestamp);
        holder.tvText.setText(mTweets.get(position).getText());
        holder.tvRetweet.setText(mTweets.get(position).getRetweetCount() + "");
        holder.tvFavorite.setText(mTweets.get(position).getFavoriteCount() + "");
        if (!mTweets.get(position).getUser().isVerified()){
            holder.ivVerified.setVisibility(View.GONE);
        }
        Picasso.with(context).load(mTweets.get(position).getUser().getImageProfile())
                .into(holder.ivProfile);
        Log.d("TimeLineAdapter","text: " + mTweets.get(position).getText() + ", position: " + position + ", media: " + mTweets.get(position).getEntity().getMedia());
        if (mTweets.get(position).getEntity().getMedia() == null) holder.ivMedia.setVisibility(View.GONE);
        if (mTweets.get(position).getEntity().getMedia() != null){
            if (mTweets.get(position).getEntity().getMedia().get(0).getMediaUrl()!= null &&
                    !mTweets.get(position).getEntity().getMedia().get(0).getMediaUrl().equals("")){
                holder.ivMedia.setVisibility(View.VISIBLE);
                Picasso.with(context).load(mTweets.get(position).getEntity().getMedia().get(0).getMediaUrl())
                        .into(holder.ivMedia);
                Log.d("TimeLineAdapter", "position: " + position + ", urlImage: " + mTweets.get(position).getEntity().getMedia().get(0).getMediaUrl());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivProfile;
        private TextView tvUsername;
        private ImageView ivVerified;
        private TextView tvScreenName;
        private TextView tvCreateDate;
        private TextView tvText;
        private TextView tvRetweet;
        private TextView tvFavorite;
        private ImageView ivMedia;

        public ViewHolder(View itemView) {
            super(itemView);
            ivProfile = (ImageView) itemView.findViewById(R.id.ivProfile);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            ivVerified = (ImageView) itemView.findViewById(R.id.ivVerified);
            tvScreenName = (TextView) itemView.findViewById(R.id.tvScreenName);
            tvCreateDate = (TextView) itemView.findViewById(R.id.tvCreateDate);
            tvText = (TextView) itemView.findViewById(R.id.tvText);
            tvRetweet = (TextView) itemView.findViewById(R.id.tvRetweet);
            tvFavorite = (TextView) itemView.findViewById(R.id.tvFavorite);
            ivMedia = (ImageView) itemView.findViewById(R.id.ivMedia);
        }
    }

    public interface Listener{
        void onLoadMore();
        void onItemClicked(View itemView);
    }
}
