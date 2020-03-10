package com.example.myapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
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

        itemList = new ArrayList<>();

        insert_button = findViewById(R.id.button);
        insert_edit = findViewById(R.id.editText);


        Permission();

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

        itemList.add(new ItemActivity(name + "/favicon.ico", websiteTitle(name), name));
        Collections.sort(itemList, new Comparator<ItemActivity>() {
            @Override
            public int compare(ItemActivity itemActivity, ItemActivity t1) {

                return itemActivity.getmText1().compareTo(t1.getmText1());
            }
        });

        adapter.notifyDataSetChanged();
    }

    public void removeItem (int position) {
        itemList.remove(position);
        Collections.sort(itemList, new Comparator<ItemActivity>() {
            @Override
            public int compare(ItemActivity itemActivity, ItemActivity t1) {

                return itemActivity.getmText1().compareTo(t1.getmText1());
            }
        });

        adapter.notifyItemRemoved(position);
    }

    public static String websiteTitle(String url) {
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

        itemList.add(new ItemActivity("https://www.channelnewsasia.com/favicon.ico", websiteTitle("https://www.channelnewsasia.com"), "https://www.channelnewsasia.com"));
        itemList.add(new ItemActivity("https://www.sg.yahoo.com/favicon.ico", websiteTitle("https://sg.yahoo.com"), "https://sg.yahoo.com"));
        itemList.add(new ItemActivity("https://www.google.com/favicon.ico", websiteTitle("https://www.google.com"), "https://www.google.com"));
        Log.d("itemlist", "" + itemList);

        Collections.sort(itemList, new Comparator<ItemActivity>() {
            @Override
            public int compare(ItemActivity itemActivity, ItemActivity t1) {
                return itemActivity.getmText1().compareTo(t1.getmText1());
            }
        });
    }

    public void build() {
        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Adapter(itemList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void OnItemClick(final int position) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setTitle("Setting")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                removeItem(position);
                            }
                        }).setNegativeButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                            }
                        }).show();

            }
        });

    }

    private void Permission() {
        String[] permission = {Manifest.permission.INTERNET};
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, permission, PermissionCode);
        } else {

        }
    }

    public static Drawable LoadPicture(String url) {
        try {
            InputStream inputStream = (InputStream) new URL(url).getContent();
            Drawable drawable = Drawable.createFromStream(inputStream, "src name");
            return drawable;
        } catch (Exception e) {
            return null;
        }

    }
}
