package com.evanglazer.tellmethisdeal;

import android.app.Activity;
import android.app.ListActivity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by Evan on 12/21/2015.
 */
public class state_city extends Activity {
    private ListView state;
    private ListView city;
    static final String[] STATES = new String[]{"Florida","Penn"};
    //static final String[] CITIES;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // no more this
        setContentView(R.layout.state_city_screen);

       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.row_layout, STATES);


        state = (ListView) findViewById(R.id.listView1);
        state.setAdapter(adapter);
        state.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = state.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), item + " selected", Toast.LENGTH_LONG).show();
            }
        });

    }

}
