package app.jugaadfunda.learningcompanion.quiz.questions;

import java.util.ArrayList;
import app.jugaadfunda.learningcompanion.apiresponse.QuestionListResponse;

public interface StartQuizInterfaceView {

    void passDataToRecyclerView(ArrayList<QuestionListResponse> questionsList);

    void selectOptions(int pos, long optionid);

    void serAdapter();
}
