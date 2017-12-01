package com.biswas.ytfh.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.biswas.ytfh.R;
import com.biswas.ytfh.app.RecyclerViewClickListener;
import com.biswas.ytfh.app.viewholder.CategoryViewHolder;
import com.biswas.ytfh.network.response.models.Category;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter {
    private ArrayList<Category> mCategories;
    private Context mContext;
    private RecyclerViewClickListener mListener;

    public CategoryAdapter(Context context, ArrayList<Category> categories, RecyclerViewClickListener listener) {
        mContext = context;
        mCategories = categories;
        mListener = listener;
    }

    public void changeList(ArrayList<Category> categories) {
        mCategories = categories;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return (new CategoryViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_category, parent, false), mListener));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder rhvh = (CategoryViewHolder) holder;

        rhvh.tv_category.setText("" + mCategories.get(position).mName);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }
}
