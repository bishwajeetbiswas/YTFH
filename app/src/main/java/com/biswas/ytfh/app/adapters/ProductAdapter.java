package com.biswas.ytfh.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.biswas.ytfh.R;
import com.biswas.ytfh.app.RecyclerViewClickListener;
import com.biswas.ytfh.app.viewholder.ProductViewHolder;
import com.biswas.ytfh.network.response.models.Product;

import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter {
    private ArrayList<Product> mProducts;
    private Context mContext;
    private RecyclerViewClickListener mListener;
    private VariantsSpinnerAdapter adapter;

    public ProductAdapter(Context context, ArrayList<Product> products, RecyclerViewClickListener listener) {
        mContext = context;
        mProducts = products;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return (new ProductViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_product, parent, false), mListener));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductViewHolder rhvh = (ProductViewHolder) holder;
        rhvh.tvProductName.setText(mProducts.get(position).mName);
        rhvh.tvDateAdded.setText(mProducts.get(position).mDateAdded);
        rhvh.tvVat.setText(mProducts.get(position).mTax.mName + " : " + mProducts.get(position).mTax.mValue);
        adapter = new VariantsSpinnerAdapter(mContext, mProducts.get(position).mVariants);
        rhvh.spVariants.setAdapter(adapter);
        rhvh.spVariants.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}
