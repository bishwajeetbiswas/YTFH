package com.biswas.ytfh.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.biswas.ytfh.R;
import com.biswas.ytfh.app.RecyclerViewClickListener;
import com.biswas.ytfh.app.adapters.ProductAdapter;
import com.biswas.ytfh.network.response.models.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsActivity extends YtfhBaseActivity {
    @BindView(R.id.rv_products)
    RecyclerView rvProducts;

    private final String CATEGORY_NAME = "categoryName";

    private String categoryName;

    public static void launchActivity(Activity activity, String categoryName) {
        activity.startActivity(new Intent(activity, ProductsActivity.class).putExtra("categoryName", categoryName));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        displayProducts();
    }

    @Override
    public void handleActionBar() {
        showToolbar(true);
        enableBackButton();
    }

    @Override
    public void loadValuesFromIntent() {
        categoryName = getIntent().getStringExtra(CATEGORY_NAME);
        Log.d("test", "categoryName=" + categoryName);
        setToolbarTitle(categoryName);
    }

    @Override
    public void initViews() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        rvProducts.setLayoutManager(new LinearLayoutManager(mActivity,
                LinearLayoutManager.VERTICAL, false));
    }

    private void displayProducts() {
        final ArrayList<Product> products = HomeActivity.products;
        final ProductAdapter pa = new ProductAdapter(mActivity, products, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Log.d("test", "name=" + products.get(position).mName);
            }
        });
        rvProducts.setAdapter(pa);
    }

}
