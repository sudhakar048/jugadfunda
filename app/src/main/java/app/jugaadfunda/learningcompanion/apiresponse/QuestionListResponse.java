package app.jugaadfunda.learningcompanion.apiresponse;

import java.util.ArrayList;

public class QuestionListResponse {
    private long quesid;
    private String title;
    ArrayList<OptionResponse> options;

    public QuestionListResponse(long quesid, String title, ArrayList<OptionResponse> options) {
        this.quesid = quesid;
        this.title = title;
        this.options = options;
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

    @Override
    public String toString() {
        return "QuestionListResponse{" +
                "quesid=" + quesid +
                ", title='" + title + '\'' +
                ", options=" + options +
                '}';
    }
}
