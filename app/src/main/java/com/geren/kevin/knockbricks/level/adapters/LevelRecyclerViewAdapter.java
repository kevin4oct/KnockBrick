package com.geren.kevin.knockbricks.level.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.geren.kevin.knockbricks.R;

import java.util.ArrayList;
import java.util.List;

public class LevelRecyclerViewAdapter extends RecyclerView.Adapter<LevelRecyclerViewAdapter.LevelRecyclerViewViewHolder> implements View.OnClickListener {

    public static final String TAG = "level适配器";

    private List<Integer> levelList;
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
//        view.setOnClickListener(this);
        view.findViewById(R.id.btn_item_level).setOnClickListener(this);
        return new LevelRecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelRecyclerViewViewHolder levelRecyclerViewViewHolder, int i) {
        Integer position = levelList.get(i);
        Button btn = levelRecyclerViewViewHolder.btn;
        btn.setText("第" + (position + 1) + "关");
    }

    @Override
    public int getItemCount() {
        return levelList.size();
    }

    public void refresh(List<Integer> list) {
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
        ViewGroup parent = (ViewGroup) v.getParent();
        int position = myRecycler.getChildAdapterPosition(parent);
        if (listener != null) {
            listener.levelAdapterClicked(position);
        }
    }

    public interface LevelAdapterClickedListener {
        void levelAdapterClicked(int position);
    }

    public class LevelRecyclerViewViewHolder extends RecyclerView.ViewHolder {
        Button btn;

        public LevelRecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn_item_level);
        }

    }
}
