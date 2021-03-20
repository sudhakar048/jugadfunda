package app.jugadfunda.apiresponse;

import java.util.Arrays;

public class FunCornerResponse {
    long funid;
    private CountResponse emojicount;
    private byte[] cartoon;
    private String description;
    private int pemoji;

    public FunCornerResponse(long funid, CountResponse emojicount, byte[] cartoon, String description, int pemoji) {
        this.funid = funid;
        this.emojicount = emojicount;
        this.cartoon = cartoon;
        this.description = description;
        this.pemoji = pemoji;
    }

    public FunCornerResponse(byte[] cartoon) {
        this.cartoon = cartoon;
    }

    public long getFunid() {
        return funid;
    }

    public void setFunid(long funid) {
        this.funid = funid;
    }

    public CountResponse getEmojicount() {
        return emojicount;
    }

    public void setEmojicount(CountResponse emojicount) {
        this.emojicount = emojicount;
    }

    public byte[] getCartoon() {
        return cartoon;
    }

    public void setCartoon(byte[] cartoon) {
        this.cartoon = cartoon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPemoji() {
        return pemoji;
    }

    public void setPemoji(int pemoji) {
        this.pemoji = pemoji;
    }

    @Override
    public String toString() {
        return "FunCornerResponse{" +
                "funid=" + funid +
                ", emojicount=" + emojicount +
                ", cartoon=" + Arrays.toString(cartoon) +
                ", description='" + description + '\'' +
                ", pemoji=" + pemoji +
                '}';
    }
}
