package model.livings;

public abstract class Carnivore extends Animal {
    private int attackValue;
    private Animal target;

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

    public Animal getTarget() {
        return target;
    }

    public void setTarget(Animal target) {
        this.target = target;
    }

    public abstract void attack(Animal animal);

    @Override
    public boolean hasTarget() {
        return target == null;
    }
}
