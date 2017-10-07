package com.example;



/**
 * CLASS: GraphicalSimulation
 * DESCRIPTION: A painted canvas in its own window, updated every 'sleepTime' milliseconds.
 * NOTE: You don't need to understand the details here, no fiddling neccessary.
 */

import java.awt.*;
import java.awt.event.*;

abstract class GraphicalSimulation extends Canvas {
    private static final int SLEEP_TIME = 1000;
    private static final int TITLE_BAR_HEIGHT = 20;

    protected boolean on = true;
    protected int width, height;
    protected int sleepTime = SLEEP_TIME;
    protected Image buffer;

    public GraphicalSimulation(String name, int inWidth, int inHeight) {
        width = inWidth;
        height = inHeight;

        // Frame can be read as 'window' here.
        Frame frame = new Frame(name);
        frame.add(this);
        frame.setSize(width, height + TITLE_BAR_HEIGHT);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        buffer = createImage(width, height);
    }

    // 'paint' will be called every tenth of a second that the game is on.
    abstract public void paint(Graphics brush);

    // 'update' paints to a buffer then to the screen, then waits a tenth of
    // a second before repeating itself, assuming the game is on. This is done
    // to avoid a choppy painting experience if repainted in pieces.
    @Override
    public void update(Graphics brush) {
        paint(buffer.getGraphics());
        System.out.flush();
        brush.drawImage(buffer, 0, 0, this);

        if (on) {
            sleep(sleepTime);
            repaint();
        }
    }

    // 'sleep' is a simple helper function used in 'update'.
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception exc) {
            // do nothing, just continue
        }
    }
}