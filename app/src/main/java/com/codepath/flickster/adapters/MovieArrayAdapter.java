package com.codepath.flickster.adapters;

import com.codepath.flickster.QuickPlayActivity;
import com.codepath.flickster.R;
import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }

    private static class PopularViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public int getViewTypeCount() {
        return Movie.MovieType.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getMovieType().ordinal();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        int movieType = getItemViewType(position);

        if (movieType == Movie.MovieType.POPULAR.ordinal()) {
            convertView = getViewForPopularMovie(movie, convertView, parent);
        } else {
            convertView = getViewForNormalMovie(movie, convertView, parent);
        }

        return convertView;
    }

    private View getViewForNormalMovie(Movie movie, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivImage.setImageResource(0);
        String imagePath;

        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imagePath = movie.getPosterPath();
        } else {
            imagePath = movie.getBackdropPath();
        }

        Picasso.with(getContext()).load(imagePath).fit().centerInside()
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.movie_placeholder)
                .into(viewHolder.ivImage);

        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        return convertView;
    }

    private View getViewForPopularMovie(Movie movie, View convertView, ViewGroup parent) {
        PopularViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie_popular, parent, false);

            viewHolder = new PopularViewHolder();
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PopularViewHolder) convertView.getTag();
        }

        viewHolder.ivImage.setImageResource(0);
        String imagePath = movie.getBackdropPath();

        Picasso.with(getContext()).load(imagePath).fit().centerInside()
                .placeholder(R.drawable.movie_placeholder)
                .error(R.drawable.movie_placeholder)
                .into(viewHolder.ivImage);

        if (viewHolder.tvTitle != null) {
            viewHolder.tvTitle.setText(movie.getOriginalTitle());
        }
        if (viewHolder.tvOverview != null) {
            viewHolder.tvOverview.setText(movie.getOverview());
        }

        final int movieNetworkId = movie.getNetworkId();
        viewHolder.ivImage.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuickPlayActivity.class);
                intent.putExtra("networkId", movieNetworkId);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
