
package com.evanglazer.tellmethisdeal;


import android.app.Activity;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Evan on 12/21/2015.
 */
public class state_city extends ListActivity {
    ListView state;
    state_adapter adapter;
    List<pojo_states> states;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_city_screen);

        state = (ListView) findViewById(R.id.listView1);
        adapter = new state_adapter(this, R.layout.row_layout, states);
        setListAdapter(adapter);

        // need to set data states to equal the parsed data

        state.setAdapter(adapter);

        state.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = state.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), item + " selected", Toast.LENGTH_LONG).show();

            }
        });

    }

    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            // starting task
            //requestData("http://services.hanselandpetal.com/secure/flowers.json");

        }

        @Override
        protected String doInBackground(String... params) {
            //String content = url_con.getData(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
           // states = json_states.parseFeed(result);

        }

        @Override
        protected void onProgressUpdate(String... values) {
        }
    }
}

