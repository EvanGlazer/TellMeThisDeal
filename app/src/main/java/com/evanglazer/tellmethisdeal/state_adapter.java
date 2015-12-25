package com.evanglazer.tellmethisdeal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 12/24/2015.
 */


public class state_adapter extends BaseAdapter {
    Context mContext;
    ArrayList<pojo_states> mPies;
    LayoutInflater mInflater;

    public state_adapter(Context c, ArrayList<pojo_states> pies)
    {
        mPies = pies;
        mContext = c;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mPies.size();
    }

    @Override
    public Object getItem(int position) {
        return mPies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = mInflater.inflate(R.layout.row_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.textName = (TextView) convertView.findViewById(R.id.textName);
            viewHolder.textabv = (TextView) convertView.findViewById(R.id.textabv);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        pojo_states currentState = mPies.get(position);
        viewHolder.textName.setText(currentState.mName);
        viewHolder.textabv.setText(currentState.mabv);
        return convertView;
    }

}