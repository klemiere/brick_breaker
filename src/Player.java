import java.awt.*;

public class Player {
    int positionX, positionY, width, height;

    Player(int positionX, int positionY, int width, int height){
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    void drawPlayer(Graphics g){
        g.setColor(Color.WHITE);
        g.drawRect(positionX, positionY, width, height);
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
    }
}
