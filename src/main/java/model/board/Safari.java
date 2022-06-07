package model.board;

import model.livings.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class Safari {

    private static Random random = new Random();

    public static int MAX_WIDTH = 600;
    public static int MAX_HEIGHT = 600;
    private ArrayList<Putable> objects;

    public Safari(Initializator initializator) {
        objects = new ArrayList<>();

        initializePlants(objects, initializator);
        //initializeLions(objects, initializator);
        initializeZebras(objects, initializator);
        //initializeSnakes(objects, initializator);
        initializeGiraffes(objects, initializator);
        setRandomDirections();
    }

    private void setRandomDirections() {
        for (Putable object : objects) {
            if (object instanceof Movable) {
                ((Movable) object).setUpDownDirection(UpDownDirection.fromInt(random.nextInt(2)));
                ((Movable) object).setLeftRightDirection(LeftRightDirection.fromInt(random.nextInt(2)));
            }
        }
    }

    private void initializePlants(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getPlants(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int healvalue = random.nextInt(initializator.getHealValueMax() - initializator.getHealValueMin() + 1) + initializator.getHealValueMin();
            int hungerValue = random.nextInt(initializator.getHungerValueMax() - initializator.getHungerValueMin() + 1) + initializator.getHungerValueMin();
            objects.add(new Plant(x,y,healvalue,hungerValue));

        }
    }

    private void initializeGiraffes(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getGiraffes(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int health = initializator.getGiraffeHealth();
            int hunger = initializator.getGiraffeHunger();
            int speed = initializator.getGiraffeSpeed();
            objects.add(new Giraffe(x,y,health,hunger,speed));
        }
    }

    private void initializeSnakes(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getSnakes(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int health = initializator.getSnakeHealth();
            int hunger = initializator.getSnakeHunger();
            int speed = initializator.getSnakeSpeed();
            int attackValue = initializator.getSnakeAttackValue();
            objects.add(new Snake(x,y,health,hunger,speed,attackValue));
        }
    }

    private void initializeZebras(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getZebras(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int health = initializator.getZebraHealth();
            int hunger = initializator.getZebraHunger();
            int speed = initializator.getZebraSpeed();
            objects.add(new Zebra(x,y,health,hunger,speed));
        }
    }

    private void initializeLions(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getLions(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int health = initializator.getLionHealth();
            int hunger = initializator.getLionHunger();
            int speed = initializator.getLionSpeed();
            int attackValue = initializator.getLionAttackValue();
            objects.add(new Lion(x,y,health,hunger,speed,attackValue));
        }
    }

    private int getRandomX() {
        return random.nextInt(MAX_WIDTH + 1);
    }

    private int getRandomY() {
        return random.nextInt(MAX_HEIGHT + 1);
    }

    public Safari() {
        objects = new ArrayList<>();
    }


    public int carnivoresSize() {
        return predicateSize(x -> x instanceof Carnivore);
    }

    public int hebivoreSize() {
        return predicateSize(x -> x instanceof Herbivore);
    }

    public int plantSize() {
        return predicateSize(x -> x instanceof Plant);
    }

    public int giraffeSize() {
        return predicateSize(x -> x instanceof Giraffe);
    }

    public int snakeSize() {
        return predicateSize(x -> x instanceof Snake);
    }

    public int lionSize() {
        return predicateSize(x -> x instanceof Lion);
    }

    public int zebraSize() {
        return predicateSize(x -> x instanceof Zebra);
    }

    public int fullSize() {
        return objects.size();
    }

    public void remove(Putable p) {
        objects.remove(p);
    }

    public void add(Putable p) {
        objects.add(p);
    }

    public Iterator<Putable> getIterator() {
        return objects.iterator();
    }

    private int predicateSize(Predicate<Putable> predicate) {
        return (int) objects.stream()
                .filter(predicate)
                .count();
    }

    public void setTargets() {
        for (Putable object : objects) {
            if (object instanceof Movable) {
                ((Movable) object).findTarget(objects);
            }
        }
    }

    public ArrayList<Putable> getObjects() {
        return objects;
    }
}
