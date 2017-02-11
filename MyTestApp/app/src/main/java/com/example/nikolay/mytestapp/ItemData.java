package com.example.nikolay.mytestapp;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.Button;

public class ItemData {
    public String title;
    //private int imageUrl;
    public double knop;

    public ItemData(String title,double knop){
        this.title = title;
        //this.imageUrl = imageUrl;
        this.knop = knop;
    }

    public Drawable draw_button(){
        Bitmap bitmap = Bitmap.createBitmap(90, 60, Bitmap.Config.ARGB_8888);

        bitmap.eraseColor(Color.WHITE);
        if(this.knop>0) {
            Bitmap zel = Bitmap.createBitmap((int) (90 * this.knop), 60, Bitmap.Config.ARGB_8888);
            zel.eraseColor(Color.GREEN);

            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(zel, 0, 0, null);
        }
        return new BitmapDrawable(bitmap);
    }

    // getters & setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getknop() {
        return knop;
    }

    public void setknop(double knop) {
        this.knop = knop;
    }
}
