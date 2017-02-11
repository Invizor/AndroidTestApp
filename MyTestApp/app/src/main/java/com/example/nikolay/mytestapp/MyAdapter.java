package com.example.nikolay.mytestapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.PrintStream;
import java.util.List;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    public List<ItemData> itemsData;
    Context cont;

    public MyAdapter(List<ItemData> itemsData,Context cont) {
        this.itemsData = itemsData;
        this.cont = cont;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, null);


        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.txtViewTitle.setText(itemsData.get(position).getTitle());
        viewHolder.imgViewIcon.setImageResource(R.drawable.strelochka);

        //----------------------------------> Рисование градиента кнопок
        Drawable image = itemsData.get(position).draw_button();

        viewHolder.IspButton.setBackground(image);
        //-------------------------------> Конец Рисования
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtViewTitle;
        public ImageView imgViewIcon;
        public Button IspButton;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.item_title);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.item_icon);
            IspButton = (Button) itemLayoutView.findViewById(R.id.item_button);


            if(cont instanceof Main3Activity)return;
            IspButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String sop=(String) txtViewTitle.getText();
                    int zn = Integer.parseInt(sop)-1;
                    ItemData elem = itemsData.get(zn);

                    Intent intent = new Intent(cont, Main2Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    String etFName;
                    double etLName;
                    etFName = elem.getTitle();
                    etLName = elem.getknop();
                    intent.putExtra("fname", etFName);
                    intent.putExtra("lname", Double.toString(etLName));
                    cont.startActivity(intent);

                }
            });

        }
    }


    @Override
    public int getItemCount() {
        return itemsData.size();
    }
}
