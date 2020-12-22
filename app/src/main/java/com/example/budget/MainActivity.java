package com.example.budget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private EditText  date , fuel , amount , cng  , meter  , result;
    private Button button , date_picker;
    private BottomNavigationView bottomNavigationView;
    private TextView date_pick;


    DatabaseReference dataRef ;

    Integer a  , b , c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date=findViewById(R.id.date_of);
        //date_pick=findViewById(R.id.date_of);
        date_picker=findViewById(R.id.date_picker);
        fuel=findViewById(R.id.fuel);
        amount=findViewById(R.id.amount_of_fuel);
        cng=findViewById(R.id.cng_petrol);
        meter=findViewById(R.id.meter_reading);
        button=findViewById(R.id.button1);
        //result=findViewById(R.id.editTextNumber2);


        bottomNavigationView=findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.all:startActivity(new Intent(getApplicationContext(),All.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;

                }
                return false;
            }
        });


        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datepicker = new DatePickerFragement();
                datepicker.show(getSupportFragmentManager(), "Date picker");
                //date.setText("Hello ");

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //a=Integer.parseInt(amount.getText().toString());
                //b=Integer.parseInt(meter.getText().toString());
                //c=a/b;

                //result.setText(Float.toString(c));
                //Toast.makeText(MainActivity.this,"Updated",Toast.LENGTH_LONG).show();
                uploadImage();
                //     if (amount!=null && divide!= null){
                //
                //                    uploadImage();
                //                }else {
                //                    Toast.makeText(MainActivity.this,"Enter amount", Toast.LENGTH_LONG).show();
                //
                //                }

            }
        });

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final String favuser=user.getUid();

        dataRef= FirebaseDatabase.getInstance().getReference().child("All").child(favuser);//also called  CAR
        //        String amount1 = c.toString();
        //final String imagename =inputimagename.getText().toString();

    }

    private void uploadImage() {

        final String key3=dataRef.push().getKey();
        // final String url= storageRef3.child(key3+".jpg").getDownloadUrl().toString();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final String favuser=user.getUid();
        String favusername = user.getEmail();

        String fuel1 = fuel.getText().toString();//new
        String amount1 = amount.getText().toString();//new
        String cng1 = cng.getText().toString();//new
        String meter1= meter.getText().toString();
        //String result1= result.getText().toString();
        String date1= date.getText().toString();
        //String date_123= date_pick.getText().toString();


        HashMap hashMap=new HashMap();
        hashMap.put("user", favusername );
        hashMap.put("fuel", fuel1 );
        hashMap.put("amount",amount1 );
        hashMap.put("cng", cng1 );
        hashMap.put("meter", meter1);//b
        //hashMap.put("result", result1);
        hashMap.put("date", date1);

        //     Intent intent=new Intent(HomeActivity3.this,people_images.class);
        //   intent.putExtra("keyofposition",key3);

        dataRef.child(key3).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Toast.makeText(HomeActivity2.this, "Data succesfull", Toast.LENGTH_SHORT).show();
                 startActivity(new Intent(getApplicationContext(),All.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayofmonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH , month);
        c.set(Calendar.DAY_OF_MONTH , dayofmonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        date.setText(currentDateString);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        String text = parent.getItemAtPosition(i).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        cng.setText(text);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}










