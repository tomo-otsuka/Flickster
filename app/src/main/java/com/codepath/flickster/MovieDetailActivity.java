package com.codepath.flickster;

import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {

    int networkId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView ivMovieImage = (ImageView) findViewById(R.id.ivMovieImage);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView) findViewById(R.id.tvOverview);
        RatingBar rbRating = (RatingBar) findViewById(R.id.rbRating);

        Intent intent = getIntent();

        networkId = intent.getIntExtra("networkId", -1);

        tvTitle.setText(intent.getStringExtra("title"));
        tvOverview.setText(intent.getStringExtra("overview"));

        Picasso.with(this).load(intent.getStringExtra("backdropPath")).fit().centerCrop()
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.movie_placeholder)
                .into(ivMovieImage);

        rbRating.setRating((float) intent.getDoubleExtra("voteAverage", 0));

        setListeners();
    }

    private void setListeners() {
        ImageView ivMovieImage = (ImageView) findViewById(R.id.ivMovieImage);
        ivMovieImage.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), QuickPlayActivity.class);
                intent.putExtra("networkId", networkId);
                startActivity(intent);
            }
        });
    }
}
