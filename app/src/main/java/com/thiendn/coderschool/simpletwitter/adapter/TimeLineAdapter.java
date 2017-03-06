package com.thiendn.coderschool.simpletwitter.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.thiendn.coderschool.simpletwitter.R;
import com.thiendn.coderschool.simpletwitter.activity.HomeActivity;
import com.thiendn.coderschool.simpletwitter.application.RestApplication;
import com.thiendn.coderschool.simpletwitter.model.Tweet;
import com.thiendn.coderschool.simpletwitter.util.DateUtil;
import com.thiendn.coderschool.simpletwitter.util.ParseResponse;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by thiendn on 05/03/2017.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ViewHolder> {
    private Listener mListener;
    private List<Tweet> mTweets;
    private Context context;
    public TimeLineAdapter(List<Tweet> tweets, Listener listener){
        mTweets = tweets;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final boolean flagFavorite = mTweets.get(position).isFavorite();
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
        if (position == mTweets.size() - 1 && mListener != null) {
            mListener.onLoadMore();
        }
        if (mTweets.get(position).isFavorite()){
            holder.ivFavorite.setImageResource(R.drawable.ic_favorite);
        }else {
            holder.ivFavorite.setImageResource(R.drawable.ic_not_favorite);
        }
        holder.ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTweets.get(position).isFavorite()) {
                    RestApplication.getRestClient().unFavoriteTweet(mTweets.get(position).getId(), new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            if(200 == statusCode){
                                Tweet tweetRes = ParseResponse.getTweetFromResponse(response);
                                updateFavoriteTweet(tweetRes, position);
                            }
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                            Log.d("Favorite", throwable.getMessage());
                        }
                    });
                } else {
                    RestApplication.getRestClient().favoriteTweet(mTweets.get(position).getId(), new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            Tweet tweetRes = ParseResponse.getTweetFromResponse(response);
                            updateFavoriteTweet(tweetRes, position);
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                            Log.d("Favorite", throwable.getMessage());
                        }
                    });
                }
            }
        });

        holder.ivReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onReply(mTweets.get(position).getUser().getScreenName());
            }
        });

    }

    private void updateFavoriteTweet(Tweet tweet, int position){
        mTweets.set(position, tweet);
        notifyItemChanged(position);
    }
//    private void updateUIFavorite(boolean flag, View view){
//        if (flag){
//            Picasso.with(context).load(R.drawable.ic_favorite).into((ImageView) view);
//        }else {
//            Picasso.with(context).load(R.drawable.ic_not_favorite).into((ImageView) view);
//        }
//    }

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
        private ImageView ivFavorite;
        private ImageView ivReply;

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
            ivFavorite = (ImageView) itemView.findViewById(R.id.ivFavorite);
            ivReply = (ImageView) itemView.findViewById(R.id.ivReply);
        }
    }

    public interface Listener{
        void onLoadMore();
        void onItemClicked(View itemView);
        void onReply(String screenname);
    }
}
