package com.example.mid_exam_dina_zaqout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

// The adapter tells ViewPager2 which Fragment to show in each tab.
public class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    // Returns the Fragment for each tab position.
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return TabFragment.newInstance("First");
            case 1:
                return TabFragment.newInstance("Second");
            default:
                return TabFragment.newInstance("Third");
        }
    }

    // Number of tabs.
    @Override
    public int getItemCount() {
        return 3;
    }
}