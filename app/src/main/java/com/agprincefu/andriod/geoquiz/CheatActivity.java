package com.agprincefu.andriod.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.agprincefu.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.agprincefu.android.geoquiz.answer_shown";
    private static final String KEY_IS_CHEAT = "Cheated";


    private TextView mAnswerTextVeiw;
    private Button mShowAnswerButton;

    private Boolean mAnswerIsTrue;
    private Boolean mIsCheated;

    public static Intent newIntent(Context contextPackage, boolean answerIsTrue) {
        Intent intent = new Intent(contextPackage, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean getAnswerShowm(Intent intent) {
        return intent.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null) {
            mIsCheated = savedInstanceState.getBoolean(KEY_IS_CHEAT);
        }

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);


        mAnswerTextVeiw = findViewById(R.id.anwser_text_view);
        mShowAnswerButton = findViewById(R.id.show_answer_button);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mAnswerIsTrue == true) {
                    mAnswerTextVeiw.setText(R.string.true_button);
                } else {
                    mAnswerTextVeiw.setText(R.string.false_button);
                }
                mIsCheated = true;
                setAnswerShownResult(mIsCheated);

            }
        });


    }

    private void setAnswerShownResult(Boolean isAnswerShown) {

        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_IS_CHEAT, mIsCheated);
    }
}
