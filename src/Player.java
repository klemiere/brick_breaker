import java.awt.*;

public class Player {
    int positionX, positionY, width, height;
    final float OFFSET = (float)width / 2;

    Player(int positionX, int positionY, int width, int height){
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.drawRect(positionX, positionY, width, height);
    }

    void checkCollisions() {
        // Check for screen borders
        // Left and right borders
        if (positionX <= 0) {
            positionX = (int) OFFSET;
        } else if (positionX + width >= GamePanel.SCREEN_WIDTH) {
            positionX = GamePanel.SCREEN_WIDTH - width - 1; //magic number to keep the player rectangle right border on screen

        }
    }

    void move(char direction){
        switch(direction){
            case 'L':
                positionX -= 5;
                break;
            case 'R':
                positionX += 5;
                break;
            case 'N':
                break;
        }
        checkCollisions();
    }
}
