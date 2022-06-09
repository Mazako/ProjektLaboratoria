package model.livings;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public abstract class Carnivore extends Animal {
    private int attackValue;

    public Carnivore(int x, int y, int health, int hunger, int speed, int attackValue) {
        super(x, y, health, hunger, speed);
        this.attackValue = attackValue;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }


    public abstract void attack(Animal animal);

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
                 .ifPresent(x -> this.setTarget(x));
    }

}
