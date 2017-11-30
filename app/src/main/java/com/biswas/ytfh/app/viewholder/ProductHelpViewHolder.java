package com.biswas.ytfh.app.viewholder;

import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.biswas.ytfh.R;
import com.biswas.ytfh.app.RecyclerViewClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductHelpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    @BindView(R.id.tv_product_name)
    public TextView tvProductName;
    @BindView(R.id.tv_date_added)
    public TextView tvDateAdded;
    @BindView(R.id.tv_vat)
    public TextView tvVat;
    @BindView(R.id.sp_variants)
    public AppCompatSpinner spVariants;
    private RecyclerViewClickListener mListener;

    public ProductHelpViewHolder(View itemView,RecyclerViewClickListener listener) {
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
