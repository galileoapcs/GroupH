package group.H.TCKO;

//TetrisGame.java
import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;
import java.awt.Color;
/**
 * This is a clone of the classic Tetris game.
 * The GUI is the standard for GridWorld.
 * It will compile and execute after class TetrisBug is written
 * ...see Question 0
 */
public class TetrisGame {

	/**
	 * World variable used for GUI...
	 * Initialized to 19x17 Grid for aesthetic viewing.
	 * ..leaves some columns at the right for extra game elements
	 * like 'upcoming piece' and 'score' information
	 */
	public static ActorWorld world = new ActorWorld(new BoundedGrid(19, 17));

	/**
	 * A reference to the currently controlled TetrisBlock in the game.
	 * Its direction and rotation is controlled by the keyboard arrow keys.
	 */
	public static TetrisBlock currentBlock;
	public static TetrisBlock nextBlock;

	/**
	 * The number of lines cleared during a the game
	 */
	public static int score;

	/**
	 * Handles game simulation and KeyEvent handling
	 * NOTE: YOU DO NOT NEED TO WRITE ANY CODE IN THE main
	 */
	public static void main(String[] args) {

		//set up world
		for (int i = 0; i < 19; i++) {
			world.add(new Location(i,11),new Rock());
			world.add(new Location(i,0),new Rock());
		}
		setNextBlock();
		nextTetrisBlock();
		//needed code for keyboard event handling
		java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager()
		.addKeyEventDispatcher(new java.awt.KeyEventDispatcher() {
			public boolean dispatchKeyEvent(java.awt.event.KeyEvent event) {
				String key = javax.swing.KeyStroke.getKeyStrokeForEvent(event).toString();
				
				if (key.equals("pressed UP"))
					currentBlock.rotate();
				if (key.equals("pressed RIGHT"))
					currentBlock.moveRight();
				if (key.equals("pressed DOWN"))
					currentBlock.act();
				if (key.equals("pressed LEFT"))
					currentBlock.moveLeft();
				if (key.equals("pressed SPACE"))
					currentBlock.dropDown();
				world.show();
				return true;
			}
		});
		world.show();

	}

	/**
	 * Calls removeCompleteRows and chooses a new TetrisBlock at random
	 */
	public static void nextTetrisBlock() {

		removeCompleteRows();
		currentBlock = nextBlock;
		currentBlock.moveEntireBlock(new Location(8,14));
		setNextBlock();
		currentBlock.moveEntireBlock(new Location(1,5));
		currentBlock.setCurrent(true);
		
	}
	
	public static void setNextBlock() {
		TetrisBlock randomBlock = null;//default 2block piece
		//choose random block
		int randNum = (int)(Math.random()*7)+1;//random number between 1 and 7
		if(randNum == 1)
			randomBlock = new TetrisBlockO();
		if(randNum == 2)
			randomBlock = new TetrisBlockI();
		if(randNum == 3)
			randomBlock = new TetrisBlockT();
		if(randNum == 4)
			randomBlock = new TetrisBlockL();
		if(randNum == 5)
			randomBlock = new TetrisBlock_L();
		if(randNum == 6)
			randomBlock = new TetrisBlockZ();
		if(randNum == 7)
			randomBlock = new TetrisBlock_Z();

		nextBlock = randomBlock;
		nextBlock.moveEntireBlock(new Location(3,14));
	}
	
	/**
	 * checks each row 1 through 18 (skip row 0) for full rows
	 * if a row is full, then remove the actor from each cell in that row
	 * and ask each actor located above the just deleted row to act and
	 * update the score++
	 */
	
	private static boolean isFullRow(int row) {
		Grid<Actor> gr = world.getGrid();
		ArrayList<Location> list = gr.getOccupiedLocations();
		int count = 0;
		for (int col=1; col<11; col++) {
			if (list.contains(new Location(row,col))) {
				count++;
			}
		}
		if (count == 10) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void pushRowsDown(int row) {
		Grid<Actor> gr = world.getGrid();
		ArrayList<Location> actors = gr.getOccupiedLocations();
		for(Location loc:actors) {
			if (loc.getCol() > 0 && loc.getCol() < 11 && loc.getRow() < row) {
				gr.get(loc).moveTo(new Location(loc.getRow()+1, loc.getCol()));
			}
		}
	}
	
	public static void removeCompleteRows() {
		Grid<Actor> gr = world.getGrid();
		
		for (int row=gr.getNumRows(); row>1; row--) {
			if (isFullRow(row)) {
				for(int col=1; col<11; col++) {
					gr.remove(new Location(row,col));
				}
				pushRowsDown(row);
				score++;
			}
		}
	}
	


}