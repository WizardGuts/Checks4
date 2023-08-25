import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;

public class CheckersPiece extends StackPane {
	public Color mainTokenColor1 = Color.valueOf("#171717");
	public Color mainTokenColor2 = Color.valueOf("#c6c6c6");
	public Color sideTokenColor1 = Color.valueOf("#343434");
	public Color sideTokenColor2 = Color.valueOf("#FFFFFF");
	
	public Color mainKingTokenColor1 = Color.valueOf("#171717");
	public Color mainKingTokenColor2 = Color.valueOf("#c6c6c6");
	public Color sideKingTokenColor1 = Color.valueOf("#DBCA72");
	
	public CheckersPieceType pieceType;
	private double mouseX, mouseY;
	private double oldX, oldY;
	
	public CheckersPieceType getType() {
		return pieceType;
	}
	
	public double getOldX() {
		return oldX;
	}
	
	public double getOldY() {
		return oldY;
	}
	
	public void setType(CheckersPieceType pieceType) {
		this.pieceType = pieceType;
	}
	
	public CheckersPiece(CheckersPieceType pieceType, int x, int y) {
		this.pieceType = pieceType;
		
		move(x,y);
		
		relocate(x * CheckersMain.tileSize, y * CheckersMain.tileSize);
		Ellipse token = new Ellipse(CheckersMain.tileSize * 0.36, CheckersMain.tileSize * 0.34);
		token.setFill(pieceType == CheckersPieceType.PLAYER1 ? mainTokenColor1 : mainTokenColor2);
		token.setStroke(Color.BLACK);
		token.setStrokeWidth(CheckersMain.tileSize * 0.05);
		
		token.setTranslateX((CheckersMain.tileSize - CheckersMain.tileSize * 0.2 * 2) / 2) ;
		token.setTranslateY((CheckersMain.tileSize - CheckersMain.tileSize * 0.2 * 2) / 2);
		
		Ellipse tokenBackground = new Ellipse(CheckersMain.tileSize * 0.38, CheckersMain.tileSize * 0.36);
		tokenBackground.setFill(pieceType == CheckersPieceType.PLAYER1 ? sideTokenColor1 : sideTokenColor2);
		tokenBackground.setStroke(Color.BLACK);
		tokenBackground.setStrokeWidth(CheckersMain.tileSize * 0.05);
		
		token.setTranslateX((CheckersMain.tileSize - CheckersMain.tileSize * 0.45 * 2) / 2) ;
		token.setTranslateY((CheckersMain.tileSize - CheckersMain.tileSize * 0.45 * 2) / 2 + CheckersMain.tileSize * 0.1);
				
		Ellipse kingTokenBackground = new Ellipse(CheckersMain.tileSize * 0.38, CheckersMain.tileSize * 0.36);
		kingTokenBackground.setFill(pieceType == CheckersPieceType.PLAYER2KING ? sideKingTokenColor1 : sideTokenColor2);
		kingTokenBackground.setStroke(Color.BLACK);
		kingTokenBackground.setStrokeWidth(CheckersMain.tileSize * 0.05);
		
		token.setTranslateX((CheckersMain.tileSize - CheckersMain.tileSize * 0.45 * 2) / 2) ;
		token.setTranslateY((CheckersMain.tileSize - CheckersMain.tileSize * 0.45 * 2) / 2 + CheckersMain.tileSize * 0.1);
		
		getChildren().addAll(token, tokenBackground);
		
		setOnMousePressed(e -> {
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
		});
		
		setOnMouseDragged(e -> {
			relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
		});
	}
	
	public void move(int x, int y) {
		oldX = x * CheckersMain.tileSize;
		oldY = y * CheckersMain.tileSize;
		relocate(oldX, oldY);
	}
	
	public void cancelMove() {
		relocate(oldX, oldY);
	}
}