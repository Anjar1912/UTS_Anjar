package com.aryobimo.uts.network.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListFilmResponse{

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<DataFilm> results;

	@SerializedName("total_results")
	private int totalResults;

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<DataFilm> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}
}