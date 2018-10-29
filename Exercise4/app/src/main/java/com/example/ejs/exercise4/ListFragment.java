package com.example.ejs.exercise4;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private ListView list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        list = view.findViewById(R.id.namelist);
        List<String> names = new ArrayList<>();


        names.add(getResources().getResourceEntryName(R.drawable.androidlogo));
        names.add(getResources().getResourceEntryName(R.drawable.applelogo));
        names.add(getResources().getResourceEntryName(R.drawable.linuxlogo));
        names.add(getResources().getResourceEntryName(R.drawable.mslogo));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, names);
        list.setAdapter(arrayAdapter);

        return view;
    }
}
