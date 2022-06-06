package model.livings;

public enum LeftRightDirection {
    LEFT,RIGHT;

    public static LeftRightDirection fromInt(int n) {
        return LeftRightDirection.values()[n];
    }
}
