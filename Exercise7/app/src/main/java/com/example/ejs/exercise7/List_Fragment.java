package com.example.ejs.exercise7;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class List_Fragment extends Fragment {
    private TextView textView1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_layout, container, false);
        textView1 = view.findViewById(R.id.textView1);
        return view;
    }

    public void showAuthorAndBooksToList(ArrayList<String> list) {
        StringBuffer res = new StringBuffer("");
        for (String s : list) {
            res.append(s+"\n");
        }
        textView1.setText(res);
    }

    public void showAuthors(ArrayList<String> list) {
        StringBuffer res = new StringBuffer("");
        for (String s : list) {
            res.append(s+"\n");
        }
        textView1.setText(res);
    }

    public void showBooks(ArrayList<String> list) {
        StringBuffer res = new StringBuffer("");
        for (String s : list) {
            res.append(s+"\n");
        }
        textView1.setText(res);
    }

    public void changeBackgroundColor(String color){
        textView1.setBackgroundColor(Color.parseColor(color));
    }
}
