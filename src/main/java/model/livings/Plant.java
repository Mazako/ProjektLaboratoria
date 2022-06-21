package model.livings;

import java.util.Objects;

/** Klasa reprezentujaca za rosline
 *
 */
public class Plant implements Putable {

    /** wspolrzedna 'x'
     *
     */
    private int x;
    /** wspolrzedna 'y'
     *
     */
    private int y;
    /** wartosc leczenia
     *
     */
    private int healValue;
    /** wartosc glodu
     *
     */
    private int hungerValue;

    /** Czy zjedzone
     *
     */
    private boolean eaten = false;

    /** Konstruktor przyjmujacy parametry rosliny
     * @param x wspolrzedna 'x'
     * @param y wspolrzedna 'y'
     * @param healValue wartosc leczenia
     */
    public Plant(int x, int y, int healValue) {
        this.x = x;
        this.y = y;
        this.healValue = healValue;
    }

    /** getter na wartosc leczenia
     * @return
     */
    public int getHealValue() {
        return healValue;
    }


    /** Czy zjedzone
     * @return
     */
    public boolean isEaten() {
        return eaten;
    }

    /** Roslina umarla
     *
     */
    public void die() {
        eaten = true;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return x == plant.x && y == plant.y && healValue == plant.healValue && hungerValue == plant.hungerValue && eaten == plant.eaten;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, healValue, hungerValue, eaten);
    }
}
