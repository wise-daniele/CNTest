package com.example.daniele.cntest.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daniele.cntest.R;
import com.example.daniele.cntest.rest.model.Joke;
import com.example.daniele.cntest.rest.model.RandomJoke;

import java.util.List;

/**
 * Created by daniele on 21/03/17.
 */

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokeViewHolder> {


    protected class JokeViewHolder extends RecyclerView.ViewHolder {

        View myView;
        TextView textJoke;

        public JokeViewHolder(View view) {
            super(view);
            myView = view;
            textJoke = (TextView) view.findViewById(R.id.text_joke);
        }
    }

    private Context mContext;
    private List<Joke> mList;

    public JokesAdapter(Context context, List<Joke> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public JokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.view_joke_item, parent, false);
        return new JokeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JokeViewHolder holder, int position) {
        final Joke joke = mList.get(position);
        String textJoke = joke.getJoke().replace("&quot;", "\"");
        holder.textJoke.setText(textJoke);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setJokesList(List<Joke> list) {
        mList = list;
    }
}