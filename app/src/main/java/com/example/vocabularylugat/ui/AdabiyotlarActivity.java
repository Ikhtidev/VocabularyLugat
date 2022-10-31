package com.example.vocabularylugat.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vocabularylugat.R;
import com.example.vocabularylugat.databinding.ActivityAdabiyotlarBinding;
import com.r0adkll.slidr.Slidr;

public class AdabiyotlarActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdabiyotlarBinding binding = ActivityAdabiyotlarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Slidr.attach(this);
        //status bar va navigation bar ranglarini o'zgartirish
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPurple));
        window.setNavigationBarColor(getResources().getColor(R.color.white));
        binding.btnBackAdabiyotlar.setOnClickListener(view -> finish());
    }

    public void telegram(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/IkhtiyorYarashov")));
    }
}