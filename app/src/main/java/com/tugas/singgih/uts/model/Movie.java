package com.tugas.singgih.uts.model;

import com.google.gson.annotations.SerializedName;

public class Movie {
	@SerializedName("poster_path")
	private String posterPath;
	@SerializedName("overview")
	private String overview;
	@SerializedName("release_date")
	private String releaseDate;
	@SerializedName("id")
	private Integer id;
	@SerializedName("title")
	private String title;
	@SerializedName("backdrop_path")
	private String backdropPath;
	@SerializedName("video")
	private Boolean video;
	@SerializedName("vote_average")
	private Double voteAverage;
	public Movie(String posterPath, String overview, String releaseDate, Integer id,
		String title, String backdropPath, Double voteAverage) {
		this.posterPath = posterPath;
		this.overview = overview;
		this.releaseDate = releaseDate;
		this.id = id;
		this.title = title;
		this.backdropPath = backdropPath;
		this.video = video;
		this.voteAverage = voteAverage;
	}
	public String getPosterPath() {
		return posterPath;
	}
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBackdropPath() {
		return backdropPath;
	}
	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}
	public Boolean getVideo() {
		return video;
	}
	public void setVideo(Boolean video) {
		this.video = video;
	}
	public Double getVoteAverage() {
		return voteAverage;
	}
	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

}