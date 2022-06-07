package model.livings;

public class Plant implements Putable {

    private int x;
    private int y;
    private int healValue;
    private int hungerValue;

    private boolean eaten = false;

    public Plant(int x, int y, int healValue, int hungerValue) {
        this.x = x;
        this.y = y;
        this.healValue = healValue;
        this.hungerValue = hungerValue;
    }

    public int getHealValue() {
        return healValue;
    }

    public void setHealValue(int healValue) {
        this.healValue = healValue;
    }

    public int getHungerValue() {
        return hungerValue;
    }

    public void setHungerValue(int hungerValue) {
        this.hungerValue = hungerValue;
    }

    public boolean isEaten() {
        return eaten;
    }

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
}
