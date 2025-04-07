import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;

class DrawPanel extends JPanel implements MouseListener {

    private ArrayList<Card> hand;

    // Rectangle object represents a rectangle
    private Rectangle button;

    public DrawPanel() {
        button = new Rectangle(147, 250, 160, 26); //get new cards button
        this.addMouseListener(this);
        hand = Card.buildHand();
        System.out.println(hand);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 120;
        int y = 10;

        int cardNumber = 0;
        int temp = 0;
        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.get(cardNumber);
            if (c.getHighlight()) {
                // draw the border rectangle around the card
                g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
            }
            // establish the location of the rectangle "hitbox"
            c.setRectangleLocation(x, y);
            g.drawImage(c.getImage(), x, y, null);

            x = x + c.getImage().getWidth() + 10;
            if ((cardNumber + 1) % 3 == 0) {
                y = y + c.getImage().getHeight() + 10;
                x = 120;
            }
            cardNumber++;
        }

        // drawing the bottom button
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("GET NEW CARDS", 150, 270); // print method
        // border around text
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        // left click (1 --> left; 3 --> right)
        if (e.getButton() == 1) {
            // if clicked is inside the button rectangle
            if (button.contains(clicked)) { // rectangle method; takes in a point obj and tells u if the point is in the object (t/f)
                hand = Card.buildHand();
            }

            // go through each card
            // check if any of them were clicked in
            // if it was clicked, flip the card
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipCard();
                }
            }
        }

        // right click
        if (e.getButton() == 3) {
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipHighlight();
                }
            }
        }


    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}