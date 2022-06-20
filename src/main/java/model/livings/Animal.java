package model.livings;

import controller.simulationWindowController;
import model.board.Safari;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


/**
 *ggdsgs
 *
 */
public abstract class Animal implements Movable, Putable {

    private static final Random random = new Random();

    public static int TARGET_RANGE = (simulationWindowController.RADIUS);

    private int x;
    private int y;
    private int health;
    private int hunger;
    private int speed;

    private LeftRightDirection leftRightDirection;

    private UpDownDirection upDownDirection;

    private Putable Target;

    private final int maxHp;


    /** Konstruktor przyjmujacy parametry zwierzecia
     *
     * @param x wspolrzedna 'x' zwierzaka
     * @param y wspolrzedna 'y' zwierzaka
     * @param health zdrowie zwierzaka
     * @param speed szybkosc zwierzaka
     */
    public Animal(int x, int y, int health, int speed) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.speed = speed;
        maxHp = health;
    }

    /** Metoda zwraca aktualne zdrowie zwierzecia
     * @return aktualna ilosc zwierzecia
     */
    public int getHealth() {
        return health;
    }

    /** metoda ustawia aktualne zdrowie zwierzecia
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /** Metoda zwraca aktualna predkosc zwierzecia
     * @return aktualna szybkosc zwierzecia
     */
    public int getSpeed() {
        return speed;
    }

    /** Metoda ustawia aktualna szybkosc zwierzecia
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /** Metoda zwraca aktualne zdrowie zwierzecia
     * @return aktualny głod zwierzecia
     */
    public int getHunger() {
        return hunger;
    }

    /** Metoda ustawia aktualne zdrowie zwierzecia
     * @param hunger
     */
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


    /**
     * Metoda przenosi obiekt do puntku
     * @param x wspolrzedna 'x' na planszy
     * @param y wspolrzedna 'y' na planszy
     */
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

    /** Metoda losowo zmienia położenie naszego zwierzaka w kierunku 'Y'
     * @param position dodatkowa wspolrzedna 'y'
     */
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

    /** Metoda losowo zmienia położenie naszego zwierzaka w kierunku 'X'
     * @param position
     */
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


    /**Metoda ktora szuka najszybszej drogi do celu i wykonuje ruch do tego celu
     *
     */
    public void moveToTarget() {
        double[][] distanceTab = initDistanceTab();

        int[] bestCoords = findBestCoords(distanceTab);
        //System.out.println("Aktualne: " + getX() + ", " + getY() +  "; najlepsze: " + bestCoords[0] + ", " + bestCoords[1]);
        setX(bestCoords[0]);
        setY(bestCoords[1]);

    }

    /**Metoda szukajaca najlepszych wspolrzednych, aby poruszyc sie do celu o krok
     * @param distanceTab
     * @return
     */
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

    /**Metoda zwracajaca tablice wszystkich mozliwych ruchow i odleglosci do celu od tych pol
     * @return
     */
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
        for (int i = -TARGET_RANGE; i <= TARGET_RANGE; i++) {
            for (int j = -TARGET_RANGE; j<= TARGET_RANGE; j++) {
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

    public boolean isAlive() {
        return getHealth() > 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return x == animal.x && y == animal.y && health == animal.health && hunger == animal.hunger && speed == animal.speed && maxHp == animal.maxHp && leftRightDirection == animal.leftRightDirection && upDownDirection == animal.upDownDirection && Objects.equals(Target, animal.Target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, health, hunger, speed, leftRightDirection, upDownDirection, Target, maxHp);
    }
}
