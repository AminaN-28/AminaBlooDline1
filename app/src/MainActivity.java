package com.example.blood_line;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import static com.example.blood_line.R.layout.register;

public class MainActivity extends AppCompatActivity {
    Button Regist, Logbtn, SkipBtn;
    /*PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;*/
    //private FirebaseAuth mAuth; //Create an instance of FirebaseAuth
    //private FirebaseUser mCurrentUser;//for member variable


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SkipBtn = findViewById(R.id.SkipBtn);
        Logbtn = findViewById(R.id.Loginbtn);
        Regist = findViewById(R.id.Register_btn);




       Regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //Toast.makeText(MainActivity.this,"REGISTER SUCCESS",Toast.LENGTH_LONG).show();
                    Intent REGISTER = new Intent(MainActivity.this, Register.class);
                    startActivity(REGISTER);

            }
        });

       Logbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

              //Toast.makeText(MainActivity.this,"LOGIN SUCCESS", Toast.LENGTH_LONG).show();
               Intent LOGIN = new Intent(MainActivity.this,Login.class);
               startActivity(LOGIN);
               //finish();
           }
       });


       SkipBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Toast.makeText(MainActivity.this,"SKIP SUCCESS",Toast.LENGTH_LONG).show();
              Intent SKIP = new Intent(MainActivity.this, Home_BL.class);
              startActivity(SKIP);
           }
       });










    }



  /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (R.id.Register_btn == item.getItemId())
        {
            /*Intent REGISTER = new Intent(MainActivity.this,Register.class);
            startActivity(REGISTER);
            finish();*/
          /*  Toast.makeText(this,"REGISTER SUCCESS",Toast.LENGTH_LONG).show();
        }
        else if (R.id.Loginbtn == item.getItemId())
        {
            Toast.makeText(this,"LOGIN SUCCESS",Toast.LENGTH_LONG).show();
            /*Intent LOGIN = new Intent(MainActivity.this, LoginOTP.class);
            startActivity(LOGIN);
            finish();*/
       /* }
        else
        {
            Toast.makeText(this,"SKIPApp",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);

    }*/
}




