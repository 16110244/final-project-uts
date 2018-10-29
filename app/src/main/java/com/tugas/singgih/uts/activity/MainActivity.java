package com.tugas.singgih.uts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import butterknife.ButterKnife;
import com.tugas.singgih.uts.R;
import com.tugas.singgih.uts.adapter.MoviesAdapter;
import com.tugas.singgih.uts.model.Movie;
import com.tugas.singgih.uts.model.MovieResponse;
import com.tugas.singgih.uts.rest.MovieApiService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = MainActivity.class.getSimpleName();
	public static final String BASE_URL = "http://api.themoviedb.org/3/";
	private static Retrofit retrofit = null;
	private RecyclerView recyclerView = null;
	// insert your themoviedb.org API KEY here
	private final static String API_KEY = "979caf2cde52e5a2e6f80a52fba98755";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
		connectAndGetApiData("top");
	}

	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;


	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.bahasa:
				Intent gantiBah = new Intent(Settings.ACTION_LOCALE_SETTINGS);
				startActivity(gantiBah);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	// This method create an instance of Retrofit
// set the base url
	public void connectAndGetApiData(String mSwitch) {
		if (retrofit == null) {
			retrofit = new Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		}
		MovieApiService movieApiService = retrofit.create(MovieApiService.class);

		Call<MovieResponse> call;
		call = caller(mSwitch);
		call.enqueue(new Callback<MovieResponse>() {
			@Override
			public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
				List<Movie> movies = response.body().getResults();
				recyclerView.setAdapter(
					new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
				Log.d(TAG, R.string.bla1 + " " + movies.size());
			}

			@Override
			public void onFailure(Call<MovieResponse> call, Throwable throwable) {
				Log.e(TAG, throwable.toString());
			}
		});
	}

	public Call caller(String mSwitch) {
		MovieApiService movieApiService = retrofit.create(MovieApiService.class);
		Call<MovieResponse> call;
		//sorting
		switch (mSwitch) {
			case "top":
				call = movieApiService.getTopRatedMovies(API_KEY);
				return call;
			case "pop":
				call = movieApiService.getPopularMovies(API_KEY);
				return call;
			default:
				call = movieApiService.getTopRatedMovies(API_KEY);
				return call;
		}
	}
}

