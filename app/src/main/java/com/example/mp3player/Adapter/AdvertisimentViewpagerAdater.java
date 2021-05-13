package com.example.mp3player.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.mp3player.Activity.SongActivity;
import com.example.mp3player.Model.Ads;
import com.example.mp3player.R;
import com.squareup.picasso.Picasso;

import java.util.List;

//This adapter to show Advertisiment in FragmentAdvertisiment
public class AdvertisimentViewpagerAdater extends PagerAdapter {

    private List<Ads> adsList;
    private int layout;
    private Context context;

    public AdvertisimentViewpagerAdater(List<Ads> adsList, int layout, Context context) {
        this.adsList = adsList;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return adsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(layout, container, false);

        ImageView imgAds = view.findViewById(R.id.imageViewAds);
        ImageView imgAdsSmallImage = view.findViewById(R.id.imageViewAdsSmallImage);
        TextView txtAdsDescription = view.findViewById(R.id.textViewAdsDescription);
        TextView txtAdsSongName = view.findViewById(R.id.textViewAdsSongName);

        final Ads ads = adsList.get(position);
        txtAdsDescription.setText(ads.getNoiDung());
        txtAdsSongName.setText(ads.getTenBaiHat());
        Picasso.with(context).load(ads.getHinhAnh()).into(imgAds);
        Picasso.with(context).load(ads.getHinhBaiHat()).into(imgAdsSmallImage);

        //On view click event
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SongActivity.class);
                intent.putExtra("Advertisiment", ads);
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
