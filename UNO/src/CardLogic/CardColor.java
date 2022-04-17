/**
 * An enumeration for the types of colors a card can be, returing
 * the color when asked, and null for the color of a wild card
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
    WILD(null);

    Color color;

    CardColor(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
