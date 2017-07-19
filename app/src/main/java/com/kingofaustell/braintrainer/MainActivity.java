package com.kingofaustell.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
//    Button to begin the quiz
    Button StartButton;
//    Text view for addition problems
    TextView addingTxt;
//    Text view for the score keeper
    TextView points;
//    Text view for the results
    TextView results;
//    Text view for timer
    TextView timer;
//    list of answers
    ArrayList<Integer> answers = new ArrayList<Integer>();
//    variable to have a location to store the correct answer
    int locationOfCorrect;
//    Variables to use buttons
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgain;
    double score;
    //        Answers that are correct
    int c = 0;
    //        number of questions
    int d = 0;

    RelativeLayout game;
    public void generateQuestions(){

        //Generates random number
        Random rand = new Random();
//
        locationOfCorrect = rand.nextInt(4);
//        clears arrayList so the only answers that are generated are not saved for future questions
        answers.clear();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        addingTxt.setText(Integer.toString(a) + " + " + Integer.toString(b));
        int incorrect;

        for (int i =0 ; i<4; i++){
            if (i== locationOfCorrect){
                answers.add(a+b);
            }else{
                incorrect = rand.nextInt(41);
                while (incorrect == a+b){
                    incorrect= rand.nextInt(41);
                }
                answers.add(incorrect);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));


    }
//    Onclick method for choosing answers
    public void chooseAnswer(View view){
//        comparing to see if the botton that was clicked equals the location of the correct answer
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrect))){
//            updates results to correct the add to score
            results.setText("Correct!!!");
            c++;
            }else{
//            updates results to incorrect
            results.setText("Incorrect!!!");
        }
//          updates number of questions asked
            d++;

//        setting text view to update score
        points.setText(Integer.toString(c) + "/" + Integer.toString(d));
        score = (c / d)*100;
    generateQuestions();
    }

    public void Start(View view) {
        StartButton.setVisibility(View.INVISIBLE);
        game.setVisibility(View.VISIBLE);
        generateQuestions();
        playAgain(findViewById(R.id.playAgain));


    }
    public void playAgain(View view){

        timer.setText("30s");
        results.setText(" ");
        points.setText("0/0");
        playAgain.setVisibility(View.INVISIBLE);

        generateQuestions();

        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);

        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {

                timer.setText("0s");

                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);

                if (score >= 80){
                    results.setText("Your score: " + Double.toString(score) + "%, Great Job");

                }else{
                    results.setText("Your score: " + Double.toString(score) + "%, Needs Work");
                }
                playAgain.setVisibility(View.VISIBLE);
                c=0;
                d=0;}
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addingTxt = (TextView) findViewById(R.id.adding);
        results = (TextView) findViewById(R.id.result);
        points = (TextView) findViewById(R.id.points);
        timer= (TextView) findViewById(R.id.timer);
        button1 = (Button) findViewById(R.id.b1);
        button2 = (Button) findViewById(R.id.b2);
        button3 = (Button) findViewById(R.id.b3);
        button4 = (Button) findViewById(R.id.b4);
        StartButton = (Button) findViewById(R.id.StartButton);
        playAgain = (Button) findViewById(R.id.playAgain);
        game= (RelativeLayout) findViewById(R.id.Game);
        generateQuestions();

    }

}
