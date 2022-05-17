package model.livings;

public class Zebra extends Herbivore{

    public Zebra(double health, double hunger, int speed) {
        super(health, hunger, speed);
    }

    @Override
    public void move() {

    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }
}
