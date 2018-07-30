package com.wondrousapps.spanishtoenglish.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondrousapps.spanishtoenglish.R;
import com.wondrousapps.spanishtoenglish.model.Firebasemodel;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder>{

    Context context;
    List<Firebasemodel> firebasemodelList;

    public DetailAdapter(Context context, List<Firebasemodel> firebasemodelList) {

        this.context = context;
        this.firebasemodelList = firebasemodelList;

    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.detail_items,null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new DetailViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int i) {

        Firebasemodel firebasemodel=firebasemodelList.get(i);
        detailViewHolder.span_tv.setText(" "+firebasemodel.getSpaphrases());
        detailViewHolder.eng_tv.setText(" "+firebasemodel.getEngphrases());

    }

    @Override
    public int getItemCount() {
        return firebasemodelList.size();
    }

    class DetailViewHolder extends RecyclerView.ViewHolder{
        TextView eng_tv,span_tv;

        public DetailViewHolder(@NonNull View itemView) {

            super(itemView);
            eng_tv=itemView.findViewById(R.id.eng_tv);
            span_tv=itemView.findViewById(R.id.span_tv);

        }
    }
}
