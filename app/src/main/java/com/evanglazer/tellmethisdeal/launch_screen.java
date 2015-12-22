package com.evanglazer.tellmethisdeal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class launch_screen extends Activity{
    private Button deals;
    private Button sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        deals = (Button) findViewById(R.id.deals);
        sell = (Button) findViewById(R.id.sell);

        deals.setOnClickListener(handler);
        sell.setOnClickListener(handler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    View.OnClickListener handler = new View.OnClickListener(){

        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.deals:
                    Intent state = new Intent(launch_screen.this, state_city.class);
                    startActivity(state);
                    break;
                case R.id.sell:
                    Intent setup = new Intent(launch_screen.this, sell_setup.class);
                    startActivity(setup);
                    break;
                default:
                    break;
            }
        }
    };



}

