import javafx.scene.paint.Color;

public class CheckersPieceResults {
	
	private CheckersPieceMoves moveType;
	private CheckersPiece piece;
	
	public CheckersPieceMoves getType() {
		return moveType;
	}
	
	public CheckersPiece getPiece() {
		return piece;
	}
	
	public CheckersPieceResults(CheckersPieceMoves moveType) {
		this(moveType, null);
	}
	
	public CheckersPieceResults(CheckersPieceMoves moveType, CheckersPiece piece) {
		this.moveType = moveType;
		this.piece = piece;
	}
	
}