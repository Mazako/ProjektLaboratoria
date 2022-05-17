package model.livings;

public class Snake extends  Carnivore{
    public Snake(double health, double hunger, int speed, double attackValue) {
        super(health, hunger, speed, attackValue);
    }

    @Override
    public void attack(Animal animal) {

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
