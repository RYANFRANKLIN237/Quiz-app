package com.example.indianquizapp;

import static com.example.indianquizapp.SplashActivity.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue=20;
    ProgressBar progressBar;
    List<Modelclaas> allQuestionsList;
    Modelclaas modelclaas;
    int index=0;
    TextView cardquestion,optiona,optionb,optionc,optiond;
    CardView cardOA,cardOB,cardOC,cardOD;
    int correctCount=0;
    int wrongCount=0;
    LinearLayout nextBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

         Hooks();
        allQuestionsList = list;
        Collections.shuffle(allQuestionsList);
        modelclaas=list.get(index);
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));
        nextBtn.setClickable(false);

        countDownTimer= new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue=timerValue-1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(DashboardActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);
                dialog.findViewById(R.id.btn_tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DashboardActivity.this, SplashActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        }.start();
        setAllData();
    }

    private void setAllData() {
        cardquestion.setText(modelclaas.getQuestion());
        optiona.setText(modelclaas.getoA());
        optionb.setText(modelclaas.getoB());
        optionc.setText(modelclaas.getoC());
        optiond.setText(modelclaas.getoD());
        timerValue=20;
        countDownTimer.cancel();
        countDownTimer.start();
    }

    private void Hooks() {
        progressBar= findViewById(R.id.quiz_timer);
        cardquestion= findViewById(R.id.card_question);
        optiona= findViewById(R.id.card_optiona);
        optionb= findViewById(R.id.card_optionb);
        optionc= findViewById(R.id.card_optionc);
        optiond= findViewById(R.id.card_optiond);
        cardOA= findViewById(R.id.cardA);
        cardOB= findViewById(R.id.cardB);
        cardOC= findViewById(R.id.cardC);
        cardOD= findViewById(R.id.cardD);
        nextBtn= findViewById(R.id.nextBtn);
    }

    public void Correct(CardView cardView){
        cardView.setBackgroundColor(getResources().getColor(R.color.green));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctCount++;
                index++;
                modelclaas=list.get(index);
                resetColor();
                setAllData();
            }
        });
    }

    public void Wrong(CardView cardOA){
        cardOA.setBackgroundColor(getResources().getColor(R.color.red));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongCount++;
                if(index<list.size()-1)
                {
                    index++;
                    modelclaas=list.get(index);
                    resetColor();
                    setAllData();
                }else{
                    GameWon();
                }
            }
        });
    }

    private void GameWon() {
        Intent intent= new Intent(DashboardActivity.this,wonActivity.class);
        intent.putExtra("correct",correctCount);
        intent.putExtra("wrong",wrongCount);
        startActivity(intent);
    }

    public void enableButton(){
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);
    }
    public void disableButton(){
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);
    }
    public void resetColor(){
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OptionAClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if(modelclaas.getoA().equals(modelclaas.getAns())){
            cardOA.setCardBackgroundColor(getResources().getColor(R.color.green));
            if(index< list.size()-1){
               Correct(cardOA);
            }
            else{
                GameWon();
            }
        }
        else{
            Wrong(cardOA);
        }
    }

    public void OptionBClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if(modelclaas.getoB().equals(modelclaas.getAns())){
            cardOB.setCardBackgroundColor(getResources().getColor(R.color.green));
            if(index< list.size()-1){
                Correct(cardOB);
            }
            else{
                GameWon();
            }
        }
        else{
            Wrong(cardOB);
        }
    }

    public void OptionClickC(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if(modelclaas.getoC().equals(modelclaas.getAns())){
            cardOC.setCardBackgroundColor(getResources().getColor(R.color.green));
            if(index< list.size()-1){
                Correct(cardOC);
            }
            else{
                GameWon();
            }
        }
        else{
            Wrong(cardOC);
        }
    }

    public void OptionDClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if(modelclaas.getoD().equals(modelclaas.getAns())){
            cardOD.setCardBackgroundColor(getResources().getColor(R.color.green));
            if(index< list.size()-1){
                Correct(cardOD);
            }
            else{
                GameWon();
            }
        }
        else{
            Wrong(cardOD);
        }
    }
}