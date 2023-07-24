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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {


    //defining views
    private Button buttonSignIn,b1;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;


    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1=(Button) findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a1=new Intent(login.this,signup.class);
                startActivity(a1);

            }
        });


        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the objects getcurrentuser method is not null
        //means user is already logged in
       /*if(firebaseAuth.getCurrentUser() == null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }*/


        //initializing views
        editTextEmail = (EditText) findViewById(R.id.txt1);
        editTextPassword = (EditText) findViewById(R.id.txt2);
        buttonSignIn = (Button) findViewById(R.id.button3);

        progressDialog = new ProgressDialog(this);
        progressBar=(ProgressBar) findViewById(R.id.br);


        //attaching click listener
        buttonSignIn.setOnClickListener(this);
        progressBar.setVisibility(View.INVISIBLE);

    }

    //method for user login
    private void userLogin(){
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
        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), tabhost.class));
                            progressBar.setVisibility(View.VISIBLE);

                        }
                        else
                            affcihe();





                    }
                });



    }
    public void affcihe()
    {
        Toast.makeText(this,"Password or Email Not Match",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            userLogin();
        }


    }
}




