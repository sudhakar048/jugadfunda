package app.jugaadfunda.learningcompanion.quiz.questions;

public interface StartQuizInterfaceImpl {

    void wsQuestionlist(long mQuizId);

    void wsAddAnswers(long mQuizId, String mQuestions, String mOptions, long mUserId);
}