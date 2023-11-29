package com.aryobimo.uts.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aryobimo.uts.R;
import com.aryobimo.uts.network.response.DataFilm;
import com.aryobimo.uts.ui.detail.DetailFilmActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<DataFilm> localDataSet = null;
    private Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView title, overview, releaseDate;
        CardView parent;
        AppCompatImageView poster;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            parent = view.findViewById(R.id.layoutParent);
            title = view.findViewById(R.id.tvTitle);
            overview = view.findViewById(R.id.tvOverview);
            releaseDate = view.findViewById(R.id.tvReleaseDate);
            poster = view.findViewById(R.id.appCompatImageView);
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView.
     */
    public MainAdapter(ArrayList<DataFilm> dataSet, Context context) {
        this.localDataSet = dataSet;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main, viewGroup, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        DataFilm data = localDataSet.get(position);

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.title.setText(data.getTitle());
        viewHolder.overview.setText(data.getOverview());
        viewHolder.releaseDate.setText(data.getReleaseDate());
        Picasso.get().load("https://themoviedb.org/t/p/w500" + data.getPosterPath()).into(viewHolder.poster);

        viewHolder.parent.setOnClickListener(view -> {
            context.startActivity(new Intent(context, DetailFilmActivity.class)
                    .putExtra("data", new Gson().toJson(data))
            );
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}