package com.example.nikolay.mytestapp;

import android.app.ActionBar;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list_activ3 = new ArrayList<String>();
    List<ItemData> itemsData = new ArrayList<ItemData>();

    @Override
    public Context getBaseContext() {
        return super.getBaseContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        for(int i=1;i<=100;i++) {
            String str;
            str = Integer.toString(i);
            itemsData.add(new ItemData(str,0));
        }

        Obnovlenie_spiska();

    }

    protected void Obnovlenie_spiska(){
        RecyclerView recyclerView_ob = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView_ob.setLayoutManager(new LinearLayoutManager(this));
        Context cont=getBaseContext();

        MyAdapter mAdapter = new MyAdapter(itemsData,cont);
        recyclerView_ob.setAdapter(mAdapter);
        recyclerView_ob.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.button_settings) {
            Intent intent = new Intent(this, Main3Activity.class);
            intent.putExtra("ListString",list_activ3);
            startActivityForResult(intent,1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data== null) {return;}
        if(resultCode != RESULT_CANCELED) {
            if (requestCode == 1) {
               ArrayList<String> items = (ArrayList<String>) data.getStringArrayListExtra("name");
                list_activ3 = (ArrayList<String>) data.getStringArrayListExtra("name2");
                for (int i = 0; i < items.size(); i++) {
                    String pol = items.get(i);
                    int zn = pol.indexOf("#");
                    if (zn == -1) continue;
                    String l, r;
                    l = pol.substring(0, zn);
                    r = pol.substring(zn + 1, (int) pol.length());
                    ItemData elem = itemsData.get(Integer.parseInt(l)-1);
                    elem.setknop(Double.parseDouble(r));
                    itemsData.set(Integer.parseInt(l)-1, elem);
                    Obnovlenie_spiska();
                }
            }
        }
    }

}

