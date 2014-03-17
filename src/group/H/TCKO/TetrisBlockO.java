package group.H.TCKO;

import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class TetrisBlockO extends TetrisBlock{
	/**
	 * value of the current rotation position {0,1,2 or 3}
	 */
	protected int rotationPos;

	/**
	 * blocks will have three TetrisBug objects in it... they will be added in the
	 * constructor
	 */
	protected ArrayList<TetrisBug> blocks;
	/**
	 * used as a convenient reference to the Grid
	 */
	protected Grid<Actor> gr;

	/**
	 * default constructor
	 */
	public TetrisBlockO() {
		super();
		rotationPos = super.rotationPos;
		blocks = super.blocks;
		gr = super.gr;
		
		if (gr.get(new Location(0, 5)) != null
				|| gr.get(new Location(0, 6)) != null || gr.get(new Location(1,6)) != null) {
			javax.swing.JOptionPane.showMessageDialog(null, "Score: "
					+ TetrisGame.score, "GAME OVER!", 0);
			System.exit(0);
		}
		putSelfInGrid(gr, new Location(1, 5));
		
		TetrisBug a;
		// create TetrisBugs for ArrayList blocks and put them in Grid gr
		a = new TetrisBug(Color.yellow);
		a.putSelfInGrid(gr, new Location(0, 5));
		blocks.add(a);
		a = new TetrisBug(Color.yellow);
		a.putSelfInGrid(gr, new Location(0, 6));
		blocks.add(a);
		a = new TetrisBug(Color.yellow);
		a.putSelfInGrid(gr, new Location(1, 6));
		blocks.add(a);

		
		// TetrisBlock subclasses will add two more TetrisBug objects to blocks
		
	}
	
	/**
	 * TetrisBlock and its TetrisBugs must face down (direction 180) If they can
	 * move down, they will. Otherwise, it will ask TetrisGame to create a new
	 * TetrisBlock since this one is stuck at the bottom.
	 */
	public void act() {
		setDirection(180);
		for (TetrisBug tb : blocks)
			tb.setDirection(180);
		if (canMoveDown())
			moveDown();
		else if (!TetrisGame.currentBlock.canMoveDown())
			TetrisGame.nextTetrisBlock();
	}
	
	public void dropDown() {
		while(TetrisGame.currentBlock.canMoveDown()) {
			act();
		}
	}
	
	/**
	 * Move the TetrisBlock and its TetrisBugs one cell. (they should already be
	 * facing down) Note: The order in which all the TetrisBugs move is important
	 * and depends on the current rotationPos.
	 */
	public void moveDown() {
		if (rotationPos == 0) {
			move();
			blocks.get(2).move();
			blocks.get(0).move();
			blocks.get(1).move();
		}
	}
	
	/**
	 * Returns true if the TetrisBlock and its TetrisBugs can move (they should
	 * already be facing down) Otherwise, returns false.
	 */
	public boolean canMoveDown() {
		if (rotationPos == 0)
			return canMove();
		else
			return true;
	}
	
	/**
	 * Sets the direction of the TetrisBlock and its TetrisBugs to 90 (right) If
	 * they can move, they will move one cell (to the right)
	 */
	public void moveRight() {
		setDirection(90);
		for (TetrisBug tb : blocks)
			tb.setDirection(90);
		if (rotationPos == 0) {
			if (blocks.get(2).canMove() && blocks.get(1).canMove()) {
				blocks.get(1).move();
				blocks.get(2).move();
				blocks.get(0).move();
				move();
			}
		}
	}
	
	/**
	 * Sets the direction of the TetrisBlock and its TetrisBugs to 90 (right) If
	 * they can move, they will move one cell (to the right)
	 */
	public void moveLeft() {
		setDirection(-90);
		for (TetrisBug tb : blocks)
			tb.setDirection(-90);
		if (blocks.get(0).canMove()){
			blocks.get(0).move();
			move();
			blocks.get(1).move();
			blocks.get(2).move();
		}
	}
	
	/**
	 * If the TetrisBlock and its TetrisBugs can rotate, then they will all move
	 * to their proper location for the given rotation designated by
	 * rotationPos... Update rotationPos.
	 */
	
	public void rotate() {

	}
}
