package GameLogic;

import java.awt.*;

public enum CardColors {
    RED (Color.RED),
    YELLOW (Color.YELLOW),
    BLUE(Color.BLUE),
    GREEN(Color.GREEN),
    WILD(Color.BLACK);

    Color color;

    private CardColors(Color color){
        this.color = color;
    }
}
