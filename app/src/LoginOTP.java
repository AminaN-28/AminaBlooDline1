package com.example.blood_line;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginOTP extends AppCompatActivity {
    /*private FirebaseAuth mAuth; //Create an instance of FirebaseAuth

    private FirebaseUser mCurrentUser;//for member variable

    private String mAuthCredential;*/
    Button continubtn;
    EditText otpNum;

    BL_DATABASE base;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_otp);


        final String nom = getIntent().getStringExtra("nom");
        final String Phone=getIntent().getStringExtra("phonenumber") ;
        final String GroupS= getIntent().getStringExtra("groupS");
        final String age= getIntent().getStringExtra("age");
        final String reg= getIntent().getStringExtra("region");
        final String  mdp= getIntent().getStringExtra("password");


       // SQLiteDatabase db = base.getWritableDatabase();



        continubtn=findViewById(R.id.continu_btn);
        otpNum=findViewById(R.id.verify1);
      continubtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (otpNum.getText().toString().length() == 0)
                {
                    otpNum.setError("Saisissez le code de verification recu");
                }
                else
                {// Toast.makeText(LoginOTP.this, "VERIFICATION SUCCESS", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginOTP.this,Home_BL.class);
                    startActivity(intent);

                    SQLiteDatabase db = base.getWritableDatabase();

                    ContentValues values = new ContentValues();

                    values.put("nom",nom);

                    values.put("phone",Phone);

                    values.put("groupeS",GroupS);

                    values.put("region",reg);

                    values.put("pwd", mdp);

                    values.put("age",Integer.valueOf(age) );

                    long a = db.insert("utilisateur",null,values);//si le a retourne -1 l'insertion cest pas bien passé
                    //retourne le nombrre d'enregistrement dns la table
                    if (a==-1)
                    {
                        Toast.makeText(LoginOTP.this ,"insert failed", Toast.LENGTH_LONG).show();
                    }
                    else

                    {
                        Toast.makeText(LoginOTP.this ,"insert success", Toast.LENGTH_LONG).show();
                    }

                }



            }
        });
    }

   /* protected void onStart() {
        super.onStart();

        /*Verifying if the user is login or not
        if (mCurrentUser == null)//the user is not login and we send him to the register page
        {
            Intent HomeIntent= new Intent(LoginOTP.this, MainActivity.class);
            HomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//
            HomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(HomeIntent);
            finish();

        }*/
    }


