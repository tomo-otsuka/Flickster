package com.codepath.flickster;

import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailActivity extends AppCompatActivity {

    int networkId;

    @BindView(R.id.ivMovieImage) ImageView ivMovieImage;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvOverview) TextView tvOverview;
    @BindView(R.id.rbRating) RatingBar rbRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        networkId = intent.getIntExtra("networkId", -1);

        tvTitle.setText(intent.getStringExtra("title"));
        tvOverview.setText(intent.getStringExtra("overview"));

        Picasso.with(this).load(intent.getStringExtra("backdropPath")).fit().centerCrop()
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.movie_placeholder)
                .into(ivMovieImage);

        rbRating.setRating((float) intent.getDoubleExtra("voteAverage", 0));
    }

    @OnClick(R.id.ivMovieImage)
    public void onMovieImageClick(View view) {
        Intent intent = new Intent(getBaseContext(), QuickPlayActivity.class);
        intent.putExtra("networkId", networkId);
        startActivity(intent);
    }
}
