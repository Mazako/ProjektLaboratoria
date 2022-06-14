package model.board;

import javafx.scene.canvas.GraphicsContext;
import model.livings.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Safari {

    private static Random random = new Random();

    private boolean done = false;
    public static int MAX_WIDTH = 800;
    public static int MAX_HEIGHT = 800;
    private ArrayList<Putable> objects;

    private int tick;

    private int ticksCountToSpawnPlant;
    private int plantsSpawnPerTick;
    private int hungerPerTickValue;

    private int attackCooldown;
    private Initializator init;

    public Safari(Initializator initializator) {
        objects = new ArrayList<>();

        initializePlants(objects, initializator);
        initializeLions(objects, initializator);
        initializeZebras(objects, initializator);
        initializeSnakes(objects, initializator);
        initializeGiraffes(objects, initializator);
        setRandomDirections();
        setTargets();
        ticksCountToSpawnPlant = initializator.getTicksPerPlantSpawn();
        plantsSpawnPerTick = initializator.getPlantsPerTick();
        hungerPerTickValue = initializator.getHungerPerTick();
        attackCooldown = initializator.getAttackCooldown();

        init = initializator;
        tick = 0;
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
            objects.add(new Plant(x,y,healvalue));

        }
    }

    private void initializeGiraffes(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getGiraffes(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int health = initializator.getGiraffeHealth();
            int speed = initializator.getGiraffeSpeed();
            objects.add(new Giraffe(x,y,health,speed));
        }
    }

    private void initializeSnakes(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getSnakes(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int health = initializator.getSnakeHealth();
            int speed = initializator.getSnakeSpeed();
            int attackValue = initializator.getSnakeAttackValue();
            objects.add(new Snake(x,y,health,speed,attackValue));
        }
    }

    private void initializeZebras(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getZebras(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int health = initializator.getZebraHealth();
            int speed = initializator.getZebraSpeed();
            objects.add(new Zebra(x,y,health,speed));
        }
    }

    private void initializeLions(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getLions(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int health = initializator.getLionHealth();
            int speed = initializator.getLionSpeed();
            int attackValue = initializator.getLionAttackValue();
            Lion lion = new Lion(x,y,health,speed,attackValue);
            objects.add(lion);
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

    public int getTicksCountToSpawnPlant() {
        return ticksCountToSpawnPlant;
    }

    public void setTicksCountToSpawnPlant(int ticksCountToSpawnPlant) {
        this.ticksCountToSpawnPlant = ticksCountToSpawnPlant;
    }

    public int getPlantsSpawnPerTick() {
        return plantsSpawnPerTick;
    }

    public void setPlantsSpawnPerTick(int plantsSpawnPerTick) {
        this.plantsSpawnPerTick = plantsSpawnPerTick;
    }

    public int getHungerPerTickValue() {
        return hungerPerTickValue;
    }

    public void setHungerPerTickValue(int hungerPerTickValue) {
        this.hungerPerTickValue = hungerPerTickValue;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
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

    public int getTick() {
        return tick;
    }


    public void step() {
        if (!done) {
            checkPlantSpawn();
            setTargets();
            Iterator<Putable> iterator = objects.iterator();
            while (iterator.hasNext()) {
                Putable p = iterator.next();
                if (p instanceof Movable) {
                    if (((Movable) p).hasTarget()) {
                        if (p instanceof Carnivore) {
                            if (((Carnivore) p).canAttack()) {
                                targetMove(p);
                            } else {
                                ((Carnivore) p).randomMove();
                            }
                        } else if (p instanceof Herbivore) {
                            targetMove(p);
                        }
                    } else {
                        if (p instanceof Herbivore && plantSize() == 0)
                            ((Herbivore) p).randomMove();
                        if (p instanceof Carnivore && hebivoreSize() == 0)
                            ((Carnivore) p).randomMove();
                        else
                            ((Movable) p).findTarget(objects);
                    }
                }
                if (p instanceof Plant) {
                    if (((Plant) p).isEaten()) {
                        deletePlantsTargets((Plant) p);
                        iterator.remove();
                    }
                } else if (p instanceof Animal) {
                    if (!((Animal) p).isAlive()) {
                        deleteAnimalTargets((Animal) p);
                        iterator.remove();
                    }
                }

            }
            objects.stream()
                    .filter(x -> x instanceof Carnivore)
                    .forEach(x -> {
                        ((Carnivore) x).downCooldown();
                        ((Carnivore) x).setHealth(((Carnivore) x).getHealth() - hungerPerTickValue);
                    });

            tick++;
            checkDone();
        }
        }

    private void deleteAnimalTargets(Animal p) {
        for (Putable object : objects) {
            if (object instanceof Animal && ((Animal) object).hasTarget()) {
                if (((Animal) object).getTarget().equals(p))
                    ((Animal) object).setTarget(null);
            }
        }
    }

    private void checkPlantSpawn() {
        if (tick % ticksCountToSpawnPlant == 0) {
            for (int i = 0; i < plantsSpawnPerTick; i++) {
                int x = getRandomX();
                int y = getRandomY();
                int healvalue = random.nextInt(init.getHealValueMax() - init.getHealValueMin() + 1) + init.getHealValueMin();
                objects.add(new Plant(x,y,healvalue));
            }
        }
    }

    private void targetMove(Putable p) {
        int prevX = p.getX();
        int prevY = p.getY();
        ((Movable) p).moveToTarget();
        if (isSomeoneHere(p.getX(), p.getY())) {
            p.setX(prevX);
            p.setY(prevY);
            ((Movable) p).randomMove();
        }

        if (((Movable) p).isInRangeOfTarget()) {
            if (p instanceof Herbivore) {
                ((Herbivore) p).eat(((Herbivore) p).getTarget());
            }
            if (p instanceof Carnivore) {
                ((Carnivore) p).attack(((Carnivore) p).getTarget(), attackCooldown);
            }
        }
    }

    private void deletePlantsTargets(Plant plant) {
            for (Putable object : objects) {
                if (object instanceof Herbivore && ((Herbivore) object).hasTarget()) {
                    if (((Herbivore) object).getTarget().equals(plant))
                        ((Herbivore) object).setTarget(null);
                }
            }
        }

        boolean isSomeoneHere(int x, int y) {
            long count = objects.stream()
                    .filter(o -> !(o instanceof Plant))
                    .filter(o -> o.getX() == x && o.getY() == y)
                    .count();
            return count > 1;
        }

        private void checkDone() {
            if (carnivoresSize() == 0 || hebivoreSize() == 0)
                done = true;
        }

    public boolean isDone() {
        return done;
    }
}

