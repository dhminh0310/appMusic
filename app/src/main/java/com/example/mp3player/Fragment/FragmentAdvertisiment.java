package com.example.mp3player.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mp3player.Adapter.AdvertisimentViewpagerAdater;
import com.example.mp3player.Model.Ads;
import com.example.mp3player.R;
import com.example.mp3player.Service.ApiService;
import com.example.mp3player.Service.RetrofitClient;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAdvertisiment extends Fragment {

    private ViewPager mViewPager;
    private CircleIndicator mIndicator;
    private AdvertisimentViewpagerAdater adater;
    private Handler mHandler;
    private Runnable mRunnable;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advertisiment, container, false);
        InitUI();
        getDataAds();
        return view;
    }

    private void InitUI() {
        mViewPager = view.findViewById(R.id.viewPagerAds);
        mIndicator = view.findViewById(R.id.circleIndicatorAds);
    }

    private void getDataAds(){

        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Ads>> callback = apiService.getListAds();
        callback.enqueue(new Callback<List<Ads>>() {
            @Override
            public void onResponse(Call<List<Ads>> call, Response<List<Ads>> response) {
                List<Ads> listAds = response.body();
                adater = new AdvertisimentViewpagerAdater(listAds, R.layout.line_advertisiment, getContext());
                mViewPager.setAdapter(adater);
                mIndicator.setViewPager(mViewPager);

                //Auto slide View
                AutoSlideView(mViewPager);

            }

            @Override
            public void onFailure(Call<List<Ads>> call, Throwable t) {
                Toast.makeText(getContext(), "ErrorOnAdvertisiment", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AutoSlideView(final ViewPager mViewPager) {
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = mViewPager.getCurrentItem();
                currentItem++;
                if(currentItem >= mViewPager.getAdapter().getCount()){
                    currentItem = 0;
                }
                mViewPager.setCurrentItem(currentItem, true);
                mHandler.postDelayed(mRunnable, 3000);
            }
        };
        mHandler.postDelayed(mRunnable, 3000);
    }

}
