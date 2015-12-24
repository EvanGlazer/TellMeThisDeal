package com.evanglazer.tellmethisdeal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Evan on 12/24/2015.
 */


public class state_adapter extends ArrayAdapter<pojo_states> {
    Context mContext;
    List<pojo_states> mStates;
    LayoutInflater mInflater;

    public state_adapter(Context c, int resource, List<pojo_states> states)
    {
        super(c,resource,states);
        mContext = c;
        mStates = states;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_layout, parent, false);

        //Display flower name in the TextView widget
        pojo_states states = mStates.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textview);
        tv.setText(states.getState_name());

        return view;
    }
}
