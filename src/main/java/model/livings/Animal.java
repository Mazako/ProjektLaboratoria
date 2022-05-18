package model.livings;

import model.board.Safari;

public abstract class Animal implements Movable, Putable {

    private int x;
    private int y;
    private double health;
    private double hunger;
    private int speed;


    public Animal(int x, int y, double health, double hunger, int speed) {
        this.x = x;
        this.y = y;
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

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void move(int x, int y) {
        if (Math.abs(x) > speed) {
            if (x >= 0)
                x = speed;
            else
                x = -speed;
        }

        if (Math.abs(y) > speed) {
            if (y >= 0)
                y = speed;
            else
                y = -speed;
        }

        if (x > 0)
            setX(Math.min(x + getX(), Safari.MAX_WIDTH));
        else
            setX(Math.max(x + getX(), 0));
        if (y > 0)
            setY(Math.min(y + getY(), Safari.MAX_WIDTH));
        else
            setY(Math.max(y + getY(), 0));

    }

    @Override
    public String toString() {
        return getX() + " " + getY();
    }
}
