package app.jugadfunda.apiresponse;

public class QuizListResponse {
    private long qid;
    private String qtitle;
    private String qdescription;

    public QuizListResponse(long qid, String qtitle, String qdescription) {
        this.qid = qid;
        this.qtitle = qtitle;
        this.qdescription = qdescription;
    }

    public long getQid() {
        return qid;
    }

    public void setQid(long qid) {
        this.qid = qid;
    }

    public String getQtitle() {
        return qtitle;
    }

    public void setQtitle(String qtitle) {
        this.qtitle = qtitle;
    }

    public String getQdescription() {
        return qdescription;
    }

    public void setQdescription(String qdescription) {
        this.qdescription = qdescription;
    }

    @Override
    public String toString() {
        return "QuizListResponse{" +
                "qid=" + qid +
                ", qtitle='" + qtitle + '\'' +
                ", qdescription='" + qdescription + '\'' +
                '}';
    }
}
