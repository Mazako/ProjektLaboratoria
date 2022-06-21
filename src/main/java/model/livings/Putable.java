package model.livings;

/** Interfejs odpowiadajacy za polozenie obiektow na planszy
 *
 */
public interface Putable {


    /** getter na wspolrzedna X
     * @return
     */
    int getX();

    /** getter na wspolrzedna Y
     * @return
     */
    int getY();

    /** setter na wspolrzedna X
     * @param x wspolrzedna 'x'
     */
    void setX(int x);

    /** setter na wspolrzedna Y
     * @param y
     */
    void setY(int y);

    /** odleglosc miedzy obiektami
     * @param p obiekt
     * @return
     */
    default double distanceBetween(Putable p) {
        return Math.sqrt(Math.pow(getX() - p.getX(),2) + Math.pow((getY() - p.getY()),2));
    }



}
