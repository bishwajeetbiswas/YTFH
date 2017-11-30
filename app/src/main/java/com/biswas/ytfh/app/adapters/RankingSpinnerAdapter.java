package com.biswas.ytfh.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.biswas.ytfh.R;
import com.biswas.ytfh.network.response.models.Ranking;

import java.util.ArrayList;

public class RankingSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    public ArrayList<Ranking> mRankings;
    private Context mContext;
    private LayoutInflater inflator;

    public RankingSpinnerAdapter(Context mContext, ArrayList<Ranking> mRankings)
    {
        this.mContext = mContext;
        this.mRankings = mRankings;
        inflator=LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mRankings.size();
    }

    @Override
    public Object getItem(int position) {
        return mRankings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflator.inflate(R.layout.item_ranking, null);
        ((TextView)view.findViewById(R.id.tv_ranking_label)).setText(mRankings.get(position).mRanking);
        return view;
    }

}