package com.example.ejs.exercise3;

import android.app.Activity;
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


public class CustomAdapter extends ArrayAdapter {

    Activity activity;
    int layout;
    ArrayList<Person> arrPerson;

    public CustomAdapter(@NonNull Activity activity, @LayoutRes int layout, @NonNull ArrayList<Person> arrPerson) {
        super(activity, layout, arrPerson);

        this.activity = activity;
        this.layout = layout;
        this.arrPerson = arrPerson;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(layout, null);

        TextView name = convertView.findViewById(R.id.text_name);
        TextView birthdate = convertView.findViewById(R.id.text_birthdate);

        name.setText(arrPerson.get(position).getName());
        birthdate.setText((arrPerson).get(position).getBirthdate());

        return convertView;
    }

}