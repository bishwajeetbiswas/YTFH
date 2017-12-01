package com.biswas.ytfh.ui;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.biswas.ytfh.R;
import com.biswas.ytfh.app.CommonMethod;
import com.biswas.ytfh.app.RecyclerViewClickListener;
import com.biswas.ytfh.app.YtfhApplication;
import com.biswas.ytfh.app.adapters.CategoryAdapter;
import com.biswas.ytfh.app.adapters.RankingSpinnerAdapter;
import com.biswas.ytfh.network.api.YtfhSubscriber;
import com.biswas.ytfh.network.response.YtfhResponse;
import com.biswas.ytfh.network.response.models.Category;
import com.biswas.ytfh.network.response.models.Product;
import com.biswas.ytfh.network.response.models.Ranking;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends YtfhBaseActivity {
    @BindView(R.id.rv_categories)
    RecyclerView rvCategories;
    @BindView(R.id.sp_ranking)
    AppCompatSpinner spRanking;

    private YtfhApplication mApp;
    private ArrayList<Category> mCategories;
    static public ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mApp = (YtfhApplication) getApplication();
        getYtfhData();
    }

    @Override
    public void handleActionBar() {
        setToolbarTitle("Home");
        showToolbar(true);
        enableBackButton();
    }

    @Override
    public void loadValuesFromIntent() {

    }

    @Override
    public void initViews() {
        spRanking.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        initRecyclerView();
    }

    private void initRecyclerView() {
        rvCategories.setLayoutManager(new LinearLayoutManager(mActivity,
                LinearLayoutManager.VERTICAL, false));
    }

    private void getYtfhData() {
        CommonMethod.showBottomProgressDialoge(mActivity, getResources().getString(R.string.please_wait));
        mApp.getYtfhApiService().
                getYtfhResponse().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new YtfhSubscriber<YtfhResponse>(mActivity, false) {
                    @Override
                    public void onComplete() {
                        CommonMethod.hideBottomProgressDialoge();
                    }

                    @Override
                    public void onNext(YtfhResponse ytfhResponse) {
                        super.onNext(ytfhResponse);
                        if (ytfhResponse != null)
                            displayRanking(ytfhResponse);
                        else
                            CommonMethod.showOkAlertDialog(mActivity.getResources().getText(R.string.no_data_found_try_again).toString(), mActivity.getResources().getString(R.string.app_name)
                                    , mActivity, true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    private void displayRanking(YtfhResponse ytfhResponse) {
        final ArrayList<Category> categories = ytfhResponse.mCategories;
        final ArrayList<Ranking> rankings = ytfhResponse.mRankings;
        mCategories = calculateList(categories, rankings.get(0).mProducts);
        final CategoryAdapter ra = new CategoryAdapter(mActivity, mCategories, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                products = mCategories.get(position).mProducts;
                String categoryName = mCategories.get(position).mName;
                ProductsActivity.launchActivity(mActivity, categoryName);
            }
        });

        if (rankings != null) {
            final RankingSpinnerAdapter adapter = new RankingSpinnerAdapter(mActivity, rankings);
            spRanking.setAdapter(adapter);
            spRanking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    ArrayList<Product> products = rankings.get(i).mProducts;
                    mCategories = calculateList(categories, products);
                    ra.changeList(mCategories);
                    ra.notifyDataSetChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            spRanking.setSelection(0);
            rvCategories.setAdapter(ra);
        }
    }

    private ArrayList<Category> calculateList(ArrayList<Category> categories, ArrayList<Product> products) {
        ArrayList<Category> categoriesCopy = new ArrayList<>(categories);
        ArrayList<Product> productsCopy;
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < categoriesCopy.size(); j++) {
                productsCopy = new ArrayList<>(categoriesCopy.get(j).mProducts);
                for (int k = 0; k < productsCopy.size(); k++) {
                    if (products.get(i).mId != productsCopy.get(k).mId)
                        productsCopy.remove(productsCopy.get(k));
                }
            }
        }
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).mProducts.size() == 0) {
                categoriesCopy.remove(categories.get(i));
            }
        }
        return categoriesCopy;
    }
}
