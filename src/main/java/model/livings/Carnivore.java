package model.livings;

public abstract class Carnivore extends Animal {
    private double attackValue;
    private Animal target;

    public Carnivore(int x, int y, double health, double hunger, int speed, double attackValue) {
        super(x, y, health, hunger, speed);
        this.attackValue = attackValue;
    }

    public double getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(double attackValue) {
        this.attackValue = attackValue;
    }

    public Animal getTarget() {
        return target;
    }

    public void setTarget(Animal target) {
        this.target = target;
    }

    public abstract void attack(Animal animal);
}
