package com.example.android.titanic;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {


    public PopularFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        ListView listView = view.findViewById(R.id.popular_fragment_list_view);
        ArrayList<Movie> popularMovies = QueryUtils.extractMoviesPopular();
        final RecommendedMovieAdapter popularMoviesAdapter = new RecommendedMovieAdapter(getActivity(), popularMovies);

        listView.setAdapter(popularMoviesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie clickedMovie = popularMoviesAdapter.getItem(i);
                Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
                intent.putExtra("title", clickedMovie.getTitle());
                String genresString = RecommendedMovieAdapter.convertGenreCodesToStrings(clickedMovie.getGenres());
                intent.putExtra("genres", genresString);
                intent.putExtra("poster_path", clickedMovie.getPosterPath());
                intent.putExtra("backdrop_path", clickedMovie.getBackdropPath());
                intent.putExtra("tmdb_rating", clickedMovie.getTmdbRating());
                intent.putExtra("popularity", clickedMovie.getPopularity());
                intent.putExtra("tagline", clickedMovie.getTagline());
                intent.putExtra("overview", clickedMovie.getOverview());
                intent.putExtra("release_date", clickedMovie.getReleaseDate());
                intent.putExtra("runtime", clickedMovie.getRuntime());
                intent.putExtra("budget", clickedMovie.getBudget());
                intent.putExtra("homepage_url", clickedMovie.getHomePageUrl());
                intent.putExtra("imdb_id", clickedMovie.getImdbId());
                intent.putExtra("tmdb_id", clickedMovie.getTmdbId());
                intent.putExtra("adult", clickedMovie.isAdult());
                intent.putExtra("revenue", clickedMovie.getRevenue());
                startActivity(intent);
            }
        });

        return view;
    }

}
