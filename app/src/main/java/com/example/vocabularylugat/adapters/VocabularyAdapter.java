package com.example.vocabularylugat.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocabularylugat.R;
import com.example.vocabularylugat.model.Vocabulary;

import java.util.ArrayList;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.VocabularyViewHolder>{

    private ArrayList<Vocabulary> vocabularies = new ArrayList<>();
    private ItemClickListener mItemClickListener;

    public VocabularyAdapter(ArrayList<Vocabulary> vocabularies, ItemClickListener mItemClickListener) {
        this.vocabularies = vocabularies;
        this.mItemClickListener = mItemClickListener;
    }

    public void setVocabularies(ArrayList<Vocabulary> data) {
        this.vocabularies = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VocabularyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vocabulary, parent, false);
        return new VocabularyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Vocabulary vocabulary = vocabularies.get(position);

        holder.ru.setText(vocabulary.getWord_ru());

        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.item_fall_down);
        holder.itemView.startAnimation(animation);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClick(vocabularies.get(position));
            }
        });
    }

    public interface ItemClickListener{
        void onItemClick(Vocabulary vocabulary);
    }

    @Override
    public int getItemCount() {
        return vocabularies.size();
    }

    public class VocabularyViewHolder extends RecyclerView.ViewHolder {

        TextView ru;

        public VocabularyViewHolder(@NonNull View itemView) {
            super(itemView);
            ru = itemView.findViewById(R.id.tvRu);
        }
    }
}
