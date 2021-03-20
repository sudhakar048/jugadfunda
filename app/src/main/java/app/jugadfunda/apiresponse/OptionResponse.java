package app.jugadfunda.apiresponse;

public class OptionResponse {
    private long oid;
    private String opts;
    private int optcount;

    public OptionResponse(long oid, String opts, int optioncount) {
        this.oid = oid;
        this.opts = opts;
        this.optcount = optioncount;
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public String getOpts() {
        return opts;
    }

    public void setOpts(String opts) {
        this.opts = opts;
    }

    public int getOptioncount() {
        return optcount;
    }

    public void setOptioncount(int optioncount) {
        this.optcount = optioncount;
    }

    @Override
    public String toString() {
        return "OptionResponse{" +
                "oid=" + oid +
                ", opts='" + opts + '\'' +
                ", optioncount=" + optcount +
                '}';
    }
}
