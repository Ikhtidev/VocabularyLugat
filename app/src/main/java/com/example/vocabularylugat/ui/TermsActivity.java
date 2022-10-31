package com.example.vocabularylugat.ui;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vocabularylugat.R;
import com.example.vocabularylugat.adapters.TermAdapter;
import com.example.vocabularylugat.databinding.ActivityTermsBinding;
import com.example.vocabularylugat.model.Term;
import com.example.vocabularylugat.utils.UploadTerms;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;

public class TermsActivity extends AppCompatActivity {

    private ActivityTermsBinding binding;
    private ArrayList<Term> terms;
    private TermAdapter adapter;
    private boolean isQuit = true;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTermsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Slidr.attach(this);

        //status bar va navigation bar ranglarini o'zgartirish
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPurple));
        window.setNavigationBarColor(getResources().getColor(R.color.white));

        binding.btnBackVocabularies.setOnClickListener(v-> finish());

        terms = new UploadTerms(getApplicationContext()).uploadData();
        adapter = new TermAdapter(terms, term -> {

            binding.termTitle.setText(term.getTerm_title());
            binding.termText.setText(term.getTerm_text());
            binding.recyclerView.setVisibility(View.INVISIBLE);
            binding.itemTerm.setVisibility(View.VISIBLE);
            isQuit = false;
        });
        binding.recyclerView.setAdapter(adapter);
        adapter.setTerms(terms);

        binding.editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.itemTerm.setVisibility(View.INVISIBLE);
                binding.recyclerView.setVisibility(View.VISIBLE);
                filter(editable.toString());
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isQuit) finish();
        else {
            binding.itemTerm.setVisibility(View.INVISIBLE);
            binding.recyclerView.setVisibility(View.VISIBLE);
            isQuit = true;
        }
    }

    private void filter(String text) {

        ArrayList<Term> filteredList = new ArrayList<>();
        for (Term item: terms){
            if (item.getTerm_title().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.setTerms(filteredList);
    }
}