import java.awt.*;

public class Ball {
    int positionX, positionY, velocityX, velocityY;
    final int DIAMETER = 20;

    Ball(int positionX, int positionY, int velocityX, int velocityY){
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.drawOval(positionX, positionY, DIAMETER, DIAMETER);
        g.fillOval(positionX, positionY, DIAMETER, DIAMETER);
    }

    void move(double deltaTime){
        // Update position based on velocity and deltaTime (movement per frame)
        positionX += velocityX * deltaTime;
        positionY += velocityY * deltaTime;
    }

    void checkBorderCollisions(){
        // Check for screen borders
        // Left and right borders
        if (positionX <= 0 || positionX + DIAMETER >= GamePanel.SCREEN_WIDTH) {
            velocityX = -velocityX;
        }

        // Top and bottom borders
        if (positionY <= 0) {
            velocityY = -velocityY;
        } else if (positionY + DIAMETER >= GamePanel.SCREEN_HEIGHT) {
            velocityY = -velocityY;
        }
    }

    void checkPlayerCollision(Player player) {
        if ((positionX + DIAMETER > player.positionX && positionX < player.positionX + player.width) &&
                // Check for Y-axis collision
                (positionY + DIAMETER > player.positionY && positionY < player.positionY + player.height)) {
            velocityY = -velocityY;
        }
    }

    void checkBrickCollisions(Brick brick){
        if (brick.solid && !brick.wasHit) {
            // Check for X-axis collision
            if ((positionX + DIAMETER > brick.positionX && positionX < brick.positionX + brick.width) &&
                    // Check for Y-axis collision
                    (positionY + DIAMETER > brick.positionY && positionY < brick.positionY + brick.height)) {

                velocityY = -velocityY;

                brick.health -= 1;
                brick.wasHit = true;
            }
        }
    }
}