package app.jugadfunda.apiresponse;

public class QuizCodeResponse {
    private long qid;
    private String qtitle;
    private int duration;
    private boolean flag;
    private String check;
    private String date;
    private String slot;
    private int totalquestions;
    private String msg;

    public QuizCodeResponse(long qid, String qtitle, int duration, boolean flag, String check, String date, String slot, int totalquestions, String msg) {
        this.qid = qid;
        this.qtitle = qtitle;
        this.duration = duration;
        this.flag = flag;
        this.check = check;
        this.date = date;
        this.slot = slot;
        this.totalquestions = totalquestions;
        this.msg = msg;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String isCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getTotalquestions() {
        return totalquestions;
    }

    public void setTotalquestions(int totalquestions) {
        this.totalquestions = totalquestions;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCheck() {
        return check;
    }

    @Override
    public String toString() {
        return "QuizCodeResponse{" +
                "qid=" + qid +
                ", qtitle='" + qtitle + '\'' +
                ", duration=" + duration +
                ", flag=" + flag +
                ", check='" + check + '\'' +
                ", date='" + date + '\'' +
                ", slot='" + slot + '\'' +
                ", totalquestions=" + totalquestions +
                ", msg='" + msg + '\'' +
                '}';
    }
}
