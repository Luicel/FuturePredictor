package me.calebclifton.models;

import me.calebclifton.FuturePredictor;
import me.calebclifton.utils.RandomUtils;

public class Prediction {
    private int overallHappiness;
    private int successChance;
    private int stubbingToeChance;
    private int eatingRawGroundBeefChance;
    private int reachDreamsChance;

    private String causeOfDeath;
    private final String[] causesOfDeath = {"car accident", "fall", "fatal wound", "suffocation", "eaten alive", "old age", "death penalty"};
    private String favoriteCondiment;
    private final String[] condiments = {"ketchup", "mustard", "mayo", "relish", "BBQ sauce"};

    private boolean willFindTrueLove;
    private boolean willBelievePrediction;

    public Prediction(String name) {
        if (FuturePredictor.database.doesNameExistInDatabase(name)) {
            initFieldsFromExisting(name);
        } else {
            initFieldsFromNew();
            FuturePredictor.database.insertPredictionIntoDatabase(name, this);
        }
    }

    private void initFieldsFromNew() {
        overallHappiness = RandomUtils.getRandomPercent();
        successChance = RandomUtils.getRandomPercent();
        stubbingToeChance = RandomUtils.getRandomPercent();
        eatingRawGroundBeefChance = RandomUtils.getRandomPercent();
        reachDreamsChance = RandomUtils.getRandomPercent();

        causeOfDeath = RandomUtils.getRandomStringElementFrom(causesOfDeath);
        favoriteCondiment = RandomUtils.getRandomStringElementFrom(condiments);

        willFindTrueLove = RandomUtils.getRandomBool();
        willBelievePrediction = false; // haha very funny
    }

    private void initFieldsFromExisting(String name) {
        overallHappiness = FuturePredictor.database.getIntFromDatabase(name, "overall_happiness");
        successChance = FuturePredictor.database.getIntFromDatabase(name, "success_chance");
        stubbingToeChance = FuturePredictor.database.getIntFromDatabase(name, "stubbing_toe_chance");
        eatingRawGroundBeefChance = FuturePredictor.database.getIntFromDatabase(name, "eating_raw_ground_beef_chance");
        reachDreamsChance = FuturePredictor.database.getIntFromDatabase(name, "reach_dreams_chance");

        causeOfDeath = FuturePredictor.database.getStringFromDatabase(name, "cause_of_death");
        favoriteCondiment = FuturePredictor.database.getStringFromDatabase(name, "favorite_condiment");

        willFindTrueLove = FuturePredictor.database.getBooleanFromDatabase(name, "will_find_true_love");
        willBelievePrediction = FuturePredictor.database.getBooleanFromDatabase(name, "will_believe_prediction");
    }

    public int getOverallHappiness() {
        return overallHappiness;
    }

    public int getSuccessChance() {
        return successChance;
    }

    public int getStubbingToeChance() {
        return stubbingToeChance;
    }

    public int getEatingRawGroundBeefChance() {
        return eatingRawGroundBeefChance;
    }

    public int getReachDreamsChance() {
        return reachDreamsChance;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public String getFavoriteCondiment() {
        return favoriteCondiment;
    }

    public boolean willFindTrueLove() {
        return willFindTrueLove;
    }

    public boolean willBelievePrediction() {
        return willBelievePrediction;
    }
}
