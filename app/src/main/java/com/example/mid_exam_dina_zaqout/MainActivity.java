package com.example.mid_exam_dina_zaqout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mid_exam_dina_zaqout.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

// MainActivity implements TextWatcher to validate the email and password while the user types.
public class MainActivity extends AppCompatActivity implements TextWatcher {

    private ActivityMainBinding binding;

    // True after the first back press (user must press back a second time to exit).
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. The Login button is disabled at the start.
        binding.buttonLogin.setEnabled(false);

        // 2. Attach the TextWatcher (this Activity) to both EditText fields.
        binding.editTextEmail.addTextChangedListener(this);
        binding.editTextPassword.addTextChangedListener(this);

        // 3. Clicking Login navigates to ModeActivity.
        binding.buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ModeActivity.class);
            startActivity(intent);
        });

        // 4. Custom exit logic: press back twice to exit.
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    // Second press: exit the application.
                    finish();
                    return;
                }

                // First press: show the Snackbar and wait for a second press.
                doubleBackToExitPressedOnce = true;
                Snackbar.make(binding.getRoot(), "Press back again to exit",
                        Snackbar.LENGTH_SHORT).show();

                // Reset the flag after 2 seconds.
                new Handler(Looper.getMainLooper()).postDelayed(
                        () -> doubleBackToExitPressedOnce = false, 2000);
            }
        });
    }

    // TextWatcher methods
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Not used.
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Not used.
    }

    @Override
    public void afterTextChanged(Editable s) {
        validateInputs();
    }

    // Enable the Login button only if the email is valid and the password has 6+ characters.
    private void validateInputs() {
        String email = binding.editTextEmail.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString();

        boolean isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean isPasswordValid = password.length() >= 6;

        binding.buttonLogin.setEnabled(isEmailValid && isPasswordValid);
    }
}