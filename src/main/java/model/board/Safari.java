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

    /** Losowosc
     *
     */
    private static Random random = new Random();

    /** Czy zrobione
     *
     */
    private boolean done = false;
    /** Maksymalna szerokosc
     *
     */
    public static int MAX_WIDTH = 800;
    /** Maksymalna wysokosc
     *
     */
    public static int MAX_HEIGHT = 800;
    /** Lista skladajaca sie z obiektow
     *
     */
    private ArrayList<Putable> objects;

    /** ilosc tikow
     *
     */
    private int tick;

    /** Tiki do zrespienia sie obiektu
     *
     */
    private int ticksCountToSpawnPlant;
    /** Ilosc roslin zrespionych przez sekunde
     *
     */
    private int plantsSpawnPerTick;
    /** Glod na sekunde
     *
     */
    private int hungerPerTickValue;

    /** Czas odnowienia ataku
     *
     */
    private int attackCooldown;
    /** Inicjalizator
     *
     */
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

    /** Metoda ustawiajaca losowe kierunki
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

    /** Inicjalizacja roslin
     * @param objects obiekty
     * @param initializator inicjalizator
     */
    private void initializePlants(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getPlants(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int healvalue = random.nextInt(initializator.getHealValueMax() - initializator.getHealValueMin() + 1) + initializator.getHealValueMin();
            objects.add(new Plant(x,y,healvalue));

        }
    }

    /** Inicjalizacja zyraf
     * @param objects obiekty
     * @param initializator inicjalizator
     */
    private void initializeGiraffes(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getGiraffes(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int health = initializator.getGiraffeHealth();
            int speed = initializator.getGiraffeSpeed();
            objects.add(new Giraffe(x,y,health,speed));
        }
    }

    /** Inicjalizacja wezy
     * @param objects obiekty
     * @param initializator inicjalizator
     */
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

    /** Inicjalizacja zebr
     * @param objects obiekty
     * @param initializator inicjalizator
     */
    private void initializeZebras(ArrayList<Putable> objects, Initializator initializator) {
        for (int i = 0; i < initializator.getZebras(); i++) {
            int x = getRandomX();
            int y = getRandomY();
            int health = initializator.getZebraHealth();
            int speed = initializator.getZebraSpeed();
            objects.add(new Zebra(x,y,health,speed));
        }
    }

    /** Inicjalizacja lwow
     * @param objects obiekty
     * @param initializator inicjalizator
     */
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

    /** getter na losowego X
     * @return
     */
    private int getRandomX() {
        return random.nextInt(MAX_WIDTH + 1);
    }

    /** getter na losowego Y
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

    /** Rozmiar zwierzecia
     * @return
     */
    public int animalSize() {
        return hebivoreSize() + carnivoresSize();
    }

    /** Usuwanie obiektu
     * @param p obiekt
     */
    public void remove(Putable p) {
        objects.remove(p);
    }

    /** Dodawanie obiektu
     * @param p obiekt
     */
    public void add(Putable p) {
        objects.add(p);
    }

    /** Iterator obiektow
     * @return
     */
    public Iterator<Putable> getIterator() {
        return objects.iterator();
    }

    /** Getter na tiki do zespawnienia rosliny
     * @return
     */
    public int getTicksCountToSpawnPlant() {
        return ticksCountToSpawnPlant;
    }

    /** Setter na tiki do zespawnienia rosliny
     * @param ticksCountToSpawnPlant
     */
    public void setTicksCountToSpawnPlant(int ticksCountToSpawnPlant) {
        this.ticksCountToSpawnPlant = ticksCountToSpawnPlant;
    }

    /** Getter na zespawnienie rosliny w czasie 1 sekundy
     * @return
     */
    public int getPlantsSpawnPerTick() {
        return plantsSpawnPerTick;
    }

    /** Setter na zespawnienie rosliny w czasie 1 sekundy
     * @param plantsSpawnPerTick
     */
    public void setPlantsSpawnPerTick(int plantsSpawnPerTick) {
        this.plantsSpawnPerTick = plantsSpawnPerTick;
    }

    /** pobranie ilosci glodu na tick
     * @return
     */
    public int getHungerPerTickValue() {
        return hungerPerTickValue;
    }

    /** ustawienie ilosci glodu na tick
     * @param hungerPerTickValue
     */
    public void setHungerPerTickValue(int hungerPerTickValue) {
        this.hungerPerTickValue = hungerPerTickValue;
    }

    /**pobranie ilosci tickow pozwalajacych na ponowny atak miesozercy
     * @return
     */
    public int getAttackCooldown() {
        return attackCooldown;
    }

    /** ustawienie ilosci tickow pozwalajacych na ponowny atak miesozercy
     * @param attackCooldown
     */
    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    /** Metoda pomocnicza do obliczania rozmiarów poszczególnych obiektów na planszy
     * @param predicate
     * @return
     */
    private int predicateSize(Predicate<Putable> predicate) {
        return (int) objects.stream()
                .filter(predicate)
                .count();
    }

    /** Ustawianie obiektow
     *
     */
    public void setTargets() {
        for (Putable object : objects) {
            if (object instanceof Movable) {
                ((Movable) object).findTarget(objects);
            }
        }
    }

    /** lista zwracajaca obiekty
     * @return
     */
    public ArrayList<Putable> getObjects() {
        return objects;
    }

    /** getter na tik
     * @return
     */
    public int getTick() {
        return tick;
    }


    /** Metoda na chodzenie
     *
     */
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

    /** Metoda na usuwanie zwierzat
     * @param p zwierze
     */
    private void deleteAnimalTargets(Animal p) {
        for (Putable object : objects) {
            if (object instanceof Animal && ((Animal) object).hasTarget()) {
                if (((Animal) object).getTarget().equals(p))
                    ((Animal) object).setTarget(null);
            }
        }
    }

    /** Metoda sprawdzajaca spawn rosliny
     *
     */
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

    /** Poruszanie sie obiektu
     * @param p obiekty
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
                ((Carnivore) p).attack(((Carnivore) p).getTarget(), attackCooldown);
            }
        }
    }

    /** Usuwanie roslin
     * @param plant rosliny
     */
    private void deletePlantsTargets(Plant plant) {
            for (Putable object : objects) {
                if (object instanceof Herbivore && ((Herbivore) object).hasTarget()) {
                    if (((Herbivore) object).getTarget().equals(plant))
                        ((Herbivore) object).setTarget(null);
                }
            }
        }

    /** Metoda sprawdzajaca czy ktos znajduje sie na tych samych wspolrzednch
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

    /** Metoda sprawdzajaca czy ilosc miesozercow i roslinozercow jest rowna 0
     *
     */
        private void checkDone() {
            if (carnivoresSize() == 0 || hebivoreSize() == 0)
                done = true;
        }

    /** Czy skonczone
     * @return
     */
    public boolean isDone() {
        return done;
    }
}

