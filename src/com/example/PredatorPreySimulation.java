package com.example;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.awt.*;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class PredatorPreySimulation extends GraphicalSimulation {

    // a random number generator for use by methods in this class.
    private static Random random = new Random();

    private ParameterBlock parameterBlock;
//     private Cell[][] cells;
    private final int cellWidth;
    private final int cellHeight;
    private int epoch = 0;

//    private void AddRandomNumberOfBunnies(Cell cell, double maxBunnies) {
//        double numberOfBunnies = maxBunnies * random.nextDouble();
//        while (random.nextDouble() < numberOfBunnies) {
//            cell.addAnimal(new Rabbit(cell));
//            numberOfBunnies--;
//        }
//    }
//
//    private void AddRandomNumberOfWolves(Cell cell, double maxWolves) {
//        double numberOfWolves = maxWolves * random.nextDouble();
//        while (random.nextDouble() < numberOfWolves) {
//            cell.addAnimal(new Wolf(cell));
//            numberOfWolves--;
//        }
//    }

    private PredatorPreySimulation(ParameterBlock parameterBlock) {
        super("PredatorPreySimulation!", parameterBlock.getScreenWidth(), parameterBlock.getScreenHeight());

        this.parameterBlock = parameterBlock;
        cellWidth = parameterBlock.getScreenWidth() / parameterBlock.getNumCellsWide();
        cellHeight = parameterBlock.getScreenHeight() / parameterBlock.getNumCellsHigh();

//        this.cells = new Cell[parameterBlock.getNumCellsWide()][parameterBlock.getNumCellsHigh()];
//        for (int x = 0; x < parameterBlock.getNumCellsWide(); x++) {
//            for (int y = 0; y < parameterBlock.getNumCellsHigh(); y++) {
//                double vegetation = random.nextDouble() * parameterBlock.getMaxVegetation();
//                Cell cell = new Cell(this, x, y, cellWidth, cellHeight,
//                        vegetation, parameterBlock.getMaxVegetation(),
//                        parameterBlock.getVegetationProportionalGrowthRate(),
//                        parameterBlock.getVegetationLinearGrowthRate());
//                AddRandomNumberOfBunnies(cell, parameterBlock.getMaxStartingBunnies());
//                AddRandomNumberOfWolves(cell, parameterBlock.getMaxStartingWolves());
//                cells[x][y] = cell;
//            }
//        }

        // simulate 4 steps per second
        sleepTime = 250;

        // start the simulation.
        repaint();
    }

    /**
     * Find a cell with a given x, y coordinate.  If the requested cell is out of bounds, it returns the closest cell.
     * @param x
     * @param y
     * @return the specified Cell
     */
//    public Cell getCell(int x, int y) {
//        if (x < 0) {
//            x = 0;
//        } else if (x >= parameterBlock.getNumCellsWide()) {
//            x = parameterBlock.getNumCellsWide() - 1;
//        }
//
//        if (y < 0) {
//            y = 0;
//        } else if (y >= parameterBlock.getNumCellsHigh()) {
//            y = parameterBlock.getNumCellsHigh() - 1;
//        }
//
//        return cells[x][y];
//    }

    /**
     * Simulate 1 epoch of the simulation and then draw the current simulated state in the window.
     * @param brush
     */
    @Override
    public void paint(Graphics brush) {
        // print out the state of the simulation at the beginning of the epoch
//        System.out.println("epoch: " + epoch + " wolves: " + Wolf.getCount() + " bunnies: " + Rabbit.getCount());

        // first copy animals from the next lists into the current lists
//        for (int x = 0; x < parameterBlock.getNumCellsWide(); x++) {
//            for (int y = 0; y < parameterBlock.getNumCellsHigh(); y++) {
//                Cell cell = cells[x][y];
//                cell.copyNextAnimalsToCurrent();
//            }
//        }
//
//        // simulate each of the cells
//        for (int x = 0; x < parameterBlock.getNumCellsWide(); x++) {
//            for (int y = 0; y < parameterBlock.getNumCellsHigh(); y++) {
//                cells[x][y].update();
//            }
//        }

        // draw a black background; will in general be drawn over.
        brush.setColor(Color.black);
        brush.fillRect(0, 0, width, height);


//        for (int x = 0; x < parameterBlock.getNumCellsWide(); x++) {
//            for (int y = 0; y < parameterBlock.getNumCellsHigh(); y++) {
//                Cell cell = cells[x][y];
//
//                // draw the background of each cell according to its vegetation
//                brush.setColor(cell.getBackgroundColor());
//                brush.fillRect(cellWidth * x, cellHeight * y, cellWidth, cellHeight);
//
//                // draw the cells animals
//                cell.draw(brush);
//            }
//        }

        epoch++;
    }

    public static void main(String[] args) {
        ParameterBlock parameterBlock;

        try {

            if (args.length == 0) {

                // use default parameters if no arguments are given
                parameterBlock = new ParameterBlock();
            } else {

                // load a file and parse it as JSON if arguments are given
                String json = getFileContentsAsString(args[0]);
                Gson gson = new Gson();
                parameterBlock = gson.fromJson(json, ParameterBlock.class);
            }

            // run the simulation
            new PredatorPreySimulation(parameterBlock);

        } catch (IOException e) {
            System.out.println("Couldn't find file: " + args[0]);
        } catch (JsonParseException e) {
            System.out.println("File " + args[0] + " is not valid JSON.");
        }
    }

    private static String getFileContentsAsString(String filename) throws IOException {

        // Java uses Paths as an operating system-independent specification of the location of files.
        // In this case, we're looking for files that are in a directory called 'data' located in the
        // root directory of the project, which is the 'current working directory'.
        final Path path = FileSystems.getDefault().getPath("inputs", filename);


        // Read all of the bytes out of the file specified by 'path' and then convert those bytes
        // into a Java String.  Because this operation can fail if the file doesn't exist, we
        // include this in a try/catch block
        return new String(Files.readAllBytes(path));

    }
}
