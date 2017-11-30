package com.biswas.ytfh.app.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.biswas.ytfh.R;
import com.biswas.ytfh.app.RecyclerViewClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RankingHelpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    @BindView(R.id.tv_category)
    public TextView tv_category;
    private RecyclerViewClickListener mListener;

    public RankingHelpViewHolder(View itemView,RecyclerViewClickListener listener) {
        super(itemView);
        mListener=listener;
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());
    }
}
