
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

/**
 * Created by Evan on 12/21/2015.
 */
public class state_city extends Activity {
    ProgressBar pb;
    TextView output;
    private ListView state;
    private ListView city;
    ArrayAdapter<String> adapter;
    List<pojo_states> states;
    List<MyTask> tasks;
    boolean flag = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // no more this
        setContentView(R.layout.state_city_screen);
        //		Initialize the TextView for vertical scrolling
        //output = (TextView) findViewById(R.id.textView);
        //output.setMovementMethod(new ScrollingMovementMethod());

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();
        isOnline();

        adapter = new ArrayAdapter<String>(this, R.layout.state_city_screen);


        state = (ListView) findViewById(R.id.listView1);
        state.setAdapter(adapter);

        requestData("http://services.hanselandpetal.com/feeds/flowers.json");
        state.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = state.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), item + " selected", Toast.LENGTH_LONG).show();

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    protected void updateDisplay() {

        if (states != null) {
            for (pojo_states s : states) {
                // add state names to listview
                adapter.add(s.getState_name() + "");
                //output.append(s.getState_name() + "\n");
            }
        }

    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                requestData("");
            return true;
        } else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            // starting task
            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);

        }

        @Override
        protected String doInBackground(String... params) {

            String content = url_con.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {

            states = json_states.parseFeed(result);
            updateDisplay();
            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
        }
    }
}

