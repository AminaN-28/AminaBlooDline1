package com.example.blood_line;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.valueOf;

public class Register extends AppCompatActivity {


    private FirebaseAuth mAuth; //Create an instance of FirebaseAuth
    private FirebaseUser mCurrentUser;//for member variable
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;


    EditText FName,PhoneN,BlooDG,Password,Street,age;
    Button registerbtn;
    ArrayList listePersonne;


   BL_DATABASE base;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mAuth= FirebaseAuth.getInstance();//initialisation of instance
        mCurrentUser= mAuth.getCurrentUser();


        Street = findViewById(R.id.Street);
        PhoneN = findViewById(R.id.numTel);
        registerbtn=findViewById(R.id.Save_btn);
        FName=findViewById(R.id.nom);
        Password=findViewById(R.id.password);
        BlooDG=findViewById(R.id.GroupS);
        age=findViewById(R.id.Age);



       base = new BL_DATABASE(this);




        registerbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {






                if ((FName.getText().toString().length() == 0) && (BlooDG.getText().toString().length() == 0)
                        && (Password.getText().toString().length() == 0) && (PhoneN.getText().toString().length()==0)
                       && (Street.getText().toString().length() == 0) && (age.getText().toString().length()==0))
                {

                    FName.setError("VEUILLEZ RENSEIGNER VOTRE NOM");
                    BlooDG.setError("VEUILLEZ RENSEIGNER VOTRE GROUPE SANGUIN");
                    PhoneN.setError("VEUILLEZ SAISIR VOTRE NUMERO");
                    Password.setError("VEUILLEZ RENSEIGNER UN  MOT DE PASSE");
                    Street.setError("VEUILLEZ RENSEIGNER VOTRE REGION");
                    age.setError("VEUILLEZ SAISIR VOTRE AGE");

                }
                // if (BlooDG.getText().toString().length() == 0) { }
                //if (Password.getText().toString().length() == 0) {}
                //if (Street.getText().toString().length() == 0){}

               else {



                String phone = PhoneN.getText().toString();
                // Toast.makeText(Register.this,phone,Toast.LENGTH_LONG).show();
                //convertir le numero recuperé en string

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        //"+221774693135"
                        "+221" + phone,
                        60,
                        TimeUnit.SECONDS,
                        Register.this,
                        mCallbacks
                );


                    //BASES DE DONNEES LOCALE

                    SQLiteDatabase bl = base.getWritableDatabase();

                    String nom =FName.getText().toString();
                    String region= Street.getText().toString();
                    String year_old = age.getText().toString();
                    String number = PhoneN.getText().toString();
                    String  mdp = Password.getText().toString();
                    String bloodG = BlooDG.getText().toString();


                    ContentValues values = new ContentValues();


                    values.put("nom",nom);

                    values.put("region",region);

                    values.put("age",Integer.valueOf(year_old));

                    values.put("groupeS",bloodG);

                    values.put("number",Integer.valueOf(number));

                    values.put("password",mdp);


                    long a = bl.insert("utilisateur",null,values);//si le a retourne -1 l'insertion cest pas bien passé
                    //retourne le nombrre d'enregistrement dns la table
                    if (a==-1)
                    {
                        Toast.makeText(Register.this ,"insert failed", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(Register.this ,"insert success", Toast.LENGTH_LONG).show();
                    }

            }

           }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
            {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d("TAG" ,"onVerificationCompleted:" + phoneAuthCredential);

               // signInWithPhoneAuthCredential(credential);
            }


           /* private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "signInWithCredential:success");

                                    FirebaseUser user = task.getResult().getUser();
                                    // ...
                                } else {
                                    // Sign in failed, display a message and update the UI
                                    Log.w("TAG, "signInWithCredential:failure", task.getException());
                                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                        // The verification code entered was invalid
                                    }
                                }
                            }
                        });
            }*/

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w("TAGGER", "onVerificationFailed", e);
                Toast.makeText(Register.this, "Phone Number format invalid", Toast.LENGTH_LONG).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                }

                // Show a message and update the UI
                // ..
            }

           @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);


                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("onCodeSent:", "onCodeSent:" );

                // Save verification ID and resending token so we can use them later
               Intent goToverify = new Intent(Register.this, LoginOTP.class);
               goToverify.putExtra("nom",FName.getText().toString());
               goToverify.putExtra("phonenumber", PhoneN.getText().toString());
               goToverify.putExtra("groupeS", BlooDG.getText().toString());
               goToverify.putExtra("age", age.getText().toString());
               goToverify.putExtra("region", Street.getText().toString());
               goToverify.putExtra("password", Password.getText().toString());
               goToverify.putExtra("string",s);
               goToverify.putExtra("forcing",forceResendingToken);
               startActivity(goToverify);



           }

        };




    }



    protected void onStart() {
        super.onStart();


        //Verifying if the user is login or not
      /*  if (mCurrentUser == null)//the user is not login and we send him to the register page
        {
            Intent HomeIntent= new Intent(Register.this, MainActivity.class);
            HomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//
            HomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(HomeIntent);



        }*/
    }




    }
