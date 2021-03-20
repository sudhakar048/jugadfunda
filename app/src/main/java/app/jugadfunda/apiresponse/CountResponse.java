package app.jugadfunda.apiresponse;

public class CountResponse {
    private int happycount;
    private int satisfiedcount;
    private int unhappycount;
    private int sadcount;

    public CountResponse(int happycount, int satisfiedcount, int unhappycount, int sadcount) {
        this.happycount = happycount;
        this.satisfiedcount = satisfiedcount;
        this.unhappycount = unhappycount;
        this.sadcount = sadcount;
    }

    public int getHappycount() {
        return happycount;
    }

    public void setHappycount(int happycount) {
        this.happycount = happycount;
    }

    public int getSatisfiedcount() {
        return satisfiedcount;
    }

    public void setSatisfiedcount(int satisfiedcount) {
        this.satisfiedcount = satisfiedcount;
    }

    public int getUnhappycount() {
        return unhappycount;
    }

    public void setUnhappycount(int unhappycount) {
        this.unhappycount = unhappycount;
    }

    public int getSadcount() {
        return sadcount;
    }

    public void setSadcount(int sadcount) {
        this.sadcount = sadcount;
    }

    @Override
    public String toString() {
        return "CountResponse{" +
                "happycount=" + happycount +
                ", satisfiedcount=" + satisfiedcount +
                ", unhappycount=" + unhappycount +
                ", sadcount=" + sadcount +
                '}';
    }
}
