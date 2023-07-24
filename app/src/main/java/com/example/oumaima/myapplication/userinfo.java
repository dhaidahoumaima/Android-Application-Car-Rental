package com.example.oumaima.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class userinfo extends AppCompatActivity {
    List<User> userslist;
    ListView userlistview;
    EditText theFilter;

    DatabaseReference databaseUser;
    UserList userAdapter;
    String uid;
FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        uid=user.getUid();
        init();
    }
    public void init() {



        userlistview = (ListView) findViewById(R.id.listview);

        userslist = new ArrayList<User>();



        databaseUser = FirebaseDatabase.getInstance().getReference("Users");

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userslist.clear();


                    String user_name=dataSnapshot.child(uid).child("name").getValue(String.class);

   //                 userslist.add(user_name);


                //userAdapter= new UserList(userinfo.this, userslist);
                userlistview.setAdapter(userAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}
