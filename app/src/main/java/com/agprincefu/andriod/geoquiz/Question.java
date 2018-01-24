package com.agprincefu.andriod.geoquiz;

/**
 * Created by agprincefu on 2018/1/23.
 */

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mResponse;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mResponse = false;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isResponse() {
        return mResponse;
    }

    public void setResponse(boolean response) {
        mResponse = response;
    }
}
