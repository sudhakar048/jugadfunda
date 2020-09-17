package app.jugaadfunda.learningcompanion.localstorage;

public class QuesionOptionPojo {
    private long tabid;
    private long quizid;
    private long questionid;
    private String option;

    public QuesionOptionPojo(long tabid, long quizid, long questionid, String option) {
        this.tabid = tabid;
        this.quizid = quizid;
        this.questionid = questionid;
        this.option = option;
    }

    public long getTabid() {
        return tabid;
    }

    public void setTabid(long tabid) {
        this.tabid = tabid;
    }

    public long getQuizid() {
        return quizid;
    }

    public void setQuizid(long quizid) {
        this.quizid = quizid;
    }

    public long getQuestionid() {
        return questionid;
    }

    public void setQuestionid(long questionid) {
        this.questionid = questionid;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
