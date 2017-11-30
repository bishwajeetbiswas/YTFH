package com.biswas.ytfh.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.biswas.ytfh.R;
import com.biswas.ytfh.network.response.models.Variant;

import java.util.ArrayList;

/**
 * Created by bishwajeetbiswas on 30/11/17.
 */

public class VariantsSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    public ArrayList<Variant> mVariants;
    private Context mContext;
    private LayoutInflater inflator;

    public VariantsSpinnerAdapter(Context mContext, ArrayList<Variant> variants) {
        this.mContext = mContext;
        this.mVariants = variants;
        inflator = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mVariants.size();
    }

    @Override
    public Object getItem(int position) {
        return mVariants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflator.inflate(R.layout.item_variant, null);
        ((TextView) view.findViewById(R.id.tv_variant)).setText("Color : " + mVariants.get(position).mColor + ", Size : " + mVariants.get(position).mSize + ", Price : " + mVariants.get(position).mPrice);
        return view;
    }

}