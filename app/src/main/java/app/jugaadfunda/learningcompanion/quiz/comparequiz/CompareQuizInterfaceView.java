package app.jugaadfunda.learningcompanion.quiz.comparequiz;

import java.util.ArrayList;

import app.jugaadfunda.learningcompanion.apiresponse.QuestionListResponse;

public interface CompareQuizInterfaceView {

    void passDataToRecyclerView(ArrayList<QuestionListResponse> questionsList);
}
