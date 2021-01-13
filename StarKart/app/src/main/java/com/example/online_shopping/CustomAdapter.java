package com.example.online_shopping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import java.util.*;

public class CustomAdapter extends BaseAdapter {
    ArrayList<ArrayList<String>> details;
    int[] images;
    Context context;
    LayoutInflater inflater;

    CustomAdapter(Context applicationContext,ArrayList<ArrayList<String>> item,int[] image)
    {
        this.context=context;
        this.details=item;
        this.images=image;
        inflater=(LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount()
    {
        return details.size();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewgroup) {
        view=inflater.inflate(R.layout.customlist,null);
        TextView name= view.findViewById(R.id.textview);
        ImageView icon=view.findViewById(R.id.imageview);
        TextView price=view.findViewById(R.id.textview2);
        name.setText(details.get(i).get(1));
        price.setText("Rs "+details.get(i).get(2));
        icon.setImageResource(images[Integer.parseInt(details.get(i).get(0))-1]);
        return view;
    }
}
