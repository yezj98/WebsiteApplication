package com.example.myapplication;

import android.Manifest;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.AlreadyBoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ItemActivity> itemList;
    ArrayList<Integer> selected;
    int x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();
        selected = new ArrayList<>();
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);

        create();
        build();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();

            }
        });


    }

    public void dialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this).setTitle("Add website link");

        final EditText editText = new EditText(this);
        builder.setView(editText);


        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String websiteLink = editText.getText().toString();
                Log.d ("name1", "" +websiteLink);
                addItem(websiteLink);
            }
        }).setNegativeButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }

    public void addItem(String name) {

        String http = "https://";

        itemList.add(new ItemActivity(http + name + "/favicon.ico".replaceAll("", ""), websiteTitle(http + name), http + name));
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
        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder drag, @NonNull RecyclerView.ViewHolder target) {

                int dragPosition = drag.getAdapterPosition();
                int targetPosition = target.getAdapterPosition();

                Collections.swap(itemList, dragPosition, targetPosition);
                adapter.notifyItemMoved(dragPosition,targetPosition);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        touchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort:
                Collections.sort(itemList, new Comparator<ItemActivity>() {
                    @Override
                    public int compare(ItemActivity itemActivity, ItemActivity t1) {
                        return itemActivity.getmText1().compareTo(t1.getmText1());
                    }
                });
                adapter.notifyItemRangeChanged(0, itemList.size());

        }
        return super.onOptionsItemSelected(item);
    }


}