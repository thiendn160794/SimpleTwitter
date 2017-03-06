package com.thiendn.coderschool.simpletwitter.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.thiendn.coderschool.simpletwitter.R;
import com.thiendn.coderschool.simpletwitter.application.RestApplication;
import com.thiendn.coderschool.simpletwitter.model.Tweet;
import com.thiendn.coderschool.simpletwitter.util.ParseResponse;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by thiendn on 06/03/2017.
 */

public class ComposeDialog extends DialogFragment {
    private Context mContext;
    private Listener mListener;
//    @BindView(R.id.Esc)
//    ImageView esc;
    ImageView esc;
    ImageView ivProfile;
    TextView tvUsername;
    TextView tvScreenname;
    EditText editText;
    Button btnTweet;
    TextView numberOfCharacter;

    public static ComposeDialog newInstance(Context context, Listener listener){
        ComposeDialog f = new ComposeDialog();
        Bundle args = new Bundle();
        f.setArguments(args);
        f.setContext(context);
        f.setListener(listener);
        return f;
    }

    public interface Listener{
        void onComposeTweetSuccess(Tweet tweet);
    }

    private void setContext(Context context){
        mContext = context;
    }

    private void setListener(Listener listener){
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_fragment, container);
//        ButterKnife.bind(this, view);
        esc = (ImageView) view.findViewById(R.id.Esc);
        ivProfile = (ImageView) view.findViewById(R.id.ivProfile);
        tvUsername = (TextView) view.findViewById(R.id.tvUsername);
        tvScreenname = (TextView) view.findViewById(R.id.tvScreenName);
        editText = (EditText) view.findViewById(R.id.etText);
        btnTweet = (Button) view.findViewById(R.id.btnTweet);
        numberOfCharacter = (TextView) view.findViewById(R.id.tvNumberOfCharacter);

        esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(, "Esc", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });
        String username = RestApplication.mUser.getName();
        String screenname = RestApplication.mUser.getScreenName();
        String profileimage = RestApplication.mUser.getImageProfile();
        tvUsername.setText(username);
        tvScreenname.setText("@" + screenname);
        Picasso.with(mContext).load(profileimage)
                .into(ivProfile);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Dont care
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int charCount = editText.getText().length();
                int charRemain = 140 - charCount;
                if (charRemain < 0){
                    numberOfCharacter.setTextColor(ContextCompat.getColor(mContext, android.R.color.holo_red_light));
                }else{
                    numberOfCharacter.setTextColor(ContextCompat.getColor(mContext, android.R.color.darker_gray));
                }
                numberOfCharacter.setText(charRemain + "");
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Dont care
            }
        });
        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String body = editText.getText().toString();
                RestApplication.getRestClient().postTweet(body, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        if (200 == statusCode) {
                            Log.d("OnTweet", response.toString());
                            Tweet tweet = ParseResponse.getTweetFromResponse(response);
                            mListener.onComposeTweetSuccess(tweet);

                            getDialog().dismiss();
                        }
                    }
                });
            }
        });
        return view;
    }


}
