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
import com.example.vocabularylugat.model.Term;

import java.util.ArrayList;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder>{
    private ArrayList<Term> terms = new ArrayList<>();
    private TermAdapter.ItemClickListener mItemClickListener;

    public TermAdapter(ArrayList<Term> terms, TermAdapter.ItemClickListener mItemClickListener) {
        this.terms = terms;
        this.mItemClickListener = mItemClickListener;
    }

    public void setTerms(ArrayList<Term> data) {
        this.terms = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vocabulary, parent, false);
        return new TermAdapter.TermViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Term term = terms.get(position);

        holder.ru.setText(term.getTerm_title());

        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.item_fall_down);
        holder.itemView.startAnimation(animation);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClick(terms.get(position));
            }
        });
    }

    public interface ItemClickListener{
        void onItemClick(Term term);
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    public class TermViewHolder extends RecyclerView.ViewHolder {

        TextView ru;

        public TermViewHolder(@NonNull View itemView) {
            super(itemView);
            ru = itemView.findViewById(R.id.tvRu);
        }
    }
}
