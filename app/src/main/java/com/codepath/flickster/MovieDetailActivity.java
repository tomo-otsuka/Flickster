package com.codepath.flickster;

import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView ivMovieImage = (ImageView) findViewById(R.id.ivMovieImage);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView) findViewById(R.id.tvOverview);
        RatingBar rbRating = (RatingBar) findViewById(R.id.rbRating);

        Intent intent = getIntent();

        tvTitle.setText(intent.getStringExtra("title"));
        tvOverview.setText(intent.getStringExtra("overview"));

        Picasso.with(this).load(intent.getStringExtra("backdropPath")).fit().centerInside()
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.movie_placeholder)
                .into(ivMovieImage);

        rbRating.setRating((float) intent.getDoubleExtra("voteAverage", 0));
    }
}
