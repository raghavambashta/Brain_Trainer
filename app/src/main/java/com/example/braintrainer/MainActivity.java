package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    CountDownTimer countDownTimer1;
    CountDownTimer countDownTimer2;
    Button playAgainButton;
    TextView viewScore;
    ConstraintLayout gameLayout;

    public void start(View view)
    {
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
        gameLayout.setVisibility(0);
    }

    public void playAgain(View view)
    {
        resultTextView.setVisibility(4);
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        newQuestion();
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        button0.setVisibility(0);
        button1.setVisibility(0);
        button2.setVisibility(0);
        button3.setVisibility(0);
        timerTextView.setVisibility(0);
        scoreTextView.setVisibility(0);
        sumTextView.setVisibility(0);
        playAgainButton.setVisibility(4);
        viewScore.setVisibility(4);

        countDownTimer1 = new CountDownTimer(30100,1000)
        {
            @Override
            public void onTick(long l)
            {
                timerTextView.setText(Integer.toString((int)l/1000)+"s");
            }

            @Override
            public void onFinish()
            {
                countDownTimer2 = new CountDownTimer(1600,1000) {
                    @Override
                    public void onTick(long l) {
                        resultTextView.setVisibility(0);
                        resultTextView.setText("Time Out 😐");
                    }

                    @Override
                    public void onFinish() {
                        playAgainButton.setVisibility(0);
                        viewScore.setVisibility(0);
                        viewScore.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                        button0.setVisibility(4);
                        button1.setVisibility(4);
                        button2.setVisibility(4);
                        button3.setVisibility(4);
                        timerTextView.setVisibility(4);
                        scoreTextView.setVisibility(4);
                        sumTextView.setVisibility(4);
                    }
                };
                countDownTimer2.start();
            }
        };
        countDownTimer1.start();
    }

    public void chooseAnswer (View view)
    {
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag()))
        {
            resultTextView.setText("Correct 🙂");
            score++;
        }
        else
        {
            resultTextView.setText("Wrong 🙁");
        }
        CountDownTimer countDownTimer = new CountDownTimer(1600,1000) {
            @Override
            public void onTick(long l) {
                resultTextView.setVisibility(0);
            }

            @Override
            public void onFinish() {
                resultTextView.setVisibility(4);
            }
        };
        countDownTimer.start();
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }



    public void newQuestion()
    {
        Random rand = new Random();
        int a = rand.nextInt(31);
        int b = rand.nextInt(31);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        int wrongAnswer;
        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                wrongAnswer = rand.nextInt(61);
                while (wrongAnswer == a + b) {
                    wrongAnswer = rand.nextInt(61);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        goButton = (Button) findViewById(R.id.goButton);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        viewScore = (TextView)findViewById(R.id.viewScore);
        gameLayout = (ConstraintLayout)findViewById(R.id.gameLayout);

        resultTextView.setVisibility(4);
        goButton.setVisibility(0);
        gameLayout.setVisibility(4);

    }
}
