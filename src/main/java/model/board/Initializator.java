package model.board;

public class Initializator {

    //lew
    private int lionHealth = 200;
    private int lionSpeed = 2;
    private int lionAttackValue =150;

    //żyrafa
    private int giraffeHealth = 300;
    private int giraffeSpeed = 2;

    //wąż
    private int snakeHealth = 150;
    private int snakeSpeed = 3;
    private int snakeAttackValue = 50;

    //zebra
    private int zebraHealth = 250;
    private int zebraSpeed = 3;

    //roślina

    private int healValueMin = 50;
    private int healValueMax = 70;

    private int plantsPerTick = 2;

    private int ticksPerPlantSpawn = 100;

    //ilości startowe

    private int lions = 10;
    private int snakes = 10;
    private int zebras = 10;
    private int giraffes = 10;
    private int plants = 10;

    private int attackCooldown;

    private int hungerPerTick;

    private boolean showDistances;



    public int getLionHealth() {
        return lionHealth;
    }

    public void setLionHealth(int lionHealth) {
        this.lionHealth = lionHealth;
    }



    public int getLionSpeed() {
        return lionSpeed;
    }

    public void setLionSpeed(int lionSpeed) {
        this.lionSpeed = lionSpeed;
    }

    public int getLionAttackValue() {
        return lionAttackValue;
    }

    public void setLionAttackValue(int lionAttackValue) {
        this.lionAttackValue = lionAttackValue;
    }

    public int getGiraffeHealth() {
        return giraffeHealth;
    }

    public void setGiraffeHealth(int giraffeHealth) {
        this.giraffeHealth = giraffeHealth;
    }


    public int getGiraffeSpeed() {
        return giraffeSpeed;
    }

    public void setGiraffeSpeed(int giraffeSpeed) {
        this.giraffeSpeed = giraffeSpeed;
    }

    public int getSnakeHealth() {
        return snakeHealth;
    }

    public void setSnakeHealth(int snakeHealth) {
        this.snakeHealth = snakeHealth;
    }


    public int getSnakeSpeed() {
        return snakeSpeed;
    }

    public void setSnakeSpeed(int snakeSpeed) {
        this.snakeSpeed = snakeSpeed;
    }

    public int getSnakeAttackValue() {
        return snakeAttackValue;
    }

    public void setSnakeAttackValue(int snakeAttackValue) {
        this.snakeAttackValue = snakeAttackValue;
    }

    public int getZebraHealth() {
        return zebraHealth;
    }

    public void setZebraHealth(int zebraHealth) {
        this.zebraHealth = zebraHealth;
    }


    public int getZebraSpeed() {
        return zebraSpeed;
    }

    public void setZebraSpeed(int zebraSpeed) {
        this.zebraSpeed = zebraSpeed;
    }

    public int getHealValueMin() {
        return healValueMin;
    }

    public void setHealValueMin(int healValueMin) {
        this.healValueMin = healValueMin;
    }

    public int getHealValueMax() {
        return healValueMax;
    }

    public void setHealValueMax(int healValueMax) {
        this.healValueMax = healValueMax;
    }


    public int getLions() {
        return lions;
    }

    public void setLions(int lions) {
        this.lions = lions;
    }

    public int getSnakes() {
        return snakes;
    }

    public void setSnakes(int snakes) {
        this.snakes = snakes;
    }

    public int getZebras() {
        return zebras;
    }

    public void setZebras(int zebras) {
        this.zebras = zebras;
    }

    public int getGiraffes() {
        return giraffes;
    }

    public void setGiraffes(int giraffes) {
        this.giraffes = giraffes;
    }

    public int getPlants() {
        return plants;
    }

    public void setPlants(int plants) {
        this.plants = plants;
    }

    public int getPlantsPerTick() {
        return plantsPerTick;
    }

    public void setPlantsPerTick(int plantsPerTick) {
        this.plantsPerTick = plantsPerTick;
    }

    public int getTicksPerPlantSpawn() {
        return ticksPerPlantSpawn;
    }

    public void setTicksPerPlantSpawn(int ticksPerPlantSpawn) {
        this.ticksPerPlantSpawn = ticksPerPlantSpawn;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public int getHungerPerTick() {
        return hungerPerTick;
    }

    public void setHungerPerTick(int hungerPerTick) {
        this.hungerPerTick = hungerPerTick;
    }

    public boolean isShowDistances() {
        return showDistances;
    }

    public void setShowDistances(boolean showDistances) {
        this.showDistances = showDistances;
    }
}
