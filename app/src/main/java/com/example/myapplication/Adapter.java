package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter <Adapter.ViewHolder> {
    ArrayList <ItemActivity> arrayList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
         ImageView imageView;
         TextView textView1, textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            textView1 = itemView.findViewById(R.id.textview);
            textView2 = itemView.findViewById(R.id.textview2);

        }
    }
    //Paste the item
    public Adapter(ArrayList<ItemActivity> itemList ) {
        arrayList = itemList;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemActivity itemActivity = arrayList.get(position);
        holder.imageView.setImageResource(itemActivity.getMimageResource());
        holder.textView1.setText(itemActivity.getmText1());
        holder.textView2.setText(itemActivity.getmText2());
    }

    //The size of the list 
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
