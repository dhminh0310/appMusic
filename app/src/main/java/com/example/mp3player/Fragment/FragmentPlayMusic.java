package com.example.mp3player.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mp3player.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentPlayMusic extends Fragment {

    View view;
    CircleImageView circleImageView;
    public static ObjectAnimator animator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_music, container, false);
        circleImageView = view.findViewById(R.id.circleImagePlayMusic);
        animator = ObjectAnimator.ofFloat(circleImageView, "rotation", 0f, 360f);
        animator.setDuration(5000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
        return view;
    }

    public void ChangeImage(String songImage){
        Picasso.with(getContext()).load(songImage).noFade().into(circleImageView);
    }
}
