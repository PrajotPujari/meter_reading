package com.example.budget;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyVieHolder extends RecyclerView.ViewHolder {

    TextView user1  , fuel1 , amount1 , cng1 ,  meter1, result1 , date1  ;


    public MyVieHolder(@NonNull View itemView) {
        super(itemView);

        //user1=itemView.findViewById(R.id.fuel_t);
        fuel1=itemView.findViewById(R.id.fuel_t);
        date1=itemView.findViewById(R.id.date_t);
        cng1=itemView.findViewById(R.id.type_t);
        amount1=itemView.findViewById(R.id.amount_t);
        meter1=itemView.findViewById(R.id.meter_t);
        //result1=itemView.findViewById(R.id.result_t);

    }
}
