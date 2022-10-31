package com.example.vocabularylugat.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.vocabularylugat.R;
import com.example.vocabularylugat.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySecondBinding binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.vocabulary.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(), VocabulariesActivity.class);
            startActivity(intent);
        });

        binding.termens.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(), TermsActivity.class);
            startActivity(intent);
        });

        binding.libraries.setOnClickListener(view1 -> {
            Intent intent = new Intent(getApplicationContext(), AdabiyotlarActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        quit();
    }

    private void quit() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setIcon(R.drawable.ic_round_help_24);

        alert.setTitle(R.string.warning);

        alert.setMessage(getString(R.string.do_you_want_to_exit));

        alert.setPositiveButton(getString(R.string.answer_yes), (dialogInterface, i) -> {
//                dasturdan chiqib ketadi
            finish();
        });

        alert.setNegativeButton(getString(R.string.answer_no), (dialogInterface, i) -> {
//                e'tiborsiz o'tib ketadi
        });

        AlertDialog dialog = alert.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.custom_red));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.custom_blue));

    }

}