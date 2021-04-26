package app.jugadfunda.quiz.questions;

import org.json.JSONArray;

import java.util.ArrayList;
import app.jugadfunda.apiresponse.QuestionListResponse;
import app.jugadfunda.quiz.pojo.SelectedOptions;

public interface StartQuizInterfaceView {

    void passList(ArrayList<QuestionListResponse> questionsList);

    void selectOptions(int pos, String option, long questionId);

    void showEmptyData();

    JSONArray convertListtoJSONArray(ArrayList<SelectedOptions> mSelectedList);

    void clearData();
}
