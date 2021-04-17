package app.jugadfunda.apiresponse;

import java.util.ArrayList;

public class QuestionListResponse {
    private long quesid;
    private String title;
    private ArrayList<OptionResponse> options;
    private String option;

    public QuestionListResponse(long quesid, String title, ArrayList<OptionResponse> options, String option) {
        this.quesid = quesid;
        this.title = title;
        this.options = options;
        this.option = option;
    }

    public long getQuesid() {
        return quesid;
    }

    public void setQuesid(long quesid) {
        this.quesid = quesid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<OptionResponse> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<OptionResponse> options) {
        this.options = options;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "QuestionListResponse{" +
                "quesid=" + quesid +
                ", title='" + title + '\'' +
                ", options=" + options +
                ", option=" + option +
                '}';
    }
}
