import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class CheckersTiles extends Rectangle {
	public Color color1 = Color.valueOf("#000000");
	public Color color2 = Color.valueOf("#FFFFFF");;
	private CheckersPiece piece;
	
	public boolean hasPiece() {
		return piece != null;
	}
	
	public CheckersPiece getPiece() {
		return piece;
	}
	
	public void setPiece(CheckersPiece piece) {
		this.piece = piece;
	}
	
	public void setColor1(Color color){
		this.color1 = color;
	}	
	
	public void setColor2(Color color){
		this.color2 = color;
	}
	
	public CheckersTiles() {
		
	}
	public CheckersTiles(boolean tiles, int x, int y, Color color1, Color color2) {
		setWidth(CheckersMain.tileSize);
		setHeight(CheckersMain.tileSize);
			
		relocate(x * CheckersMain.tileSize, y * CheckersMain.tileSize);
		setFill(tiles ? color1 : color2);
	}
}