package model.livings;

import model.board.Safari;

import java.util.Random;

public abstract class Animal implements Movable, Putable {

    private static Random random = new Random();

    private int x;
    private int y;
    private int health;
    private int hunger;
    private int speed;

    private LeftRightDirection leftRightDirection;

    private UpDownDirection upDownDirection;




    public Animal(int x, int y, int health, int hunger, int speed) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.hunger = hunger;
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }


    @Override
    public LeftRightDirection getLeftRightDirection() {
        return leftRightDirection;
    }

    @Override
    public void setLeftRightDirection(LeftRightDirection leftRightDirection) {
        this.leftRightDirection = leftRightDirection;
    }

    @Override
    public UpDownDirection getUpDownDirection() {
        return upDownDirection;
    }

    @Override
    public void setUpDownDirection(UpDownDirection upDownDirection) {
        this.upDownDirection = upDownDirection;
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

    @Override
    public void randomMove() {
        int position = random.nextInt(speed) + 1;
        XRandomMove(position);
        YRandomMove(position);
    }

    private void YRandomMove(int position) {
        switch (upDownDirection) {
            case UP -> {
                int newPosition = getY() + position;
                setY(Math.min(newPosition,Safari.MAX_HEIGHT));
                if (getY() == Safari.MAX_HEIGHT)
                    setUpDownDirection(UpDownDirection.DOWN);
            }

            case DOWN -> {
                int newPosition = getY() - position;
                setY(Math.max(newPosition,0));
                if (getY() == 0)
                    setUpDownDirection(UpDownDirection.UP);

            }
        }
    }

    private void XRandomMove(int position) {
        switch (leftRightDirection) {
            case LEFT -> {
                int newPosition = getX() - position;
                setX(Math.max(newPosition,0));
                if (getX() == 0)
                    setLeftRightDirection(LeftRightDirection.RIGHT);
            }
            case RIGHT -> {
                int newPosition = getX() + position;
                setX(Math.min(newPosition,Safari.MAX_WIDTH));
                if (getX() == Safari.MAX_WIDTH)
                    setLeftRightDirection(LeftRightDirection.LEFT);
            }
        }
    }

}
