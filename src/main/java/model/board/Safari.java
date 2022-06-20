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

    /** generator liczb losowych
     *
     */
    private static Random random = new Random();

    /**
     * Maksymalna szerokosc planszy
     */
    public static int MAX_WIDTH = 800;
    /**
     * Maksymalna wysokosc planszy
     */
    public static int MAX_HEIGHT = 800;
    /**
     * Lista obiektow
     */
    private ArrayList<Putable> objects;

    /** krok symulacji
     *
     */
    private int tick;

    /**
     * Zlicza tiki do zrespienia rosliny
     */
    private int ticksCountToSpawnPlant;
    /**
     * Ilosc zrespionych roslin na jeden tik
     */
    private int plantsSpawnPerTick;

    /** Inizjalizator
     *
     */
    private Initializator init;

    /**
     * @param initializator
     */
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
        init = initializator;
        tick = 0;
    }

    /** Ustaw losowe kierunki
     *
     */
    private void setRandomDirections() {
        for (Putable object : objects) {
            if (object instanceof Movable) {
                ((Movable) object).setUpDownDirection(UpDownDirection.fromInt(random.nextInt(2)));
                ((Movable) object).setLeftRightDirection(LeftRightDirection.fromInt(random.nextInt(2)));
            }
        }
    }

    /** Metoda sluzaca do zrespienia rosliny na planszy
     * @param objects
     * @param initializator
     */
    private void initializePlants(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getPlants(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int healvalue = random.nextInt(initializator.getHealValueMax() - initializator.getHealValueMin() + 1) + initializator.getHealValueMin();
            int hungerValue = random.nextInt(initializator.getHungerValueMax() - initializator.getHungerValueMin() + 1) + initializator.getHungerValueMin();
            objects.add(new Plant(x,y,healvalue,hungerValue));

        }
    }

    /** Metoda sluzaca do zrespienia zyraf na planszy
     * @param objects
     * @param initializator
     */
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

    /** Metoda sluzaca do zrespienia wezy na planszy
     * @param objects
     * @param initializator
     */
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

    /** Metoda sluzaca do zrespienia zebr na planszy
     * @param objects
     * @param initializator
     */
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

    /** Metoda sluzaca do zrespienia lwow na planszy
     * @param objects
     * @param initializator
     */
    private void initializeLions(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getLions(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int health = initializator.getLionHealth();
            int hunger = initializator.getLionHunger();
            int speed = initializator.getLionSpeed();
            int attackValue = initializator.getLionAttackValue();
            Lion lion = new Lion(x,y,health,hunger,speed,attackValue);
            objects.add(lion);
        }
    }

    /** Losuje losowa wspolrzedna na osi X
     * @return
     */
    private int getRandomX() {
        return random.nextInt(MAX_WIDTH + 1);
    }

    /** Losuje losowa wspolrzedna na osi X
     * @return
     */
    private int getRandomY() {
        return random.nextInt(MAX_HEIGHT + 1);
    }

    public Safari() {
        objects = new ArrayList<>();
    }


    /** Rozmiar miesozercy
     * @return
     */
    public int carnivoresSize() {
        return predicateSize(x -> x instanceof Carnivore);
    }

    /** Rozmiar roslinozercy
     * @return
     */
    public int hebivoreSize() {
        return predicateSize(x -> x instanceof Herbivore);
    }

    /** Rozmiar rosliny
     * @return
     */
    public int plantSize() {
        return predicateSize(x -> x instanceof Plant);
    }

    /** Rozmiar zyrafy
     * @return
     */
    public int giraffeSize() {
        return predicateSize(x -> x instanceof Giraffe);
    }

    /** Rozmiar weza
     * @return
     */
    public int snakeSize() {
        return predicateSize(x -> x instanceof Snake);
    }

    /** Rozmiar lwa
     * @return
     */
    public int lionSize() {
        return predicateSize(x -> x instanceof Lion);
    }

    /** Rozmiar zebry
     * @return
     */
    public int zebraSize() {
        return predicateSize(x -> x instanceof Zebra);
    }

    /** Maksymalny rozmiar
     * @return
     */
    public int fullSize() {
        return objects.size();
    }

    /** Metoda sluzaca do usuwania obiektow
     * @param p
     */
    public void remove(Putable p) {
        objects.remove(p);
    }

    /** Metoda sluzaca do dodawania obiektow
     * @param p
     */
    public void add(Putable p) {
        objects.add(p);
    }

    /**
     * @return
     */
    public Iterator<Putable> getIterator() {
        return objects.iterator();
    }

    /** Metoda pomagajaca zwracac rozmiary tablicy
     * @param predicate
     * @return
     */
    private int predicateSize(Predicate<Putable> predicate) {
        return (int) objects.stream()
                .filter(predicate)
                .count();
    }

    /** Ustaw cele
     *
     */
    public void setTargets() {
        for (Putable object : objects) {
            if (object instanceof Movable) {
                ((Movable) object).findTarget(objects);
            }
        }
    }

    /**
     * @return
     */
    public ArrayList<Putable> getObjects() {
        return objects;
    }

    public int getTick() {
        return tick;
    }


    /** Metoda sluzaca do zmieany polozenia obiektow
     *
     */
    public void step() {
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
                }
            else if (p instanceof Animal) {
                if (!((Animal) p).isAlive()) {
                    deleteAnimalTargets((Animal) p);
                    iterator.remove();
                }
            }

            }
        objects.stream()
                .filter(x -> x instanceof Carnivore)
                .forEach(x -> ((Carnivore) x).downCooldown());

        tick++;
        }

    /** Metoda sluzaca usuwaniu zwierzecia
     * @param p
     */
    private void deleteAnimalTargets(Animal p) {
        for (Putable object : objects) {
            if (object instanceof Animal && ((Animal) object).hasTarget()) {
                if (((Animal) object).getTarget().equals(p))
                    ((Animal) object).setTarget(null);
            }
        }
    }

    /** Metoda sluzaca dodawaniu rosliny
     *
     */
    private void checkPlantSpawn() {
        if (tick % 100 == 0) {
            for (int i = 0; i < plantsSpawnPerTick; i++) {
                int x = getRandomX();
                int y = getRandomY();
                int healvalue = random.nextInt(init.getHealValueMax() - init.getHealValueMin() + 1) + init.getHealValueMin();
                int hungerValue = random.nextInt(init.getHungerValueMax() - init.getHungerValueMin() + 1) + init.getHungerValueMin();
                objects.add(new Plant(x,y,healvalue,hungerValue));
            }
        }
    }

    /** Metoda sluzaca poruszaniu sie do celu
     * @param p obiekt
     */
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
                ((Carnivore) p).attack(((Carnivore) p).getTarget());
            }
        }
    }

    /** Usuwa cele z martwych zwierzat
     * @param plant
     */
    private void deletePlantsTargets(Plant plant) {
            for (Putable object : objects) {
                if (object instanceof Herbivore && ((Herbivore) object).hasTarget()) {
                    if (((Herbivore) object).getTarget().equals(plant))
                        ((Herbivore) object).setTarget(null);
                }
            }
        }

    /** Czy ktos znajduje sie na zadanych wspolrzednych na planszy
     * @param x wspolrzedna 'x'
     * @param y wspolrzedna 'y'
     * @return
     */
        boolean isSomeoneHere(int x, int y) {
            long count = objects.stream()
                    .filter(o -> !(o instanceof Plant))
                    .filter(o -> o.getX() == x && o.getY() == y)
                    .count();
            return count > 1;
        }




}

