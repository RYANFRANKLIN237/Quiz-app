package com.example.indianquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    public static ArrayList<Modelclaas> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_main);

        list=new ArrayList<>();
        list.add(new Modelclaas("Aproximately what fraction of the world's population lives in india","1/6","1/10","1/50","1/3","1/6"));
        list.add(new Modelclaas("Aproximately what fraction of the world's population lives in Africa","1/1","1/2","1/5","1/3","1/3"));
        list.add(new Modelclaas("Who is the most powerful woman in Cameroon","Minette libom","Chantal biya","Celestine ketcha","Judith yay sunday","Chantal biya"));
        list.add(new Modelclaas("Who is the 44th president of the USA","George bush","Xi jinping","Barack obama","Donald trump","Donald trump"));





        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        },1500);
    }
}