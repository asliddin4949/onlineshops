package onlineshops.Objects;

public enum Measurement {
    KG("kg"),
    PIECE("dona");
    String uzb;

    Measurement(String uzb) {
        this.uzb = uzb;
    }

    public String getUzb() {
        return uzb;
    }
}
