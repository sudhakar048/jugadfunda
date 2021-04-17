package app.jugadfunda.quiz.questions;

import org.json.JSONArray;

public interface StartQuizInterfaceImpl {

    void wsQuestionlist(long mQuizId);

    void wsAddAnswers(long mQuizId, JSONArray list, String mobilenumber, int miutetime, int secondtime);
}
