package com.example;

public class ParameterBlock {
    private int screenWidth = 800;
    private int screenHeight = 640;
    private int numCellsWide = 1;
    private int numCellsHigh = 1;
    private double maxStartingBunnies = 0;
    private double maxStartingWolves = 0;
    private double vegetationProportionalGrowthRate = .1;
    private double vegetationLinearGrowthRate = 200;
    private double maxVegetation = 1000;

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getNumCellsWide() {
        return numCellsWide;
    }

    public int getNumCellsHigh() {
        return numCellsHigh;
    }

    public double getMaxStartingBunnies() {
        return maxStartingBunnies;
    }

    public double getMaxStartingWolves() {
        return maxStartingWolves;
    }

    public double getVegetationProportionalGrowthRate() {
        return vegetationProportionalGrowthRate;
    }

    public double getVegetationLinearGrowthRate() {
        return vegetationLinearGrowthRate;
    }

    public double getMaxVegetation() {
        return maxVegetation;
    }
}
