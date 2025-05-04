import java.awt.*;

public class Brick {
    int positionX, positionY, width, height;
    int health = 3;
    boolean solid = true;

    Brick(int positionX, int positionY, int width, int height){

        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;

    }

    public void draw(Graphics g){
        switch (health) {
            case 3:
                g.setColor(new Color(53, 87, 37));
                g.fillRect(positionX, positionY, width, height);
                g.setColor(new Color(115, 163, 93));
                g.drawRect(positionX, positionY, width, height);
                break;
            case 2:
                g.setColor(new Color(209, 209, 8));
                g.fillRect(positionX, positionY, width, height);
                g.setColor(Color.YELLOW);
                g.drawRect(positionX, positionY, width, height);
                break;
            case 1:
                g.setColor(new Color(130, 3, 3));
                g.fillRect(positionX, positionY, width, height);
                g.setColor(Color.RED);
                g.drawRect(positionX, positionY, width, height);
                break;
            case 0:
                g.setColor(new Color(0, 0, 0, 0));
                g.drawRect(positionX, positionY, width, height);
                solid = false;
                break;
        }
    }

}
