package app.jugadfunda.quiz.comparequiz;

import java.util.ArrayList;

import app.jugadfunda.apiresponse.QuestionListResponse;

public interface CompareQuizInterfaceView {

    void passDataToRecyclerView(ArrayList<QuestionListResponse> questionsList);
}
