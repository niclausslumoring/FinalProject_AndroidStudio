package com.example.signup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.signup.fragments.FragmentAboutUs;
import com.example.signup.fragments.FragmentGame;
import com.example.signup.fragments.FragmentProfile;
import com.example.signup.fragments.FragmentReview;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragmentGame();
            case 1:
                return new FragmentReview();
            case 2:
                return new FragmentProfile();
            case 3:
                return new FragmentAboutUs();
            default:
                return new FragmentGame();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
