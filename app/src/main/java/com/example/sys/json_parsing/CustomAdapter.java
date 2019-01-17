package com.example.sys.json_parsing;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Listdata> {
    ArrayList <Listdata> listdata;
    Context context;
    int resource;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList <Listdata> listdata) {
        super( context, resource, listdata );
        this.listdata = listdata;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService( Activity.LAYOUT_INFLATER_SERVICE );
            convertView = layoutInflater.inflate( R.layout.layout, null, true );
        }
        Listdata listdata=getItem(position);
        ImageView img=(ImageView)convertView.findViewById(R.id.img);
        Picasso.get()
                .load(listdata.getImage_url())
                .into(img);
        TextView title=(TextView)convertView.findViewById(R.id.title);
        title.setText(listdata.getTitle());
        TextView des=(TextView)convertView.findViewById(R.id.name);
        des.setText(listdata.getDes());
        return convertView;
    }
}

