package model.livings;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Herbivore extends Animal {

    private Plant target;

    public Herbivore(int x, int y, int health, int hunger, int speed) {
        super(x, y, health, hunger, speed);
    }

    public Plant getTarget() {
        return target;
    }

    public void setTarget(Plant target) {
        this.target = target;
    }

    @Override
    public boolean hasTarget() {
        return target != null;
    }

    @Override
    public void findTarget(List<Putable> list) {
        list.stream()
                .filter(x -> x instanceof Plant)
                .min(Comparator.comparing(this::distanceBetween))
                .ifPresent(x -> this.setTarget((Plant) x));
        System.out.println(getTarget());
    }

    @Override
    public void moveToTarget() {
        if (target.isEaten()) {
            setTarget(null);
        }
        if (getX() == getTarget().getX() && getY() == getTarget().getY()) {
            eat(target);
        }
        else {
            double[][] distanceTab = initDistanceTab();
            int[] bestCoords = findBestCoords(distanceTab);
            setX(bestCoords[0]);
            setY(bestCoords[1]);
        }

    }

    public void eat(Plant target) {
        target.die();
        setTarget(null);
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
}
