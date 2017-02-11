package com.example.nikolay.mytestapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

        String fName = intent.getStringExtra("fname");
        String lName = intent.getStringExtra("lname");

        TextView TxWv = (TextView) findViewById(R.id.zn_title);
        TxWv.setText(fName);

        double zn = Double.parseDouble(lName);

        //------------------------> Рисование кнопки
        Bitmap bitmap = Bitmap.createBitmap(90, 60, Bitmap.Config.ARGB_8888);

        bitmap.eraseColor(Color.WHITE);
        if(zn>0) {
            Bitmap zel = Bitmap.createBitmap((int) (90 * zn), 60, Bitmap.Config.ARGB_8888);
            zel.eraseColor(Color.GREEN);

            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(zel, 0, 0, null);
        }
        Drawable image = new BitmapDrawable(bitmap);

        Button IspButton = (Button)findViewById(R.id.zn_button);

        IspButton.setBackground(image);
        //------------------------> конец рисование кнопки
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.back) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
