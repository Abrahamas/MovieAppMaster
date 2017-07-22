package com.example.ahtt_vp_feminin.myflicks;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.ahtt_vp_feminin.myflicks.adapters.MovieArrayAdapter;
import com.example.ahtt_vp_feminin.myflicks.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;


public class MovieActivity extends AppCompatActivity {
ArrayList<Movie> movies;

    MovieArrayAdapter movieadapter;
    ListView LvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        LvItems = (ListView) findViewById(R.id.IVmovie);
        movies = new ArrayList<>();
        movieadapter = new MovieArrayAdapter(this, movies);
        LvItems.setAdapter(movieadapter);
        ActionBar actionBar = getSupportActionBar();
        //Changing the toolbar logo
        actionBar.setLogo(R.mipmap.ic_toolmovie);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        String Url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(Url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;
                try {
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    movieadapter.notifyDataSetChanged();
                    log.d("DEBUG", movieJsonResults.toString());
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }
}
