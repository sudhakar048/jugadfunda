package app.jugaadfunda.learningcompanion.apiresponse;

public class QuizListResponse {
    private long qid;
    private String qtitle;
    private String qdescription;
    private String noofattempts;

    public QuizListResponse(long qid, String qtitle, String qdescription, String noofattempts) {
        this.qid = qid;
        this.qtitle = qtitle;
        this.qdescription = qdescription;
        this.noofattempts = noofattempts;
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

    public String getNoofattempts() {
        return noofattempts;
    }

    public void setNoofattempts(String noofattempts) {
        this.noofattempts = noofattempts;
    }
}
