package com.example.ahtt_vp_feminin.myflicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahtt_vp_feminin.myflicks.R;
import com.example.ahtt_vp_feminin.myflicks.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by AHTT - V P-FEMININ on 7/18/2017.
 */


public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    //Question: Are viewholders typically implement as private classes? Or was this just
//Used to show how one could be made quickly?
    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


// Get the data item for position
        Movie movie = getItem(position);
        ViewHolder viewHolder;
        convertView = null;

// Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovie);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


// Clear prev image
        viewHolder.ivImage.setImageResource(0);

// Populate text
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

// Load movie images based on item_movie
        int orientation = getContext().getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath()).placeholder(R.drawable.video_placeholder).transform(new RoundedCornersTransformation(8, 8)).into(viewHolder.ivImage);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackdropPath()).placeholder(R.drawable.video_placeholder).transform(new RoundedCornersTransformation(8, 8)).into(viewHolder.ivImage);
        }

        return convertView;


    }
}