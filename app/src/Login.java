package com.example.blood_line;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button login;
    EditText num, mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        num =findViewById(R.id.number);
        mdp = findViewById(R.id.MDP);




        login= findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((num.getText().toString().length() == 0) && (mdp.getText().toString().length() == 0))
                {
                    num.setError("Saisissez votre numero");
                    mdp.setError("Saisissez votre mot de passe");
                }
                else
                {
                    //Toast.makeText(Login.this,"LOGIN SUCCESS", Toast.LENGTH_LONG).show();
                    Intent Log= new Intent(Login.this,Home_BL.class);
                    startActivity(Log);
                }

            }
        });
    }
}
