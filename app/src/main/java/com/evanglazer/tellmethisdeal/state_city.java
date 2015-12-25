
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Evan on 12/21/2015.
 */
public class state_city extends Activity {
    ListView ListView;
    ProgressBar pb;
    Button data;
    ArrayList<pojo_states> pojo;
    List<MyTask> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_city_screen);
        data = new Button(this);
        data = (Button) findViewById(R.id.data);

        tasks = new ArrayList<>();

        state_adapter adapter = new state_adapter(this, pojo);
        ListView.setAdapter(adapter);

        if (isOnline()) {
            requestData("http://api.sba.gov/geodata/primary_city_county_links_for_state_of/FL.json");
        } else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }
        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), pojo.get(position).mName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected void updateDisplay() {

        state_adapter adapter = new state_adapter(this, pojo);
        ListView.setAdapter(adapter);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private class MyTask extends AsyncTask<String,String, String> {


        @Override
        protected void onPreExecute() {

            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected String doInBackground(String[] params) {

            String content = url_con.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {

            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }

            if (result == null) {
                Toast.makeText(state_city.this, "Web service not available", Toast.LENGTH_LONG).show();
                return;
            }

            pojo = json_states.parseFeed(result);
            updateDisplay();

        }

    }

}
