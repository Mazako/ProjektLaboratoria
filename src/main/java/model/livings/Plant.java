package model.livings;

public class Plant implements Putable {

    private int x;
    private int y;
    private double healValue;
    private double hungerValue;

    public Plant(int x, int y, double healValue, double hungerValue) {
        this.x = x;
        this.y = y;
        this.healValue = healValue;
        this.hungerValue = hungerValue;
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
