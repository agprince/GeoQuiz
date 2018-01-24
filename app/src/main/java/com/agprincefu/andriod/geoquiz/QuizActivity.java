package com.agprincefu.andriod.geoquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "agtest";
    private static final String KEY_INDEX="index";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private int mResponseNumber=0;
    private int mScore=0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Log.d(TAG, "onCreate(Bundle) called");
        if(savedInstanceState!=null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mQuestionTextView = findViewById(R.id.question_text_view);

        updateQuestion();


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast toast = Toast.makeText(QuizActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT);
                //toast.setGravity(Gravity.TOP,0,40);
                // toast.show();

                checkAnswer(true);
                if(!mQuestionBank[mCurrentIndex].isResponse()){
                    mQuestionBank[mCurrentIndex].setResponse(true);
                    mFalseButton.setClickable(false);
                    mTrueButton.setClickable(false);
                    mResponseNumber++;
                    
                }
                if(mResponseNumber == mQuestionBank.length){
                    Toast.makeText(QuizActivity.this,"You anwser all ,this scroe is : "+mScore*100/mQuestionBank.length,Toast.LENGTH_SHORT).show();
                }


            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
                checkAnswer(false);

                if(!mQuestionBank[mCurrentIndex].isResponse()){
                    mQuestionBank[mCurrentIndex].setResponse(true);
                    mFalseButton.setClickable(false);
                    mTrueButton.setClickable(false);
                    mResponseNumber++;
                }
                if(mResponseNumber == mQuestionBank.length){
                    Toast.makeText(QuizActivity.this,"You anwser all ,this scroe is : "+mScore*100/mQuestionBank.length*100,Toast.LENGTH_SHORT).show();
                }

            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
                if(!mQuestionBank[mCurrentIndex].isResponse()){
                    mFalseButton.setClickable(true);
                    mTrueButton.setClickable(true);
                }


            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState");
        outState.putInt(KEY_INDEX,mCurrentIndex);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }


    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageAnswer = 0;

        if (userPressedTrue == answerTrue) {
            messageAnswer = R.string.correct_toast;
            mScore++;
        } else {
            messageAnswer = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageAnswer, Toast.LENGTH_SHORT).show();
    }

}
