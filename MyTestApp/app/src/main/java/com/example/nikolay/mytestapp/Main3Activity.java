package com.example.nikolay.mytestapp;

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
import android.widget.EditText;

import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;



public class Main3Activity extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<ItemData> isp = new ArrayList<ItemData>();

    ArrayList<String> ves_spisok = new ArrayList<String>(); //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity3);
        final Button button = (Button) findViewById(R.id.knopka_vvod);

        Intent intent = getIntent(); //
        ves_spisok = intent.getStringArrayListExtra("ListString");//
        for(int i=0;i<(int)ves_spisok.size();i++) {//
            String pol = ves_spisok.get(i);//
            int zn = pol.indexOf("#");//
            if (zn == -1) continue;//
            String l, r;//
            l = pol.substring(0, zn);//
            r = pol.substring(zn + 1, (int) pol.length());//
            ItemData elem = new ItemData("num",0); //
            elem.setTitle(l);//
            elem.setknop(Double.parseDouble(r));//
            isp.add(elem);
        } //


        Obnovlenie_spiska();

        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) // клик на кнопку
            {
                ItemData obnov = new ItemData("num",0);
                EditText edit1 = (EditText)findViewById(R.id.number_line);
                EditText edit2 = (EditText)findViewById(R.id.koef_zapoln);
                if (tryParseInt(edit1.getText().toString())!=null && tryParseDouble(edit2.getText().toString())!=null) {
                    if(tryParseInt(edit1.getText().toString())>=1 && tryParseInt(edit1.getText().toString())<=100 && tryParseDouble(edit2.getText().toString())>=0 && tryParseDouble(edit2.getText().toString())<=1) {
                        obnov.setTitle(edit1.getText().toString());
                        obnov.setknop(Double.parseDouble(edit2.getText().toString()));
                        isp.add(obnov);
                        String result = edit1.getText().toString() + "#" + edit2.getText().toString();
                        items.add(result);
                        ves_spisok.add(result);

                        Obnovlenie_spiska();
                    }
                    edit1.setText("");
                    edit2.setText("");
                }else{
                    edit1.setText("");
                    edit2.setText("");
                }
            }
        });

    }

    protected void Obnovlenie_spiska() {
        //----> создаем адаптер для изменяемых элементов
        RecyclerView recyclerView_ob = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView_ob.setLayoutManager(new LinearLayoutManager(this));
        Context cont=this;
        MyAdapter mAdapter = new MyAdapter(isp,cont);
        recyclerView_ob.setAdapter(mAdapter);
        recyclerView_ob.setItemAnimator(new DefaultItemAnimator());
        //---->конец создания адаптера для изменяемых элементов
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.back2) {
            Intent intent = new Intent();
            intent.putStringArrayListExtra("name", items);
            intent.putStringArrayListExtra("name2", ves_spisok);
            setResult(RESULT_OK, intent);
            finish();
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    Integer tryParseInt(String s) {
        try {
            return new Integer(s);
        } catch (NumberFormatException e) {
            return null; // не-а, не int
        }
    }
    Double tryParseDouble(String s) {
        try {
            return new Double(s);
        } catch (NumberFormatException e) {
            return null; // не-а, не double
        }
    }
}

