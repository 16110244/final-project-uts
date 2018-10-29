package com.tugas.singgih.uts.activity;

import static com.tugas.singgih.uts.adapter.MoviesAdapter.IMAGE_URL_BASE_PATH;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tugas.singgih.uts.R;
import com.tugas.singgih.uts.model.Movie;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {

	private static final String TAG = "DetailsActivity";
	private boolean isStarred = false;

	@BindView(R.id.poster)
	ImageView poster;
	@BindView(R.id.releaseDate)
	TextView releaseDate;
	@BindView(R.id.voteAverage)
	TextView voteAverage;
	@BindView(R.id.overview)
	TextView overview;
	@BindView(R.id.backdrop)
	ImageView backdrop;
	@BindView(R.id.back)
	ImageView back;
	@BindView(R.id.title)
	TextView title;
	@BindView(R.id.activity_details)
	ConstraintLayout activityDetails;
	@BindView(R.id.star)
	ImageView star;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		supportRequestWindowFeature(AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY);
		setContentView(R.layout.activity_details);
		ButterKnife.bind(this);

		Intent intent = getIntent();
		Movie movie = (new Gson()).fromJson(intent.getStringExtra("DATA"), Movie.class);
		populateDATA(movie);
	}

	@OnClick(R.id.back)
	public void onBackClicked() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	@OnClick(R.id.star)
	public void onStarClicked() {
		isStarred = !isStarred;
		star.setImageResource(
			isStarred ? android.R.drawable.btn_star_big_on : android.R.drawable.btn_star_big_off);
	}

	public void populateDATA(Movie movie) {

		if (movie != null) {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = format.parse(movie.getReleaseDate());
				format.applyPattern("MMMM yyyy");
				releaseDate.setText(format.format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			voteAverage.setText("Average Rating: " + movie.getVoteAverage().toString() + "/10");
			overview.setText(movie.getOverview());
			title.setText(movie.getTitle());

			String image_url = IMAGE_URL_BASE_PATH + movie.getPosterPath();
			Picasso.with(this)
				.load(image_url)
				.placeholder(android.R.drawable.sym_def_app_icon)
				.error(android.R.drawable.sym_def_app_icon)
				.into(poster);
			String backdrop_url = IMAGE_URL_BASE_PATH + movie.getBackdropPath();
			Log.d(TAG, "onCreate: " + backdrop_url);
			Picasso.with(this)
				.load(backdrop_url)
				.placeholder(android.R.drawable.sym_def_app_icon)
				.error(android.R.drawable.sym_def_app_icon)
				.into(backdrop);
		}
	}
}
