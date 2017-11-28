package com.example.samim.to_do_application;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by SAMIM on 11/15/2017.
 */

public class Item_Adapter extends ArrayAdapter {
    List list=new ArrayList();


    public Item_Adapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Item object) {
        super.add(object);
        list.add(object);

    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.show_item_in_a_listview,parent,false);
            viewHolder.title=(TextView)convertView.findViewById(R.id.txtTitel);
            viewHolder.description=(TextView)convertView.findViewById(R.id.txtDescription);
            viewHolder.status=(TextView)convertView.findViewById(R.id.txtStstus);
            viewHolder.date=(TextView)convertView.findViewById(R.id.txtdate);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        Item item=(Item)getItem(position);
        viewHolder.title.setText(item.getTitle());
        viewHolder.description.setText(item.getDescription());
        viewHolder.date.setText(item.getDate());
        viewHolder.status.setText(item.getStatus());

        return convertView;
    }


    private class ViewHolder {
        TextView title,description,status,date;
    }
}
