/**
 * This program realize the breakout game
 */
package com.shpp.p2p.cs.yparfeniuk.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle and color
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;
    private static final double HALF_PADDLE_WIDTH = PADDLE_WIDTH / 2.0;
    private static final Color PADDLE_COLOR = Color.BLACK;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Color of a bricks row
     */
    private static final Color[] BRICKS_COLOR = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};

    /**
     * Radius, diameter, vectors and color of the ball
     */
    private static final int BALL_RADIUS = 10;
    private static final int BALL_DIAMETER = BALL_RADIUS * 2;
    private static final Color BALL_COLOR = Color.BLACK;
    private static final double BALL_VECTOR_X_MAX = 3.0;
    private static final double BALL_VECTOR_Y = 3.0;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    /**
     * Percent full and step speed for next level
     */
    private static final int PERCENT_FULL = 100;
    private static final int STEP_SPEED = 10;

    /**
     * Global variable for first speed ball, num attempt and num all bricks
     */
    private static int ballSpeed = 24;
    private static int numAttempt = 1;
    private static int numBrick = NBRICK_ROWS * NBRICKS_PER_ROW;

    /**
     * The main method
     */
    @Override
    public void run() {
        createBricksNet();
        createPaddle();
        createBall();
    }

    /**
     * Method for move paddle with location mouse, but not output from this window game,
     * Y axis stay same, change only X axis
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        double valuePaddleY = getHeight() - PADDLE_Y_OFFSET;    //parameter for Y axis. Please use HEIGHT instead getHeight() if in your system will be correct drawn (I use Linux)

        //change location paddle. Paddle do not out from window
        if ((mouseEvent.getX() > HALF_PADDLE_WIDTH) && (mouseEvent.getX() < WIDTH - HALF_PADDLE_WIDTH)) {
            paddle.setLocation(mouseEvent.getX() - HALF_PADDLE_WIDTH, valuePaddleY);
        } else if (mouseEvent.getX() <= PADDLE_WIDTH) {
            paddle.setLocation(0, valuePaddleY);
        } else {
            paddle.setLocation(WIDTH - PADDLE_WIDTH, valuePaddleY);
        }
    }

    /**
     * Create new global variable paddle
     */
    GRect paddle = null;

    /**
     * Create new paddle and add method addMouseListeners() for listening
     */
    private void createPaddle() {
        paddle = new GRect(WIDTH / 2.0 - HALF_PADDLE_WIDTH, getHeight() - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setColor(PADDLE_COLOR);
        add(paddle);

        addMouseListeners();    //listener for paddle location
    }

    /**
     * Create new global variable ball
     */
    GOval ball = null;

    /**
     * Create new ball and set parameters ball.
     * And add method startBallVector() for random vector,
     */
    private void createBall() {
        ball = new GOval(WIDTH / 2.0 - BALL_RADIUS, HEIGHT / 2.0 - BALL_RADIUS, BALL_DIAMETER, BALL_DIAMETER);
        ball.setFilled(true);
        ball.setColor(BALL_COLOR);
        add(ball);

        waitForClick(); //game will be begin only if player click in game window
        startBallVector();  //random vector for X axis
    }

    /**
     * This method set vector x with random parameter and vector y.
     * This vector for only ball.
     * And add method moveBall(vectorX, vectorY) for start move ball
     */
    private void startBallVector() {
        RandomGenerator randomGen = RandomGenerator.getInstance();
        double vectorX = randomGen.nextDouble(1.0, BALL_VECTOR_X_MAX);
        double vectorY = BALL_VECTOR_Y;

        if (randomGen.nextBoolean(0.5)) {   //random with 50% true and 50% false
            vectorX = -vectorX;
        }

        moveBall(vectorX, vectorY); //ball will be move with this vector parameters
    }

    /**
     * This recursions method start ball move with vector parameters and ball reflection in another method
     */
    private void moveBall(double vectorX, double vectorY) {
        ball.move(vectorX, vectorY);
        pause(ballSpeed);

        ballReflectionWall(vectorX, vectorY);   //reflection ball

        moveBall(vectorX, vectorY); //recursion for ball always move
    }

    /**
     * In this method ball reflection from wall and change attempt if is need.
     * Also check this attempt and start new game if all good. If not wall, reflection object
     */
    private void ballReflectionWall(double vectorX, double vectorY) {
        if (ball.getX() + BALL_DIAMETER >= WIDTH || ball.getX() + BALL_DIAMETER + vectorX > WIDTH
                || ball.getX() <= 0 || ball.getX() + vectorX < 0) {
            moveBall(-vectorX, vectorY);
        } else if (ball.getY() <= 0 || ball.getY() + vectorY < 0) {
            moveBall(vectorX, -vectorY);
        } else if (ball.getY() + BALL_DIAMETER >= getHeight() || ball.getY() + BALL_DIAMETER + vectorY > getHeight()) {
            pause(1000);    //(1 second) pause when ball in down wall

            numAttempt++;   //new attempt for next game
            remove(ball);   //remove ball
            if (!endGame()) {  //check this attempt and set game speed
                createBall();   //start new attempt
            }
        }
        //if it's not wall, it's some object
        ballReflectionObject(vectorX, vectorY);
    }

    /**
     * In this method ball reflection from object and change numBrick if object is brick.
     * Also check this attempt and set new speed for game
     */
    private void ballReflectionObject(double vectorX, double vectorY) {
        GObject collider = getCollidingObject();    //new object

        if (collider == paddle && (ball.getY() + BALL_DIAMETER == paddle.getY() //if collider is paddle, ball reflection
                || ball.getY() + BALL_DIAMETER - vectorY <= paddle.getY())) {
            moveBall(vectorX, -vectorY);
        } else if (collider != null && collider != paddle) {    //this is another object (not paddle)
            remove(collider);   //delete brick
            numBrick--; //minus one brick
            if (!endGame()) {   //check game (if not all bricks was removed)
                moveBall(vectorX, -vectorY);    //new vector for ball
            }
        }
    }

    /**
     * This method returns element, who ball colliding, or null if not
     */
    private GObject getCollidingObject() {
        if (getElementAt(ball.getX(), ball.getY()) != null) {
            return getElementAt(ball.getX(), ball.getY());
        } else if (getElementAt(ball.getX() + BALL_DIAMETER, ball.getY()) != null) {
            return getElementAt(ball.getX() + BALL_DIAMETER, ball.getY());
        } else if (getElementAt(ball.getX() + BALL_DIAMETER, ball.getY() + BALL_DIAMETER) != null) {
            return getElementAt(ball.getX() + BALL_DIAMETER, ball.getY() + BALL_DIAMETER);
        } else if (getElementAt(ball.getX(), ball.getY() + BALL_DIAMETER) != null) {
            return getElementAt(ball.getX(), ball.getY() + BALL_DIAMETER);
        }
        return null;
    }

    /**
     * Create new bricks net in window with set parameters
     */
    private void createBricksNet() {

        //base for X axis
        double valueX = getWidth() / 2.0 - (NBRICKS_PER_ROW * (BRICK_WIDTH + BRICK_SEP) - BRICK_SEP) / 2.0;

        for (int i = 0; i < NBRICK_ROWS; i++) { //draw every rows
            createBrickRow(i, valueX);
        }
    }

    /**
     * Create new bricks row in set location
     */
    private void createBrickRow(int numRow, double valueX) {
        double valueY = BRICK_Y_OFFSET + (BRICK_HEIGHT + BRICK_SEP) * numRow;   //base parameter Y axis for every row
        Color rowColor = BRICKS_COLOR[numRow / 2];  //base color for every 2 rows
        GRect brick;

        for (int i = 0; i < NBRICKS_PER_ROW; i++) { //draw new bricks row
            brick = new GRect(valueX + (BRICK_WIDTH + BRICK_SEP) * i, valueY, BRICK_WIDTH, BRICK_HEIGHT);
            brick.setFilled(true);
            brick.setColor(rowColor);
            add(brick);
        }
    }

    /**
     * This method checks if player win or lose and shows information label if game ended.
     * And calls method levelGame(); for change level game if game not ended
     */
    private boolean endGame() {
        if ((NTURNS < numAttempt) || (numBrick == 0)) { //true only if player lose or win

            //draw some information label
            GLabel label = (NTURNS < numAttempt) ? new GLabel("You lose") : new GLabel("You win");
            label.setColor((NTURNS < numAttempt) ? Color.RED : Color.GREEN);
            label.setFont("Serif-50");
            label.setLocation(getWidth() / 2.0 - label.getWidth() / 2.0, getHeight() / 2.0 - label.getHeight() / 2.0);
            add(label);

            //game end and this objects do not need
            remove(ball);
            remove(paddle);
            pause(5000);
            return true;
        } else {
            levelGame();
            return false;
        }
    }

    /**
     * This method change game speed (level)
     */
    private void levelGame() {
        if (((numBrick * PERCENT_FULL) / (NBRICKS_PER_ROW * NBRICK_ROWS)) % STEP_SPEED == 0) {
            ballSpeed = ballSpeed - ballSpeed / STEP_SPEED;
        }
    }
}
