package app.jugadfunda.quiz.pojo;

public class SelectedOptions {
    private int pos;
    private long optionid;

    public SelectedOptions(int pos, long optionid) {
        this.pos = pos;
        this.optionid = optionid;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public long getOptionid() {
        return optionid;
    }

    public void setOptionid(long optionid) {
        this.optionid = optionid;
    }

    @Override
    public String toString() {
        return "SelectedOptions{" +
                "pos=" + pos +
                ", optionid=" + optionid +
                '}';
    }
}
