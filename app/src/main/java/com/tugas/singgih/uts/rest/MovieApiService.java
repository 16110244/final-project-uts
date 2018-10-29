package com.tugas.singgih.uts.rest;


import com.tugas.singgih.uts.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {
	@GET("movie/top_rated")
	Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);
	@GET("movie/popular")
	Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);
	@GET("/movie/{id}/videos")
	Call<MovieResponse> getTrailers(@Path("id") int movieId);
	@GET("/movie/{id}/reviews")
	Call<MovieResponse> getReviews(@Path("id") int movieId);

//	@GET("/movie/{id}/reviews") Observable<Review.Response> reviews(
//		@Path("id") long movieId,
//		@Query("page") int page);
}