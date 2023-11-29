package com.aryobimo.uts.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.aryobimo.uts.App;
import com.aryobimo.uts.R;
import com.aryobimo.uts.network.Pref;
import com.aryobimo.uts.network.response.DataFilm;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;
    App app;
    MainAdapter adapter;
    ArrayList<DataFilm> listFilm = new ArrayList<>();
    RecyclerView recyclerView;
    SwipeRefreshLayout layoutRefresh;
    CardView btnGantiBahasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Menginisiasi bahasa terlebih dahulu
        initBahasa(false);

        setContentView(R.layout.activity_main);
        app = new App();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initView();
        observeData();
    }

    private void initView() {
        btnGantiBahasa = findViewById(R.id.btnGantiBahasa);
        recyclerView = findViewById(R.id.recyclerView);
        layoutRefresh = findViewById(R.id.layoutRefresh);
        adapter = new MainAdapter(listFilm, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        btnGantiBahasa.setOnClickListener(view -> {
            if (Pref.getLanguage(this).equals("Indonesia")) {
                Pref.setLanguage(this, "English");
            } else {
                Pref.setLanguage(this, "Indonesia");
            }
            initBahasa(true);
        });

        layoutRefresh.setOnRefreshListener(() -> {
            listFilm.clear();
            adapter.notifyDataSetChanged();
            viewModel.getDaftarFilm();
        });
    }

    private void initBahasa(Boolean restartActivity) {
        if ("English".equals(Pref.getLanguage(this))) {
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        } else {
            Locale locale = new Locale("in");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        }

        if (restartActivity) {
            startActivity(new Intent(this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            );
        }
    }

    private void observeData() {
        viewModel.getDaftarFilm();

        viewModel.listFilmResponse.observe(this, listFilmResponse -> {
            Log.i("dataFilm", "jumlah item : " + listFilmResponse.getResults().size());
            listFilm.clear();
            listFilm.addAll(listFilmResponse.getResults());
            adapter.notifyDataSetChanged();
        });

        viewModel.loading.observe(this, aBoolean -> {
            layoutRefresh.setRefreshing(aBoolean);
        });
    }


}