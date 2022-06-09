package model.livings;

import controller.simulationWindowController;
import model.board.Safari;

import java.util.ArrayList;
import java.util.Random;

public abstract class Animal implements Movable, Putable {

    private static final Random random = new Random();

    private int x;
    private int y;
    private int health;
    private int hunger;
    private int speed;

    private LeftRightDirection leftRightDirection;

    private UpDownDirection upDownDirection;

    private Putable Target;

    private final int maxHp;


    public Animal(int x, int y, int health, int hunger, int speed) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.hunger = hunger;
        this.speed = speed;
        maxHp = health;
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
    public Putable getTarget() {
        return Target;
    }

    public void setTarget(Putable target) {
        Target = target;
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
        int position = getSpeed();
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


    public void moveToTarget() {
        double[][] distanceTab = initDistanceTab();

        int[] bestCoords = findBestCoords(distanceTab);
        //System.out.println("Aktualne: " + getX() + ", " + getY() +  "; najlepsze: " + bestCoords[0] + ", " + bestCoords[1]);
        setX(bestCoords[0]);
        setY(bestCoords[1]);

    }

    private int[] findBestCoords(double[][] distanceTab) {
        int[] coords = new int[2];
        int bestX = getX();
        int bestY = getY();
        double bestDistance = distanceBetween(getTarget());
        for (int x = 0; x < distanceTab.length; x++) {
            for (int y = 0; y < distanceTab.length; y++) {
                if (distanceTab[x][y] < bestDistance) {
                    bestDistance = distanceTab[x][y];
                    bestX = getX() - getSpeed() + x;
                    bestY = getY() - getSpeed() + y;
                }
            }
        }
        coords[0] = bestX;
        coords[1] = bestY;
        return coords;
    }

    private double[][] initDistanceTab() {
        int tabLen = (getSpeed() * 2) + 1;
        double[][] tab = new double[tabLen][tabLen];
        for (int x = 0; x < tabLen; x++) {
            int newX = (getX() - getSpeed()) +  x;
            for (int y = 0; y < tabLen; y++) {
                int newY = (getY() - getSpeed()) + y;
                double newDistance = Math.sqrt(Math.pow(newX-getTarget().getX(),2) + Math.pow(newY - getTarget().getY(),2));
                tab[x][y] = newDistance;
            }
        }
        return tab;


    }

    @Override
    public boolean isInRangeOfTarget() {
        for (int i = -simulationWindowController.RADIUS; i <= simulationWindowController.RADIUS; i++) {
            for (int j = -1; j<= 1; j++) {
                if (getX() + i == getTarget().getX() && getY() + j == getTarget().getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getMaxHp() {
        return maxHp;
    }

}
