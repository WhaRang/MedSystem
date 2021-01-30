package home.helpers;

public enum HttpStatusCodes {

    OK (200),
    BAD(400);

    public final int value;

    HttpStatusCodes(int i) {
        value = i;
    }
}
