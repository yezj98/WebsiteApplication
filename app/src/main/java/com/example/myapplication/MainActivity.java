package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ItemActivity> itemList;
    String website, mWebsiteTitle;

    EditText insert_edit;
    Button insert_button;
    ImageView imageView;
    private int PermissionCode = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert_button = findViewById(R.id.button);
        insert_edit = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView1);
        Permission();

        Glide.with(this).load("http://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Apple_logo_black.svg/1200px-Apple_logo_black.svg.png")
                .dontAnimate().into(imageView);


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

    public void addItem(String name) {

        itemList.add(new ItemActivity(R.drawable.ic_android, websiteTitle(name), name));
    }

    public static String websiteTitle (String url) {
        URL mURL = null;
        try {
            mURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String domain = mURL.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain; // set the condition, if the domain start with "www." then return
    }

    public void create() {
        itemList = new ArrayList<>();


        itemList.add(new ItemActivity(R.drawable.ic_android, websiteTitle("https://www.channelnewsasia.com"), "https://www.channelnewsasia.com"));
        itemList.add(new ItemActivity(R.drawable.ic_android, websiteTitle("https://sg.yahoo.com"), "https://sg.yahoo.com"));
        itemList.add(new ItemActivity(R.drawable.ic_android, websiteTitle("https://www.google.com"), "https://www.google.com"));
    }

    public void build() {
        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Adapter(itemList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    private void Permission () {
        String [] permission ={Manifest.permission.INTERNET};
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,permission ,PermissionCode);
        }
        else {

        }
    }
}
