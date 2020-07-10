package com.example.blood_line;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Write_Alert extends AppCompatActivity {



    EditText text_alert;
    Button publier_alert;
    BL_DATABASE alert_base;






    public void getName(){
        //String rv = "not found";
        SQLiteDatabase db = alert_base.getReadableDatabase();
        String[]request = {User.nom };
        Cursor cursor = (Cursor) db.query(User.Table_Name,request,null, null,null, null,null);
        while (cursor.moveToNext()){
            String nom = cursor.getString(0);
            Toast.makeText(Write_Alert.this,"Récupérée avec succes",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write__alert);


        alert_base = new BL_DATABASE(this);

        text_alert = findViewById(R.id.textAlert);
        publier_alert=findViewById(R.id.btn_Alert);



        publier_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getName();
                if (text_alert.getText().toString().length() == 0){
                    text_alert.setError("Veuillez mettre votre publication");
                }
                else {
                text_alert.getText().toString();
                    Toast.makeText(Write_Alert.this, "Publiée avec succes", Toast.LENGTH_SHORT).show();

                    String txt = text_alert.getText().toString();
                    Date c = Calendar.getInstance().getTime();
                    System.out.println("Current time => " + c);

                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate = df.format(c);

                    //text_alert.setText();


                    //String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    final String nom = getIntent().getStringExtra("nom");
                    Intent int_Alert = new Intent(Write_Alert.this,Home_BL.class);
                    startActivity(int_Alert);




                }


                //Creation d'un dictionnaire

                Map<String,String> map = new HashMap<String, String>();
                map.put("nom","amina");




                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");





                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.

                        String value = dataSnapshot.getValue(String.class);
                        Log.d("280399", "Value is: " + value);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                        // Failed to read value
                        Log.w("2803","Failed to read value.", error.toException());


                    }
                });




            }
        });

    }
}
