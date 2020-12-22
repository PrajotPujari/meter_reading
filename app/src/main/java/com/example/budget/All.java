package com.example.budget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class All extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;


    RecyclerView recyclerView;
    ProgressBar mProgressCircle;


    FirebaseRecyclerOptions<Car> options;
    FirebaseRecyclerAdapter<Car, MyVieHolder> madapter;
    DatabaseReference dataRef;

   // StorageReference storageReference;
    //new


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all);

        recyclerView=findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);

        //account=findViewById(R.id.account);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final String favuser=user.getUid();

        dataRef= FirebaseDatabase.getInstance().getReference().child("All").child(favuser);//also called  CAR




        bottomNavigationView=findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.all);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.all:
                        return true;

                }
                return false;
            }
        });

        LoadDataAll();


    }

    private void LoadDataAll() {

        options = new FirebaseRecyclerOptions.Builder<Car>().setQuery(dataRef, Car.class).build();
       // final ArrayList<Integer> rideList = null;
        madapter = new  FirebaseRecyclerAdapter<Car, MyVieHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull  MyVieHolder holder, final int position, @NonNull final Car model) {
                //account1 , pearson1 , result1 , user1 ;
                // car user , amount ,pearson ,result , name;
                //user1  , fuel1 , amount1 , cng1 ,  meter1, result1 , date1
                //holder.user1.setText(model.getUser());
                holder.fuel1.setText(model.getFuel());
                holder.amount1.setText(model.getAmount());
                holder.cng1.setText(model.getCng());
                holder.meter1.setText(model.getMeter());
                //holder.result1.setText(model.getResult());
                holder.date1.setText(model.getDate());
                //holder.textView.setText(model.getCarName());

               // mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @NonNull
            @Override
            public MyVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view2, parent, false);
                return new MyVieHolder(v);
            }
        };
        madapter.startListening();
        recyclerView.setAdapter(madapter);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}