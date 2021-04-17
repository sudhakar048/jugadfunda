package app.jugadfunda.quiz.pojo;

public class SelectedOptions {
    private int pos;
    private String option;
    private long questionId;

    public SelectedOptions(int pos, String option, long questionId) {
        this.pos = pos;
        this.option = option;
        this.questionId = questionId;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "SelectedOptions{" +
                "pos=" + pos +
                ", option='" + option + '\'' +
                ", questionId=" + questionId +
                '}';
    }
}
