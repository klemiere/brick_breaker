import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int DELAY = 1000 / 144;

    static final int BRICK_WIDTH = 60;
    static final int BRICK_HEIGHT = 20;
    int rows = 5;
    int columns = 10;

    boolean running = false;
    Timer timer;

    Player player;
    boolean leftPressed = false;
    boolean rightPressed = false;

    Ball ball;
    long lastTime = System.nanoTime();


    List<Brick> bricks = new ArrayList<>();

    GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame(){
        populateBricks();
        player = new Player(250, 550, 120, 20);
        ball = new Ball(310, 520, 200, 200);
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void populateBricks(){
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int x = col * (BRICK_WIDTH);
                int y = row * (BRICK_HEIGHT);
                bricks.add(new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT));
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        if (running){
            for (Brick brick : bricks) brick.draw(g);
            player.draw(g);
            ball.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (running){
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - lastTime) / 1_000_000_000.0; // seconds
            lastTime = currentTime;

            if (leftPressed && !rightPressed) player.move('L');
            else if (rightPressed && !leftPressed) player.move('R');
            ball.move(deltaTime);
            ball.checkBorderCollisions();
            ball.checkPlayerCollision(player);
            for(Brick brick : bricks) ball.checkBrickCollisions(brick);
            for(Brick brick : bricks) brick.resetHit();
            repaint();
        }
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: leftPressed = true; break;
                case KeyEvent.VK_RIGHT: rightPressed = true; break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: leftPressed = false; break;
                case KeyEvent.VK_RIGHT: rightPressed = false; break;
            }
        }
    }
}
