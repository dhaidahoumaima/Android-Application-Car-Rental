package com.example.oumaima.myapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class signup extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private MaterialEditText editTextEmail;
    private MaterialEditText editTextPassword,editTextPrenom,editTextnom,editTextPhone,editTextPasswordagain;
    private Button buttonSignup;
    private ProgressDialog progressDialog;

    FirebaseDatabase database;
    DatabaseReference ref;

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    User user=new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();


        database=FirebaseDatabase.getInstance();
        ref=database.getReference("Users");
        //progressBar.setVisibility(View.INVISIBLE);

        //initializing views
        editTextEmail = (MaterialEditText) findViewById(R.id.email1);
        editTextPassword = (MaterialEditText) findViewById(R.id.pass1);
        editTextnom=(MaterialEditText) findViewById(R.id.name1);
        editTextPrenom=(MaterialEditText) findViewById(R.id.prenom1);
        editTextPhone=(MaterialEditText) findViewById(R.id.phone1);
        //editTextPasswordagain=(MaterialEditText) findViewById(R.id.pass2);



        buttonSignup = (Button) findViewById(R.id.btn5);

        progressDialog = new ProgressDialog(this);
        //attaching listener to button
        buttonSignup.setOnClickListener(this);
    }


    private void registerUser(){


        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }



            //if the email and password are not empty
            //displaying a progress dialog

            progressDialog.setMessage("Registering Please Wait...");
            progressDialog.show();

            //creating a new user
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            String key =  ref.push().getKey();

                            if(task.isSuccessful()){
                                //display some message here

                                Toast.makeText(signup.this,"Successfully registered",Toast.LENGTH_LONG).show();
                                getData();
                                ref.child(key).setValue(user);


                            }else{
                                //display some message here
                                Toast.makeText(signup.this,"Registration Error",Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });



    }
    void getData(){
        user.setEmail(editTextEmail.getText().toString());
        user.setPhone(editTextPhone.getText().toString());
        user.setPrenom(editTextPrenom.getText().toString());
        user.setNom(editTextnom.getText().toString());


    }
    @Override
    public void onClick(View view) {
        //calling register method on click

        registerUser();
        //progressBar.setVisibility(View.VISIBLE);




    }
}
