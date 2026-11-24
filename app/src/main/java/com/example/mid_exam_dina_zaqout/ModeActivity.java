package com.example.mid_exam_dina_zaqout;

import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mid_exam_dina_zaqout.databinding.ActivityModeBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class ModeActivity extends AppCompatActivity {

    private ActivityModeBinding binding;

    // The names of the three tabs.
    private final String[] tabTitles = {"First", "Second", "Third"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityModeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up the app bar.
        if (getSupportActionBar() == null) {
            setSupportActionBar(binding.toolbar);
        } else {
            binding.toolbar.setVisibility(View.GONE);
        }

        // Show the Up button.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Connect the adapter to ViewPager2.
        binding.viewPager.setAdapter(new PagerAdapter(this));

        // Connect TabLayout with ViewPager2.
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> tab.setText(tabTitles[position])).attach();

        // Custom back-stack behavior.
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (binding.viewPager.getCurrentItem() != 0) {
                    binding.viewPager.setCurrentItem(0);
                } else {
                    finish();
                }
            }
        });

        // Open the PopupMenu when Set Mode is clicked.
        binding.buttonSetMode.setOnClickListener(v -> showModeMenu());
    }

    // Display the mode selection menu.
    private void showModeMenu() {

        PopupMenu popupMenu = new PopupMenu(this, binding.buttonSetMode);

        popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {

            int id = item.getItemId();

            if (id == R.id.mode_light) {

                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO);
                return true;

            } else if (id == R.id.mode_night) {

                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);
                return true;

            } else if (id == R.id.mode_system) {

                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                return true;
            }

            return false;
        });

        popupMenu.show();
    }

    // Up navigation: return directly to MainActivity.
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}