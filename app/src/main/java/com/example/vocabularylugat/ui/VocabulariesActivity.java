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
import com.example.vocabularylugat.adapters.VocabularyAdapter;
import com.example.vocabularylugat.databinding.ActivityVocabulariesBinding;
import com.example.vocabularylugat.model.Vocabulary;
import com.example.vocabularylugat.utils.UploadVocabularies;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;

public class VocabulariesActivity extends AppCompatActivity {

    private ActivityVocabulariesBinding binding;
    private ArrayList<Vocabulary> lugat;
    private VocabularyAdapter adapter;
    private boolean isQuit = true;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVocabulariesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Slidr.attach(this);

        //status bar va navigation bar ranglarini o'zgartirish
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPurple));
        window.setNavigationBarColor(getResources().getColor(R.color.white));

        binding.btnBackVocabularies.setOnClickListener(v-> finish());

        lugat = new UploadVocabularies(getApplicationContext()).uploadData();
        adapter = new VocabularyAdapter(lugat, vocabulary -> {

            binding.itemRu.setText(vocabulary.getWord_ru());
            binding.itemUz.setText(vocabulary.getWord_uz());
            binding.itemEng.setText(vocabulary.getWord_eng());
            binding.recyclerView.setVisibility(View.INVISIBLE);
            binding.itemVocabulary.setVisibility(View.VISIBLE);
            isQuit = false;
        });
        binding.recyclerView.setAdapter(adapter);
        adapter.setVocabularies(lugat);

        binding.editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.itemVocabulary.setVisibility(View.INVISIBLE);
                binding.recyclerView.setVisibility(View.VISIBLE);
                filter(editable.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (isQuit) finish();
        else {
            binding.itemVocabulary.setVisibility(View.INVISIBLE);
            binding.recyclerView.setVisibility(View.VISIBLE);
            isQuit = true;
        }
    }

    private void filter(String text) {

        ArrayList<Vocabulary> filteredList = new ArrayList<>();
        for (Vocabulary item: lugat){
            if (item.getWord_ru().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.setVocabularies(filteredList);
    }
}