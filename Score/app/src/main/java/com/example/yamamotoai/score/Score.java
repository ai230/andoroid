package com.example.yamamotoai.score;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by yamamotoai on 2017-07-10.
 */

public class Score extends AppCompatActivity{

    String msg = "Message:";
    //global valiable
    int scoreA =0;
    int scoreB=0;

    Button btnA1;
    Button btnA2;
    Button btnA3;
    Button btnB1;
    Button btnB2;
    Button btnB3;
    Button btnReset;

    TextView txtScoreA;
    TextView txtScoreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);
        txtScoreA = (TextView) findViewById(R.id.txtAScore);
        txtScoreB = (TextView) findViewById(R.id.txtBScore);
        btnA1 = (Button) findViewById(R.id.btnA1);
        btnA2 = (Button) findViewById(R.id.btnA2);
        btnA3 = (Button) findViewById(R.id.btnA3);
        btnB1 = (Button) findViewById(R.id.btnB1);
        btnB2 = (Button) findViewById(R.id.btnB2);
        btnB3 = (Button) findViewById(R.id.btnB3);
        btnReset = (Button) findViewById(R.id.btnReset);


        btnA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreA = scoreA + 3;
                disprayForScoreA(scoreA);
            }
        });

        btnA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreA = scoreA + 2;
                disprayForScoreA(scoreA);
            }
        });

        btnA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreA = scoreA + 1;
                disprayForScoreA(scoreA);
            }
        });

        btnB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreB = scoreB + 3;
                disprayForScoreB(scoreB);
            }
        });

        btnB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreB = scoreB + 2;
                disprayForScoreB(scoreB);
            }
        });

        btnB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreB = scoreB + 1;
                disprayForScoreB(scoreB);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scoreA = 0;
                scoreB = 0;
                disprayForScoreA(scoreA);
                disprayForScoreB(scoreB);

            }
        });

    }
    public void disprayForScoreA(Integer scoreA) {
        txtScoreA.setText(String.valueOf(scoreA));
    }

    public void disprayForScoreB(Integer scoreB) {
        txtScoreB.setText(String.valueOf(scoreB));
    }

}
