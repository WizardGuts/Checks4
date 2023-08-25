import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public enum CheckersPieceType {
	PLAYER1(1, 0), PLAYER2(-1, 0), PLAYER1KING(1, -1), PLAYER2KING(-1, 1);
	
	final int direction;
	final int kingDirection;
	
	CheckersPieceType(int direction, int kingDirection) {
		this.direction = direction;
		this.kingDirection = kingDirection;
	}
}