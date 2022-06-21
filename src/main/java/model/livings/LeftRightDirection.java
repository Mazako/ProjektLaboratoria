package model.livings;

/** Enum odpowiedzialny za ruch lewo prawo
 *
 */
public enum LeftRightDirection {
    LEFT,RIGHT;

    /** Kierunek poruszania sie X
     * @param n
     * @return
     */
    public static LeftRightDirection fromInt(int n) {
        return LeftRightDirection.values()[n];
    }
}
