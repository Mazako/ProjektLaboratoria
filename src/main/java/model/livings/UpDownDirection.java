package model.livings;

public enum  UpDownDirection {
    UP,DOWN;

    public static UpDownDirection fromInt(int n) {
        return UpDownDirection.values()[n];
    }
}
