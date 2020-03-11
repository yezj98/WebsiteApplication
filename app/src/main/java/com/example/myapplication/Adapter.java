package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<ItemActivity> arrayList, selected;
    ArrayList<CardView> cardViewList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    int row = -1;


    public interface OnItemClickListener {
        void OnItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1, textView2;

        CardView cardView;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            textView1 = itemView.findViewById(R.id.textview);
            textView2 = itemView.findViewById(R.id.textview2);
            cardView = itemView.findViewById(R.id.cardview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.OnItemClick(position);
                        }
                    }
                }
            });

        }
    }

    //Paste the item
    public Adapter(ArrayList<ItemActivity> itemList) {
        arrayList = itemList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        selected = new ArrayList<>();

        final ItemActivity itemActivity = arrayList.get(position);


//        holder.imageView.setImageResource(itemActivity.getMimageResource());
        holder.textView1.setText(itemActivity.getmText1());
        holder.textView2.setText(itemActivity.getmText2());

        Glide.with(holder.imageView.getContext()).load(itemActivity.getMimageResource())
                .dontAnimate().into(holder.imageView);


    }

    //The size of the list
    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
