package com.example;

import com.sun.tools.javac.util.Pair;

import java.awt.*;
import java.util.Random;

public class ExampleSimulation extends GraphicalSimulation {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int POINT_SIZE = 10;
    private static final Integer GRAVITY = 2;

    private Random random;
    private Pair<Integer, Integer>[] points;
    private Color[] colors;
    private int velocity;

    public ExampleSimulation(int numPoints, int velocity) {
        super("Gravity Simulation", SCREEN_WIDTH, SCREEN_HEIGHT);

        this.velocity = velocity;
        this.random = new Random();
        points = new Pair[numPoints];
        colors = new Color[numPoints];

        // initialize points to random locations on screen and with random colors
        for (int i = 0; i < numPoints; i++) {
            int x = random.nextInt(SCREEN_WIDTH);
            int y = random.nextInt(SCREEN_HEIGHT);
            points[i] = new Pair<>(x,y);
            // pick a random color, but make sure it isn't too dark by making it at least 0x30 in each color channel
            colors[i] = new Color(random.nextInt(0xffffff) | 0x303030);
        }

        // run simultion every 50 milliseconds (e.g., 10 times a second)
        sleepTime = 50;

        // start the simulation.
        repaint();
    }

    private int moveRandomlyWithinBounds(int currentValue, int lowerBound, int upperBound) {
        // move the value somewhere between +velocity and -velocity
        int newValue = currentValue + random.nextInt((2 * velocity) + 1) - velocity;

        // then make sure it stays in bounds
        if (newValue < lowerBound) {
            return lowerBound;
        }
        if (newValue > upperBound) {
            return upperBound;
        }
        return newValue;
    }

    private void simulate() {
        // randomly updates the position of each point, but with 'gravity' pulling the points to the left.
        for (int i = 0; i < points.length; i++) {
            int x = moveRandomlyWithinBounds(points[i].fst - GRAVITY, 0, SCREEN_WIDTH - POINT_SIZE);
            int y = moveRandomlyWithinBounds(points[i].snd, POINT_SIZE, SCREEN_HEIGHT);
            points[i] = new Pair<>(x, y);
        }
    }

    @Override
    public void paint(Graphics brush) {
        // update the locations of each point
        simulate();

        // paint background black
        brush.setColor(Color.black);
        brush.fillRect(0, 0, width, height);

        // paint all of the points in their new locations
        for (int i = 0; i < points.length; i++) {
            brush.setColor(colors[i]);
            brush.fillRect(points[i].fst, points[i].snd, POINT_SIZE, POINT_SIZE);
        }
    }

    public static void main(String[] args) {
        new ExampleSimulation(200, 5);
    }
}
