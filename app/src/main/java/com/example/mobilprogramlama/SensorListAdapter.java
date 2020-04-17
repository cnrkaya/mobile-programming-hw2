package com.example.mobilprogramlama;


import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import static java.lang.String.valueOf;

public class SensorListAdapter extends BaseAdapter {
    private List<Sensor> mList;
    private Context context;
    private TextView sensorName,vendor,power,maxRange,ItemNo;
    private int lightMode;

    public SensorListAdapter(List<Sensor> mList, Context context,int light){
        this.mList = mList;
        this.context=context;
        this.lightMode = light;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = LayoutInflater.from(context).inflate(R.layout.sensor_item,parent,false);
        TextView ItemNo = (TextView) layout.findViewById(R.id.number);
        TextView sensorName = (TextView) layout.findViewById(R.id.sensorName);
        TextView vendor = (TextView) layout.findViewById(R.id.vendor);
        TextView power = (TextView) layout.findViewById(R.id.power);
        TextView maxRange = (TextView) layout.findViewById(R.id.maxRange);

        ItemNo.setText(valueOf(position+1));
        sensorName.setText("Sensör Adı: "+mList.get(position).getName());
        vendor.setText("Tedarikçi:"+mList.get(position).getVendor());
        power.setText("Güç Gereksinimi: "+valueOf(mList.get(position).getPower()));
        maxRange.setText("Maksimum Ölçüm Değeri: "+valueOf(mList.get(position).getMaximumRange()));

        int textColor=Color.BLACK; //default value
        if(lightMode == 0) {
            Log.i("lightmode burada 0 olmalı ama olan deger :  ",valueOf(lightMode));
            textColor = Color.WHITE;
        }
        else if(lightMode==1) {
            Log.i("lightmode ",valueOf(lightMode));
            textColor=Color.parseColor("#e1eefa");

        }
        else if(lightMode==2) {
            Log.i("lightmode ",valueOf(lightMode));
            textColor=Color.parseColor("#576373");
        }
        else if(lightMode==3){
            Log.i("lightmode ",valueOf(lightMode));
            textColor=Color.BLACK;
        }

        ItemNo.setTextColor(textColor);
        sensorName.setTextColor(textColor);
        vendor.setTextColor(textColor);
        power.setTextColor(textColor);
        maxRange.setTextColor(textColor);

        return layout;
    }
}
