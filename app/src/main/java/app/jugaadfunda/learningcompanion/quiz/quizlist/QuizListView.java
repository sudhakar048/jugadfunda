package app.jugaadfunda.learningcompanion.quiz.quizlist;

import java.util.ArrayList;

import app.jugaadfunda.learningcompanion.apiresponse.QuizListResponse;

public interface QuizListView {

    void passDatatoRecyclerView(ArrayList<QuizListResponse> list);

    void loadProgressBar();

    void checkforNodata();
}
