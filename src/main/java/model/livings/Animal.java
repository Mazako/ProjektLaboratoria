package model.livings;

public abstract class Animal implements Movable, Putable {

    private double health;
    private double hunger;
    private int speed;

    public Animal(double health, double hunger, int speed) {
        this.health = health;
        this.hunger = hunger;
        this.speed = speed;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public double getHunger() {
        return hunger;
    }

    public void setHunger(double hunger) {
        this.hunger = hunger;
    }
}
