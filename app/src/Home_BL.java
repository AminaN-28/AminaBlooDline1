package com.example.blood_line;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class Home_BL extends AppCompatActivity {
    private DrawerLayout mdrawer;
    private  ActionBarDrawerToggle mToggle;
    SpaceNavigationView navigationView;
    private NavigationView navDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__bl);

        mdrawer = findViewById(R.id.drawer);
        navDrawer = findViewById(R.id.drawernav);
        mToggle = new ActionBarDrawerToggle(this, mdrawer, R.string.open,R.string.close);
        mdrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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
                Log.d("Value is: " + value, "2803");

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w( "Failed to read value.", error.toException());
            }
        });



        //FloatingActionButon

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent fab_int =new Intent(Home_BL.this,Write_Alert.class);
                startActivity(fab_int);
            }
        });



        //DrawerLayout item clickable
        navDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.db:
                        Toast.makeText(Home_BL.this,"Dashboared",Toast.LENGTH_LONG).show();
                        Intent firstintent = new Intent(Home_BL.this, Dashboared.class);
                        startActivity(firstintent);
                        return true;



                    case R.id.profil:
                        Toast.makeText(Home_BL.this,"Profil",Toast.LENGTH_LONG).show();
                        Intent secondintent = new Intent(Home_BL.this,Profil.class);
                        startActivity(secondintent);
                        return true;


                   case R.id.event :
                       Toast.makeText(Home_BL.this,"Event",Toast.LENGTH_LONG).show();
                       Intent thirdintent = new Intent(Home_BL.this,Event.class);
                        startActivity(thirdintent);
                        return true;


                    case  R.id.signet :
                        Toast.makeText(Home_BL.this,"Signet",Toast.LENGTH_LONG).show();
                        Intent fourintent = new Intent(Home_BL.this,Signet.class);
                        startActivity(fourintent);
                        return true;


                 case   R.id.carte:
                    Intent fiveintent = new Intent(Home_BL.this,Localisation.class);
                    startActivity(fiveintent);
                    return true;

                case  R.id.parametres:
                    Toast.makeText(Home_BL.this,"Parametres et Confidentialites",Toast.LENGTH_LONG).show();
                    Intent sixintent = new Intent(Home_BL.this, Parameter.class);
                    startActivity(sixintent);
                    return true;


                 case R.id.deconnexion:
                     Toast.makeText(Home_BL.this,"Deconnexion",Toast.LENGTH_LONG).show();
                     Intent sevenintent = new Intent(Home_BL.this,MainActivity.class);
                     startActivity(sevenintent);
                     return true;



                }
                return true;
            }
        });






//        base = new BL_DATABASE(this);
        navigationView = findViewById(R.id.space);

        navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.addSpaceItem(new SpaceItem("SEARCH", R.drawable.ic_search_black_24dp));
        navigationView.addSpaceItem(new SpaceItem("NOTIFICATION", R.drawable.ic_notifications_black_24dp));

      navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(Home_BL.this,"HOME", Toast.LENGTH_SHORT).show();
                navigationView.setCentreButtonSelectable(true);
            }
            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(Home_BL.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
                Intent int_search= new Intent(Home_BL.this,SearchAct.class);
                startActivity(int_search);
            }
            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(Home_BL.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
                Intent int_notif= new Intent(Home_BL.this,NotificationAct.class);
                startActivity(int_notif);
            }
        });



      //RECUPERER LE TEXTE PUBLIE ,LA DATE DE PUBLICATION ET LE NUMERO DU USER

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


}
/*class CustomAdapt extends BaseAdapter{

        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            View view = getLayoutInflater().inflate(R.layout.custuomizelistview, null);

            ImageView imageView = view.findViewById(R.id.imgView);
            TextView txtView = view.findViewById(R.id.text1);


            imageView.setImageResource(image[position]);

            txtView.setText(Name[position]);

            return view;
        }
*/