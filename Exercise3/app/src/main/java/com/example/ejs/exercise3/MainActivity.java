package com.example.ejs.exercise3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int arrIndex;
    ListView list;
    EditText editName, editBirthdate;
    Button btnAdd, btnEdit, btnDelete;
    ArrayList<Person> arrPerson;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateLayout();
        arrPerson = new ArrayList<Person>();
        arrPerson.add(new Person("Eirik Stensen", "05-01-1995"));

        adapter = new CustomAdapter(this, R.layout.item_layout, arrPerson);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrIndex = position;
                editName.setText(arrPerson.get(position).getName());
                editBirthdate.setText(arrPerson.get(position).getBirthdate());
            }
        });
    }

    private void initiateLayout(){
        list = findViewById(R.id.list);
        editName = findViewById(R.id.editName);
        editBirthdate = findViewById(R.id.editBirthdate);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String name = "";
        String birthdate = "";

        switch(v.getId()) {
            case R.id.btnAdd:
                name = editName.getText().toString();
                birthdate = editBirthdate.getText().toString();

                arrPerson.add(new Person(name, birthdate));
                adapter.notifyDataSetChanged();
                setEditEmpty();
                break;
            case R.id.btnDelete:
                try {
                    arrPerson.remove(arrIndex);
                    adapter.notifyDataSetChanged();
                    arrIndex = -1;

                    setEditEmpty();
                    break;
                } catch (Exception e){
                    Toast.makeText(this, "No person from list selected, try again", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.btnEdit:
                try {
                    name = editName.getText().toString();
                    birthdate = editBirthdate.getText().toString();

                    arrPerson.set(arrIndex, new Person(name, birthdate));
                    adapter.notifyDataSetChanged();
                    arrIndex = -1;

                    setEditEmpty();
                    break;
                } catch (Exception e){
                    Toast.makeText(this, "No person from list selected, try again", Toast.LENGTH_SHORT).show();
                    break;
                }
        }
    }

    private void setEditEmpty() {
        editName.setText("");
        editBirthdate.setText("");
    }
}