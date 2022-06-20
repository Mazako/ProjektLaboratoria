package model.livings;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public abstract class Carnivore extends Animal {

    /**
     *
     */
    private static final int COOLDOWN_VALUE = 8;

    /** czas odnowienia miesozercy
     *
     */
    public int cooldown ;
    /**wartosc ataku miesozercy
     *
     */
    private int attackValue;

    /** Konstruktor przyjmujacy parametry miesozercy
     * @param x wspolrzedna 'x' miesozercy
     * @param y wspolrzedna 'y' miesozercy
     * @param health zdrowie miesozercy
     * @param hunger glod miesozercy
     * @param speed predkosc miesozercy
     * @param attackValue wartosc ataku miesozercy
     */
    public Carnivore(int x, int y, int health, int hunger, int speed, int attackValue) {
        super(x, y, health, hunger, speed);
        this.attackValue = attackValue;
        this.cooldown = 10;
    }

    /** getter na wartosc ataku
     * @return
     */
    public int getAttackValue() {
        return attackValue;
    }

    /** setter na wartosc ataku
     * @param attackValue
     */
    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }


    /** Metoda atakujaca inne zwierze
     * @param animal zwierze
     */
    public  void attack(Animal animal) {
        animal.setHealth(animal.getHealth() - getAttackValue());
        startCooldown();
    }

    @Override
    public boolean hasTarget() {
        return getTarget() != null;
    }

    @Override
    public Herbivore getTarget() {
        return (Herbivore) super.getTarget();
    }

    @Override
    public void findTarget(List<Putable> list) {
         list.stream()
                 .filter(x -> x instanceof Herbivore)
                 .min(Comparator.comparing(this::distanceBetween))
                 .ifPresent(this::setTarget);
    }

    /** Metoda tworzaca czas odnowienia
     *
     */
    public void startCooldown() {
        cooldown = COOLDOWN_VALUE;
    }

    /** Metoda zmniejszajaca czas odnowienia
     *
     */
    public void downCooldown() {
        if (cooldown > 0)
            cooldown--;
    }

    /** Metoda sprawdzajaca czy moze atakowac
     * @return
     */
    public boolean canAttack() {
        return cooldown == 0;
    }

}
