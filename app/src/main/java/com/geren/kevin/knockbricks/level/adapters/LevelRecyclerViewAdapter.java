package com.geren.kevin.knockbricks.level.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geren.kevin.knockbricks.R;

import java.util.ArrayList;
import java.util.List;

public class LevelRecyclerViewAdapter extends RecyclerView.Adapter<LevelRecyclerViewAdapter.LevelRecyclerViewViewHolder> implements View.OnClickListener {

    public static final String TAG = LevelRecyclerViewViewHolder.class.getSimpleName();
    private List<String> levelList;
    private RecyclerView myRecycler;
    private Context context;
    private LayoutInflater inflater;
    private LevelAdapterClickedListener listener;

    public LevelRecyclerViewAdapter(Context context, RecyclerView myRecycler) {
        this.levelList = new ArrayList<>();
        this.context = context;
        this.myRecycler = myRecycler;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LevelRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_level, viewGroup, false);
        view.setOnClickListener(this);
        return new LevelRecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelRecyclerViewViewHolder levelRecyclerViewViewHolder, int i) {
        String s = levelList.get(i);
        TextView tv = levelRecyclerViewViewHolder.tv;
        tv.setText(s);
    }

    @Override
    public int getItemCount() {
        return levelList.size();
    }

    public void refresh(List<String> list) {
        if (list != null && list.size() > 0) {
            this.levelList.clear();
            this.levelList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void setOnLevelAdapterCloickListener(LevelAdapterClickedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        int position = myRecycler.getChildAdapterPosition(v);
        if (listener != null) {
            listener.levelAdapterClicked(position);
        }
    }

    public interface LevelAdapterClickedListener {
        void levelAdapterClicked(int position);
    }

    public class LevelRecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public LevelRecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_item_level);
        }

    }
}
