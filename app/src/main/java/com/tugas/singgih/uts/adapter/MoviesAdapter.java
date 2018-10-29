package com.tugas.singgih.uts.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.tugas.singgih.uts.R;
import com.tugas.singgih.uts.activity.DetailsActivity;
import com.tugas.singgih.uts.model.Movie;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
	private static List<Movie> movies;
	private int rowLayout;
	private Context context;
	public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w500//";

	public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
		this.movies = movies;
		this.rowLayout = rowLayout;
		this.context = context;
	}


	//A view holder inner class where we get reference to the views in the layout using their ID
	public static class MovieViewHolder extends RecyclerView.ViewHolder {
		LinearLayout moviesLayout;
		ImageView movieImage;
		ImageView backdropImage;
		public MovieViewHolder(View v) {
			super(v);
			moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
			movieImage = (ImageView) v.findViewById(R.id.movie_image);
			backdropImage = (ImageView) v.findViewById(R.id.backdrop);
			v.setOnClickListener(new View.OnClickListener() {
				@Override public void onClick(View v) {
					int pos = getAdapterPosition();
					Context context = v.getContext();
					Intent intent = new Intent(context, DetailsActivity.class);
					intent.putExtra("DATA", new Gson().toJson(getPhoto(pos)));
					context.startActivity(intent);
				}
			});
		}

		public Movie getPhoto(int position) {
			return ((movies != null) && (movies.size() !=0) ? movies.get(position) : null);
		}
	}

	@Override
	public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
		int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

		return new MovieViewHolder(view);
	}
	@Override
	public void onBindViewHolder(MovieViewHolder holder, final int position) {
		String image_url = IMAGE_URL_BASE_PATH + movies.get(position).getPosterPath();
		Picasso.with(context)
			.load(image_url)
			.placeholder(android.R.drawable.gallery_thumb)
			.error(android.R.drawable.sym_def_app_icon)
			.into(holder.movieImage);
		//to preload backdrop img
//		String backdrop_url = IMAGE_URL_BASE_PATH + movies.get(position).getBackdropPath();
//		Picasso.with(context)
//			.load(backdrop_url)
//			.placeholder(android.R.drawable.sym_def_app_icon)
//			.error(android.R.drawable.sym_def_app_icon)
//			.into(holder.backdropImage);
	}
	@Override
	public int getItemCount() {
		return movies.size();
	}
}