package model.livings;

/** Enum odpowiedzialny za ruch gora dol
 *
 */
public enum  UpDownDirection {
    UP,DOWN;

    /** Kierunek poruszania sie Y
     * @param n
     * @return
     */
    public static UpDownDirection fromInt(int n) {
        return UpDownDirection.values()[n];
    }
}
