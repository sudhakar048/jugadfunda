package app.jugadfunda.quiz.quizlist;

import java.util.ArrayList;

import app.jugadfunda.apiresponse.QuizListResponse;

public interface QuizListView {

    void passDatatoRecyclerView(ArrayList<QuizListResponse> list);

    void loadProgressBar();

    void checkforNodata();
}
