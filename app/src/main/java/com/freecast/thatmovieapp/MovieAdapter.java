package com.freecast.thatmovieapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.DataObjectHolder> {

    private final List<Pair<String, String>> movies;
    private static final String base_image_url = "https://image.tmdb.org/t/p/original";
    int selectedPosition = -1;
    private final Context context;

    public MovieAdapter(List<Pair<String, String>> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public MovieAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.movie_item, parent, false);

        return new MovieAdapter.DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.DataObjectHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        private final TextView movieTitle;

        private final ImageView movieImage;

        public DataObjectHolder(View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieImage = itemView.findViewById(R.id.moviePoster);
        }



        void bind(int position) {
            movieTitle.setText(movies.get(position).first);
            Glide.with(context).load(base_image_url + movies.get(position).second).into(movieImage);
        }
    }
}




