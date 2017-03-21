package com.example.daniele.cntest.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.daniele.cntest.R;
import com.example.daniele.cntest.rest.model.Joke;
import com.example.daniele.cntest.rest.model.RandomJoke;

import java.util.List;

/**
 * Created by daniele on 21/03/17.
 */

public class JokesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int JOKE_VIEW_TYPE = 1;
    public static final int LOADING_VIEW_TYPE = 2;


    protected class JokeViewHolder extends RecyclerView.ViewHolder {

        View myView;
        TextView textJoke;

        public JokeViewHolder(View view) {
            super(view);
            myView = view;
            textJoke = (TextView) view.findViewById(R.id.text_joke);
        }
    }


    protected class LoadingViewHolder extends RecyclerView.ViewHolder {

        View myView;
        ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            myView = view;
            progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        }
    }


    private Context mContext;
    private List<Joke> mList;

    public JokesAdapter(Context context, List<Joke> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch(viewType){
            case JOKE_VIEW_TYPE:
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View itemView = inflater.inflate(R.layout.view_joke_item, parent, false);
                return new JokeViewHolder(itemView);
            case LOADING_VIEW_TYPE:
                inflater = LayoutInflater.from(parent.getContext());
                itemView = inflater.inflate(R.layout.view_progress_item, parent, false);
                return new LoadingViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case JOKE_VIEW_TYPE:
                final Joke joke = mList.get(position);
                if(joke != null) {
                    String textJoke = joke.getJoke().replace("&quot;", "\"");
                    ((JokeViewHolder)holder).textJoke.setText(textJoke);
                }
            break;
            case LOADING_VIEW_TYPE:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mList.size() - 1 == position){
            return LOADING_VIEW_TYPE;
        }
        else{
            return JOKE_VIEW_TYPE;
        }
    }

    public void setJokesList(List<Joke> list) {
        mList = list;
    }
}