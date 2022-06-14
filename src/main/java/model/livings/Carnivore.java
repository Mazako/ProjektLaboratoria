package model.livings;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public abstract class Carnivore extends Animal {

    public int cooldown ;
    private int attackValue;

    public Carnivore(int x, int y, int health, int speed, int attackValue) {
        super(x, y, health, speed);
        this.attackValue = attackValue;
        this.cooldown = 10;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }


    public  void attack(Animal animal, int cooldownValue) {
        animal.setHealth(animal.getHealth() - getAttackValue());
        if (animal.getHealth() <= 0) {
            setHealth(Math.min(getMaxHp(), getHealth() + animal.getMaxHp()));
        }
        startCooldown(cooldownValue);
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

    public void startCooldown(int cooldownValue) {
        cooldown = cooldownValue;
    }
    public void downCooldown() {
        if (cooldown > 0)
            cooldown--;
    }

    public boolean canAttack() {
        return cooldown == 0;
    }


}
