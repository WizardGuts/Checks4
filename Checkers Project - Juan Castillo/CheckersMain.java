import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.paint.Color;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

public class CheckersMain extends Application {
	
	public static final int tileSize = 50;
	public static final int boardWidth = 8;
	public static final int boardHeight = 8;
	
	private CheckersTiles[][] checkersBoard = new CheckersTiles[boardHeight][boardWidth];
	private Group tileGroup = new Group();
	private Group pieceGroup = new Group();
	
//	Scene scene = new Scene(checkersPane());
	
	private Parent checkersPane() {
		Pane pane = new Pane();
		pane.setPrefSize(boardWidth * tileSize, boardHeight * tileSize);
		pane.getChildren().addAll(tileGroup, pieceGroup);
		Color color1 = new Color(1,1,1,1);
		Color color2 = new Color(0,0,0,1);
		
		Color tokenMain1 = Color.web("#FFFFFF");
		Color tokenMain2 = Color.web("#343434");
		Color tokenSide1 = Color.web("#171717");
		Color tokenSide2 = Color.web("#c6c6c6");
		
		for (int y = 0; y < boardHeight; y++) {
			for (int x = 0; x < boardWidth; x++) {
				CheckersTiles tile = new CheckersTiles((x + y) % 2 == 0, x, y, color1, color2);
				checkersBoard[x][y] = tile;
				tileGroup.getChildren().add(tile);
				
				CheckersPiece piece = null;
				if (y <= 2 && (x + y) % 2 != 0) {
					piece = createPiece(CheckersPieceType.PLAYER1, x, y, tokenMain1, tokenSide1);
//					piece = createPiece(CheckersPieceType.PLAYER1KING, x, y, tokenMain1, tokenSide1);
				}
				
				if (y >= 5 && (x + y) % 2 != 0) {
					piece = createPiece(CheckersPieceType.PLAYER2, x, y, tokenMain2, tokenSide2);
//					piece = createPiece(CheckersPieceType.PLAYER2KING, x, y, tokenMain1, tokenSide1);
				}
				
				if (piece != null) {
				tile.setPiece(piece);
				pieceGroup.getChildren().add(piece);
				}
			}
		}
		
		BorderPane borderPane = new BorderPane();
		MenuBar menuBar = new MenuBar();
		
		Menu boardMenu = new Menu("Boards");
		Menu operationsMenu = new Menu("Operations");
			menuBar.getMenus().addAll(boardMenu, operationsMenu);
		
		Menu monoSubMenu = new Menu("Monochrome Colors");
		Menu officialSubMenu = new Menu("Official Colors");
		Menu consumerSubMenu = new Menu("Consumer Colors");
		Menu internationalSubMenu = new Menu("International Colors");
			boardMenu.getItems().add(monoSubMenu);
			boardMenu.getItems().add(officialSubMenu);
			boardMenu.getItems().add(consumerSubMenu); 
			boardMenu.getItems().add(internationalSubMenu);
			
		Menu exitSubMenu = new Menu("Exit");
//		Menu restartSubMenu = new Menu("Restart");
			operationsMenu.getItems().add(exitSubMenu);
//			operationsMenu.getItems().add(restartSubMenu);
		
		monoSubMenu.setOnAction(e -> {
			tileGroup.getChildren().removeAll();
			for (int y = 0; y < boardHeight; y++) {
				for (int x = 0; x < boardWidth; x++) {
					CheckersTiles tile = new CheckersTiles((x + y) % 2 == 0, x, y, Color.valueOf("#FFFFFF"), Color.valueOf("#000000"));
					checkersBoard[x][y] = tile;
					tileGroup.getChildren().add(tile);
				}
			}
		});
		
		officialSubMenu.setOnAction(e -> {
			tileGroup.getChildren().removeAll();
			for (int y = 0; y < boardHeight; y++) {
				for (int x = 0; x < boardWidth; x++) {
					CheckersTiles tile = new CheckersTiles((x + y) % 2 == 0, x, y, Color.valueOf("#d8c8bf"), Color.valueOf("#3f644b"));
					checkersBoard[x][y] = tile;
					tileGroup.getChildren().add(tile);
				}
			}
		});
		
		consumerSubMenu.setOnAction(e -> {
			tileGroup.getChildren().removeAll();
			for (int y = 0; y < boardHeight; y++) {
				for (int x = 0; x < boardWidth; x++) {
					CheckersTiles tile = new CheckersTiles((x + y) % 2 == 0, x, y, Color.valueOf("#e6251e"), Color.valueOf("#000000"));
					checkersBoard[x][y] = tile;
					tileGroup.getChildren().add(tile);
				}
			}
		});
		
		internationalSubMenu.setOnAction(e -> {
			tileGroup.getChildren().removeAll();
			for (int y = 0; y < boardHeight; y++) {
				for (int x = 0; x < boardWidth; x++) {
					CheckersTiles tile = new CheckersTiles((x + y) % 2 == 0, x, y, Color.valueOf("#d9cca4"), Color.valueOf("#4e3528"));
					checkersBoard[x][y] = tile;
					tileGroup.getChildren().add(tile);
				}
			}
		});
		
		exitSubMenu.setOnAction(e -> {
			System.exit(0);
		});
		
//		restartSubMenu.setOnAction(e -> {
//			tileGroup.getChildren().removeAll();
//			pieceGroup.getChildren().removeAll();
//			for (int y = 0; y < boardHeight; y++) {
//				for (int x = 0; x < boardWidth; x++) {
//					CheckersTiles tile = new CheckersTiles((x + y) % 2 == 0, x, y, color1, color2);
//					checkersBoard[x][y] = tile;
//					tileGroup.getChildren().add(tile);
//					
//					CheckersPiece piece = null;
//					
//					if (y <= 2 && (x + y) % 2 != 0) {
//						piece = createPiece(CheckersPieceType.PLAYER1, x, y, tokenMain1, tokenSide1);
//					}
//					
//					if (y >= 5 && (x + y) % 2 != 0) {
//						piece = createPiece(CheckersPieceType.PLAYER2, x, y, tokenMain2, tokenSide2);
//					}
//					
//					if (piece != null) {
//					tile.setPiece(piece);
//					pieceGroup.getChildren().add(piece);
//					}
//				}
//			}
//		});
		
		borderPane.setTop(menuBar);
		borderPane.setCenter(pane);
		
		return borderPane;
	}
	
	private CheckersPiece createPiece(CheckersPieceType pieceType, int x, int y, Color tokenMain, Color tokenSide) {
		CheckersPiece piece = new CheckersPiece(pieceType, x, y);
		
		Color kingTokenMain1 = Color.web("#FFFFFF");
		Color kingTokenMain2 = Color.web("#343434");
		Color kingTokenSide = Color.web("#DBCA72");
		
		piece.setOnMouseReleased(e -> {
			int newX = onBoard(piece.getLayoutX());
			int newY = onBoard(piece.getLayoutY());
			
			CheckersPieceResults results;
			
			if (newX < 0 || newY < 0 || newX >= boardWidth || newY >= boardHeight) {
				results = new CheckersPieceResults(CheckersPieceMoves.NONE);
			} else {
				results = tryMove(piece, newX, newY);
			}
			
			int x0 = onBoard(piece.getOldX());
			int y0 = onBoard(piece.getOldY());
			switch (results.getType()) {
				case NONE:
				piece.cancelMove();
				break;
				
				case MOVE:
				piece.move(newX, newY);
				checkersBoard[x0][y0].setPiece(null);
				checkersBoard[newX][newY].setPiece(piece);
				if (piece.getType() == CheckersPieceType.PLAYER1KING) {
					piece.move(newX, newY);
					checkersBoard[x0][y0].setPiece(null);
					checkersBoard[newX][newY].setPiece(piece);
				}
				
				if (piece.getType() == CheckersPieceType.PLAYER2KING) {
					piece.move(newX, newY);
					checkersBoard[x0][y0].setPiece(null);
					checkersBoard[newX][newY].setPiece(piece);
				}
				break;
				
				case KING:
				piece.move(newX, newY);
				checkersBoard[x0][y0].setPiece(null);
				checkersBoard[newX][newY].setPiece(piece);
				
				if (piece.getType() == CheckersPieceType.PLAYER1) {
					piece.setType(CheckersPieceType.PLAYER1KING);
					//piece = createPiece(CheckersPieceType.PLAYER1KING, x, y, kingTokenMain1, kingTokenSide);

				}
				
				if (piece.getType() == CheckersPieceType.PLAYER2) {
					piece.setType(CheckersPieceType.PLAYER2KING);
					//piece = createPiece(CheckersPieceType.PLAYER2KING, x, y, kingTokenMain2, kingTokenSide);
				}
				break;
				
				case KILL:
				piece.move(newX, newY);
				checkersBoard[x0][y0].setPiece(null);
				checkersBoard[newX][newY].setPiece(piece);
				
				CheckersPiece opponentPiece = results.getPiece();
				checkersBoard[onBoard(opponentPiece.getOldX())][onBoard(opponentPiece.getOldY())].setPiece(null);
				pieceGroup.getChildren().remove(opponentPiece);
				break;
			}
		});
		
		return piece;
	}
	
	private CheckersPieceResults tryMove(CheckersPiece piece, int newX, int newY) {
		if (checkersBoard[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
			return new CheckersPieceResults(CheckersPieceMoves.NONE);
		}
		
		int x0 = onBoard(piece.getOldX());
		int y0 = onBoard(piece.getOldY());
		if (newY == 7 && piece.getType().direction == 1) {
			return new CheckersPieceResults(CheckersPieceMoves.KING);
		}
		if (newY == 0 && piece.getType().direction == -1) {
			return new CheckersPieceResults(CheckersPieceMoves.KING);
		}
		if (Math.abs(newX - x0) == 1 && (piece.getType() == CheckersPieceType.PLAYER1KING) || (piece.getType() == CheckersPieceType.PLAYER2KING)) {
			return new CheckersPieceResults(CheckersPieceMoves.MOVE);
		} else if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().direction) {
			return new CheckersPieceResults(CheckersPieceMoves.MOVE);
		} else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().direction * 2) {
			int x1 = x0 + (newX - x0) / 2;
			int y1 = y0 + (newY - y0) / 2;
			
			if (checkersBoard[x1][y1].hasPiece() && checkersBoard[x1][y1].getPiece().getType() != piece.getType()) {
				return new CheckersPieceResults(CheckersPieceMoves.KILL, checkersBoard[x1][y1].getPiece());
			}
		}
		return new CheckersPieceResults(CheckersPieceMoves.NONE);
	}
	
	private int onBoard(double pixel) {
		return (int)(pixel + tileSize / 2) / tileSize;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
	Scene scene = new Scene(checkersPane());
	primaryStage.setTitle("Checkers Project - Juan Castillo Pineda"); // Set the stage title
	primaryStage.setScene(scene); // Place the scene in the stage
	primaryStage.show(); // Display the stage
	}
}