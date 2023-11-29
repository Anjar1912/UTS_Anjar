package com.aryobimo.uts.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aryobimo.uts.R;
import com.aryobimo.uts.network.response.DataFilm;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailFilmActivity extends AppCompatActivity {

    AppCompatImageView ivPoster;
    AppCompatTextView tvTitle, tvReleaseDate, tvOverview;
    FloatingActionButton ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);
        DataFilm data = new Gson().fromJson(getIntent().getStringExtra("data"), DataFilm.class);
        ivPoster = findViewById(R.id.ivPoster);
        tvTitle = findViewById(R.id.tvTitle);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        tvOverview = findViewById(R.id.tvOverview);
        ivBack = findViewById(R.id.ivBack);


        Picasso.get().load("https://themoviedb.org/t/p/w500" + data.getPosterPath()).into(ivPoster);
        tvTitle.setText(data.getTitle());
        tvReleaseDate.setText(data.getReleaseDate());
        tvOverview.setText(data.getOverview());
        ivBack.setOnClickListener(view -> {
            finish();
        });
    }
}