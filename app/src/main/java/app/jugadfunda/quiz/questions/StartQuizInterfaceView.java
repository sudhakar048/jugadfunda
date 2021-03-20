package app.jugadfunda.quiz.questions;

import java.util.ArrayList;
import app.jugadfunda.apiresponse.QuestionListResponse;

public interface StartQuizInterfaceView {

    void passDataToRecyclerView(ArrayList<QuestionListResponse> questionsList);

    void selectOptions(int pos, long optionid);

    void serAdapter();
}
