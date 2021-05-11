/*
 * This program draws one ball in center and another ball (moving) around him (in rect trajectories)
 * When user click on some ball, ball chang trajectory move
 * */
package com.shpp.p2p.cs.yparfeniuk.assignment8;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Assignment8Part1 extends WindowProgram {
    /**
     * Windows parameters
     */
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Ball count
     */
    public static final int BALL_COUNT = 25;

    public static final int MAX_BALL_IN_SIDE = (int) Math.sqrt(BALL_COUNT);

    public static final int MIN_BALL_COUNT = 9;

    /**
     * Ball radius
     */
    public static final double BALL_RADIUS = (APPLICATION_WIDTH / 2.0) / (MAX_BALL_IN_SIDE + 2);

    public static final double BALL_DISTANCE = (APPLICATION_WIDTH - MAX_BALL_IN_SIDE) / MAX_BALL_IN_SIDE;

    /**
     * Count rect sides (it important for trajectory)
     */
    public static final int NUM_SIDES = 4;

    /**
     * Size trajectory (it important for trajectory)
     */
    public static final double TRAJECTORY = APPLICATION_WIDTH / 8.0;

    ArrayList<Color> colors = new ArrayList<>(); //contains every color of balls

    ArrayList<ArrayList> ballsInTop = new ArrayList<>(); //every ball who must move
    ArrayList<ArrayList> ballsInBottom = new ArrayList<>(); //every ball who must move
    ArrayList<ArrayList> ballsInLeft = new ArrayList<>(); //every ball who must move
    ArrayList<ArrayList> ballsInRight = new ArrayList<>(); //every ball who must move

    ArrayList<ArrayList> allBalls = new ArrayList<>(); //every ball who must move

    boolean leftMove = true; // turns trajectory

    @Override
    public void run() {
        if (BALL_COUNT >= MIN_BALL_COUNT && MAX_BALL_IN_SIDE % 2 == 1) {
            createBalls();
            /*while (true) {
                moveBall();
            }*/
        } else {
            drawWarningLabel();
        }
    }

    private void createBalls() {
        int numBallsInSide = MAX_BALL_IN_SIDE;
        int minBallInSide = (int) Math.sqrt(MIN_BALL_COUNT);

        while (numBallsInSide >= minBallInSide) {
            for (int i = 0; i < NUM_SIDES; i++) {
                ballForMove(i, numBallsInSide);
            }
            numBallsInSide -= 2;
        }
        drawBall(APPLICATION_WIDTH / 2.0 - BALL_RADIUS, APPLICATION_HEIGHT / 2.0 - BALL_RADIUS);
        addMouseListeners();
    }

    /**
     * This method changes trajectory all ball if click on some ball
     *
     * @param mouseEvent some users click
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        super.mouseClicked(mouseEvent);
        if (getElementAt(mouseEvent.getX(), mouseEvent.getY()) != null) {
            leftMove = !leftMove;
        }
    }

    /**
     * This method moves all ball (without one ball in center)
     */
    /*private void moveBall() {
        for (GOval ball : allBalls) {
            if (leftMove) {
                trajectories(ball);
            } else {
                trajectoriesChange(ball);
            }
        }
    }*/

    /**
     * This method move balls in trajectories (after click at any element, move to another way)
     *
     * @param ball one ball (who must move)
     */
    /*private void trajectoriesChange(GOval ball) {
        double bottomY = APPLICATION_HEIGHT - TRAJECTORY - BALL_RADIUS;
        double rightX = APPLICATION_WIDTH - TRAJECTORY - BALL_RADIUS;

        if (ball.getX() < rightX && ball.getY() <= TRAJECTORY - BALL_RADIUS) {
            ball.move(1, 0);

        } else if (ball.getX() <= TRAJECTORY - BALL_RADIUS && ball.getY() > TRAJECTORY - BALL_RADIUS) {
            ball.move(0, -1);

        } else if (ball.getX() > TRAJECTORY - BALL_RADIUS && ball.getY() >= bottomY) {
            ball.move(-1, 0);

        } else if (ball.getX() >= rightX && ball.getY() < bottomY) {
            ball.move(0, 1);
        }
        pause(1);
    }*/

    /**
     * This method move balls in trajectories (in start to left)
     *
     * @param ball one ball (who must move)
     */
    /*private void trajectories(GOval ball) {
        double bottomY = APPLICATION_HEIGHT - TRAJECTORY - BALL_RADIUS;
        double rightX = APPLICATION_WIDTH - TRAJECTORY - BALL_RADIUS;

        if (ball.getX() > TRAJECTORY - BALL_RADIUS && ball.getY() <= TRAJECTORY - BALL_RADIUS) {
            ball.move(-1, 0);

        } else if (ball.getX() <= TRAJECTORY - BALL_RADIUS && ball.getY() < bottomY) {
            ball.move(0, 1);

        } else if (ball.getX() < rightX && ball.getY() >= bottomY) {
            ball.move(1, 0);

        } else if (ball.getX() >= rightX && ball.getY() > TRAJECTORY - BALL_RADIUS) {
            ball.move(0, -1);
        }
        pause(1);
    }*/

    /**
     * This method draws all ball (without drained center ball)
     *
     * @param numBall step in position for every ball
     */
    private void ballForMove(int numBall, int numBallInSide) {
        double stepPosition = (TRAJECTORY * NUM_SIDES) / BALL_COUNT;
        double positionBall = TRAJECTORY + stepPosition * numBall;
        double valueX = positionBall, valueY = TRAJECTORY - BALL_RADIUS;

        if (positionBall > TRAJECTORY && positionBall <= TRAJECTORY * (NUM_SIDES / 2.0)) {
            valueX = APPLICATION_WIDTH - TRAJECTORY - BALL_RADIUS;
            valueY = positionBall - TRAJECTORY;
        } else if (positionBall > TRAJECTORY * (NUM_SIDES / 2.0) && positionBall <= TRAJECTORY * (NUM_SIDES - 1)) {
            valueX = positionBall - TRAJECTORY * (NUM_SIDES / 2.0);
            valueY = APPLICATION_HEIGHT - TRAJECTORY - BALL_RADIUS;
        } else if (positionBall > TRAJECTORY * (NUM_SIDES - 1) && positionBall < TRAJECTORY * NUM_SIDES) {
            valueX = TRAJECTORY - BALL_RADIUS;
            valueY = positionBall - TRAJECTORY * (NUM_SIDES - 1);
        }

        //allBalls.add(drawBall(valueX, valueY));
    }

    /**
     * This method draw one ball with random color and add this ball
     *
     * @param valueX coordinate ball in x axis
     * @param valueY coordinate ball in y axis
     * @return ball
     */
    private GOval drawBall(double valueX, double valueY) {
        GOval ball = new GOval(valueX, valueY, BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        ball.setColor(randomColor());
        add(ball);

        return ball;
    }

    /**
     * Create new color for ball
     *
     * @return new individual color
     */
    private Color randomColor() {
        Color newColor;
        RandomGenerator randomGenerator = new RandomGenerator();

        do {
            newColor = randomGenerator.nextColor();
        } while (colors.contains(newColor));

        colors.add(newColor);

        return newColor;
    }

    private void drawWarningLabel() {
        GLabel warningCountBall = new GLabel("Please, change constant BALL_COUNT!!!");
        warningCountBall.setFont("Serif-20");
        warningCountBall.setLocation((APPLICATION_WIDTH - warningCountBall.getWidth()) / 2.0,
                (APPLICATION_HEIGHT - warningCountBall.getHeight()) / 2.0);
        add(warningCountBall);

        GLabel explainCountBall = new GLabel(
                "BALL_COUNT must be degree of odd number (starting with 3 in 2 == 9)");
        explainCountBall.setFont("Serif-13");
        explainCountBall.setLocation((APPLICATION_WIDTH - explainCountBall.getWidth()) / 2.0,
                warningCountBall.getY() + explainCountBall.getHeight() * 2);
        add(explainCountBall);
    }
}
