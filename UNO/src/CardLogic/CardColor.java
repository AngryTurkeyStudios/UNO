/**
 * An enumeration for the types of colors a card can be, which
 * will provide the color for the renderer to look up when drawing
 * a card.
 *
 * @author Natalie Schneider
 * @version Final Project
 * @bugs None
 */

package CardLogic;

import java.awt.*;

public enum CardColor {
    RED (Color.RED),
    YELLOW (Color.YELLOW),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    WILD(Color.BLACK);

    Color color;

    CardColor(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
