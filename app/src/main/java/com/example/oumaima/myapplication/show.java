package com.example.oumaima.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;

import java.util.List;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class show extends AppCompatActivity {
    List<Voiture> userslist;
    ListView userlistview;
    EditText theFilter;

    DatabaseReference databaseUser;
    UserList userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        init();


    }
    public void init() {



        userlistview = (ListView) findViewById(R.id.list);

        userslist = new ArrayList<>();



        databaseUser = FirebaseDatabase.getInstance().getReference("Annonce");

    }
    @Override
    protected void onStart() {
        super.onStart();

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userslist.clear();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){

                    Voiture user = postSnapshot.getValue(Voiture.class);

                    userslist.add(user);
                }

                userAdapter= new UserList(show.this, userslist);
                userlistview.setAdapter(userAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}
