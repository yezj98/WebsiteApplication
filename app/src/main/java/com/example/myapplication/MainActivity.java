package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ItemActivity> itemList;
    String website;

    EditText insert_edit;
    Button insert_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert_button = findViewById(R.id.button);
        insert_edit = findViewById(R.id.editText);



        create();
        build();


        insert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                website = insert_edit.getText().toString();
                addItem(website);
            }
        });







    }

    public void addItem (String name) {
        itemList.add(new ItemActivity(R.drawable.ic_android, "title", name));
    }

    public void create () {
        itemList = new ArrayList<>();
        itemList.add(new ItemActivity(R.drawable.ic_android, "The title", "website"));
        itemList.add(new ItemActivity(R.drawable.ic_android, "The title", "website"));
        itemList.add(new ItemActivity(R.drawable.ic_android, "The title", "website"));
    }

    public void build() {
        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Adapter(itemList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }
}
