package com.mobileappscompany.tedrmitchell.listviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Android1 on 3/4/2015.
 */
public class CountryArrayAdapter extends ArrayAdapter<String> {

    Context curContext;
    ArrayList<String> countries;

    public CountryArrayAdapter(Context context, int resource, int textViewResourceId, ArrayList<String> strings) {
        super(context, resource, textViewResourceId, strings);
        curContext = context;
        countries = strings;
    }

    @Override
    public int getViewTypeCount() {
        // you have two different row types
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // You need to tell to adapter which row is needed at given position
        return position % 2;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            if(getItemViewType(position) == 0) {
                row = ((LayoutInflater) curContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_item_left, parent, false);
            }
            else {
                row = ((LayoutInflater) curContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_item_right, parent, false);
            }
        }

        ImageView countryImage = (ImageView) row.findViewById(R.id.country_image);
        TextView tv1 = (TextView) row.findViewById(R.id.country_name);
        TextView tv2 = (TextView) row.findViewById(R.id.language);


        tv1.setText(countries.get(position));
        tv2.setText("something");

        if(countries.get(position).equals("Canada")) {
            countryImage.setImageResource(R.drawable.canada);
        }
        if(countries.get(position).equals("Austria")) {
            countryImage.setImageResource(R.drawable.austria);
        }
        if(countries.get(position).equals("England")) {
            countryImage.setImageResource(R.drawable.england);
        }
        if(countries.get(position).equals("Germany")) {
            countryImage.setImageResource(R.drawable.germany);
        }
        if(countries.get(position).equals("Mexico")) {
            countryImage.setImageResource(R.drawable.mexico);
        }
        if(countries.get(position).equals("Portugal")) {
            countryImage.setImageResource(R.drawable.portugal);
        }
        if(countries.get(position).equals("Spain")) {
            countryImage.setImageResource(R.drawable.spain);
        }
        if(countries.get(position).equals("United States")) {
            countryImage.setImageResource(R.drawable.unitedstates);
        }

        return row;
    }
}
