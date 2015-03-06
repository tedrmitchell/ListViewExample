package com.mobileappscompany.tedrmitchell.listviewexample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class ListViewActivity extends ActionBarActivity implements EditDeleteDialogFragment.IEdit, EditDeleteDialogFragment.IDelete {

    ListView list;
    TextView textView;
    ArrayList<String> countries;
    ImageView deleteImage;
    int curPosition;
    CountryArrayAdapter countryAdapter;

    @Override
    public void onEdit() {}

    @Override
    public void onDelete() {
        removeItem();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        list = (ListView) findViewById(R.id.countryList);
        textView = (TextView) findViewById(R.id.textView);
        deleteImage = (ImageView) findViewById(R.id.delete);

        countries = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.countries)));

        countryAdapter = new CountryArrayAdapter(this, R.layout.list_item_left, R.id.country_name, countries);
        list.setAdapter(countryAdapter);

        list.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                textView.setText(countries.get(position));
                deleteImage.setVisibility(View.VISIBLE);
                curPosition = position;
            }

        }));

        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(countries.get(position));
                deleteImage.setVisibility(View.VISIBLE);
                curPosition = position;
                new EditDeleteDialogFragment().show(getFragmentManager(), "Edit or Delete");
                return true;
            }
        });

        SwipeDismissListViewTouchListener touchListener = new SwipeDismissListViewTouchListener(list, new SwipeDismissListViewTouchListener.DismissCallbacks() {
            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                for (int position : reverseSortedPositions) {
                    countryAdapter.remove(countryAdapter.getItem(position));
                }
                countryAdapter.notifyDataSetChanged();
                }

            public boolean canDismiss(int id) {
                return true;
            }
            });
        list.setOnTouchListener(touchListener);
        list.setOnScrollListener(touchListener.makeScrollListener());
    }

    private void removeItem() {
        String toRemove = countryAdapter.getItem(curPosition);
        textView.setText("");
        countryAdapter.remove(toRemove);
        countryAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
