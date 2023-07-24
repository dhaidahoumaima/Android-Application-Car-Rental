package com.example.oumaima.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.Spinner;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.util.UUID;

import android.app.ProgressDialog;

public class tabhost extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{
    TabHost tabHost;
    String[] country = {"Mark" ,"DACIA", "CLIO", "4*4","FORD","Nessan"};
    String[] ville = { "City", "Agadir", "AL HAJEB", "AL HOCEIMA", "AZILAL","BENI MELLAL","BOUJDOUR","CASABLANCA","ESSAOUIRA","MARRAKECH","TETOUAN"};
    String[] Boite_vitesse = { "Boite de vitesses", "MANUELLE", "AUTOMATIQUE"};
    Spinner spinner,spinner2,spinner3;
    Button button;
    MaterialEditText name,phone,price,description,model;
    FirebaseDatabase database;
    DatabaseReference ref;
    Voiture voiture;
    //static int nbr=0;
    ImageButton image;
    int PICK_IMAGE_REQUEST = 111;

    StorageReference imagepath;
    private StorageReference Mstorage;
    Uri uri;
    Button chooseImg, uploadImg;
    ImageView imgView;
    ProgressDialog pd;
    StorageReference childRef;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://applicatioon-1af4c.appspot.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);

        name=(MaterialEditText) findViewById(R.id.namex);
        phone=(MaterialEditText) findViewById(R.id.phonex);
        price=(MaterialEditText) findViewById(R.id.pricex);
        description=(MaterialEditText) findViewById(R.id.descriptionx);
        model=(MaterialEditText) findViewById(R.id.modelx);
        button=(Button) findViewById(R.id.buttonx);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("Annonce");



        Mstorage=FirebaseStorage.getInstance().getReference();
        voiture=new Voiture();
        spinner=(Spinner) findViewById(R.id.spinner) ;
        spinner2=(Spinner) findViewById(R.id.spinner2) ;
        spinner3=(Spinner) findViewById(R.id.spinner3) ;




        chooseImg = (Button)findViewById(R.id.chooseImg);
        uploadImg = (Button)findViewById(R.id.uploadImg);
        imgView = (ImageView)findViewById(R.id.imgView);

        pd = new ProgressDialog(this);
        pd.setMessage("Uploading....");


        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key =  ref.push().getKey();

                if(uri != null) {
                    pd.show();

                    childRef = storageRef.child(key);

                    //uploading the image
                    UploadTask uploadTask = childRef.putFile(uri);

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            pd.dismiss();
                            Toast.makeText(tabhost.this, "Upload successful", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(tabhost.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(tabhost.this, "Select an image", Toast.LENGTH_SHORT).show();
                }
            }
        });




        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Annonce");
        host.addTab(spec);


        //Tab 3
        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Compte");
        host.addTab(spec);


        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner

        spin.setAdapter(aa);



        Spinner spine = (Spinner) findViewById(R.id.spinner2);
        spine.setOnItemSelectedListener(this);
        //Creting the ArrayAdapter instance having the country list
        ArrayAdapter aaa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ville);
        aaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spine.setAdapter(aaa);



        Spinner spinee = (Spinner) findViewById(R.id.spinner3);
        spinee.setOnItemSelectedListener(this);
        //Creting the ArrayAdapter instance having the country list
        ArrayAdapter aaaa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Boite_vitesse);
        aaaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinee.setAdapter(aaaa);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();

            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                //Setting image to ImageView
                imgView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void getData(){

        voiture.setName(name.getText().toString());
        voiture.setPhone(phone.getText().toString());
        voiture.setPrice(price.getText().toString());
        voiture.setImage(childRef.toString());
        voiture.setMarque(spinner.getSelectedItem().toString());
        voiture.setVille(spinner2.getSelectedItem().toString());
        voiture.setBoite_vitesse(spinner3.getSelectedItem().toString());
        voiture.setDescription(description.getText().toString());
        voiture.setModel(model.getText().toString());




    }


    public void buttonok(View view) {

        ref.addValueEventListener(new ValueEventListener() {
            String key =  ref.push().getKey();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getData();
                ref.child(key).setValue(voiture);
                Toast.makeText(tabhost.this, "Succeful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(tabhost.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
