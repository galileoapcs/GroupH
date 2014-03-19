package group.H.TCKO;

//TetrisBlock.java
import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.util.ArrayList;
import java.awt.Color;

/**
 * TetrisBlock is a type of Bug. It will act in GridWorld by moving down
 * (direction 180) if it can, otherwise it will ask TetrisGame to make a new
 * TetrisBlock for the game.
 */
public class TetrisBlock_L extends TetrisBlock {

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
	
	protected boolean current;

	/**
	 * default constructor
	 */
	public TetrisBlock_L() {
		super();
		rotationPos = super.rotationPos;
		blocks = super.blocks;
		gr = super.gr;
		current = super.current;
		
		if (gr.get(new Location(2, 5)) != null
				|| gr.get(new Location(2, 4)) != null) {
			javax.swing.JOptionPane.showMessageDialog(null, "Score: "
					+ TetrisGame.score, "GAME OVER!", 0);
			System.exit(0);
		}
		TetrisBug b;
		TetrisBug c;
		// create TetrisBugs for ArrayList blocks and put them in Grid gr
		b = new TetrisBug(Color.blue);
		b.putSelfInGrid(gr, new Location(2, 5));
		blocks.add(b);
		c = new TetrisBug(Color.blue);
		c.putSelfInGrid(gr, new Location(2, 4));
		blocks.add(c);
		

		// TetrisBlock subclasses will add two more TetrisBug objects to blocks

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
			blocks.get(2).move();
			blocks.get(1).move();
			move();
			blocks.get(0).move();
		} else if (rotationPos == 1) {
			blocks.get(2).move();
			move();
			blocks.get(0).move();
			blocks.get(1).move();
		} else if (rotationPos == 2) {
			blocks.get(0).move();
			move();
			blocks.get(2).move();
			blocks.get(1).move();
			
		} else if (rotationPos == 3) {
			blocks.get(1).move();
			blocks.get(2).move();
			move();
			blocks.get(0).move();
		}
	}

	/**
	 * Returns true if the TetrisBlock and its TetrisBugs can move (they should
	 * already be facing down) Otherwise, returns false.
	 */
	public boolean canMoveDown() {
		if (rotationPos == 0)
			return blocks.get(2).canMove() && blocks.get(1).canMove();
		else if (rotationPos == 1)
			return blocks.get(0).canMove() && blocks.get(2).canMove() && canMove();
		else if (rotationPos == 2)
			return blocks.get(2).canMove() && blocks.get(0).canMove();
		else if (rotationPos == 3)
			return blocks.get(1).canMove() && blocks.get(0).canMove() && canMove();
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
			if (blocks.get(1).canMove() && canMove() && blocks.get(0).canMove()) {
				move();
				blocks.get(1).move();
				blocks.get(0).move();
				blocks.get(2).move();
			}
		} else if (rotationPos == 1) {
			if (blocks.get(2).canMove() && blocks.get(1).canMove()) {
				blocks.get(2).move();
				blocks.get(1).move();
				move();
				blocks.get(0).move();
			}
		} else if (rotationPos == 2) {
			if (blocks.get(2).canMove() && canMove() && blocks.get(0).canMove()) {
				move();
				blocks.get(0).move();
				blocks.get(2).move();
				blocks.get(1).move();
			}
		} else if (rotationPos == 3) {
			if (blocks.get(2).canMove() && blocks.get(0).canMove()) {
				blocks.get(0).move();
				blocks.get(2).move();
				move();
				blocks.get(1).move();
				
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
		if (rotationPos == 0) {
			if (blocks.get(2).canMove() && canMove() && blocks.get(0).canMove()) {
				move();
				blocks.get(2).move();
				blocks.get(0).move();
				blocks.get(1).move();
			}
		} else if (rotationPos == 1) {
			if (blocks.get(2).canMove() && blocks.get(0).canMove()) {
				blocks.get(2).move();
				blocks.get(0).move();
				move();
				blocks.get(1).move();
			}
		} else if (rotationPos == 2) {
			if (blocks.get(1).canMove() && canMove() && blocks.get(0).canMove()) {
				move();
				blocks.get(0).move();
				blocks.get(1).move();
				blocks.get(2).move();
			}
		} else if (rotationPos == 3) {
			if (blocks.get(2).canMove() && blocks.get(1).canMove()) {
				blocks.get(1).move();
				blocks.get(2).move();
				move();
				blocks.get(0).move();
				
				
			}
		}

	}

	/**
	 * If the TetrisBlock and its TetrisBugs can rotate, then they will all move
	 * to their proper location for the given rotation designated by
	 * rotationPos... Update rotationPos.
	 */
	public void rotate() {
		Location newLoc;
		Location newLoc1;
		Location newLoc2;
		if (rotationPos == 0) {
			newLoc = new Location(blocks.get(0).getLocation().getRow() + 1,
					blocks.get(0).getLocation().getCol() - 1);
			newLoc1 = new Location(blocks.get(1).getLocation().getRow() - 1,
					blocks.get(1).getLocation().getCol() + 1);
			newLoc2 = new Location(blocks.get(2).getLocation().getRow(),
					blocks.get(2).getLocation().getCol() + 2);
			if (gr.isValid(newLoc1) && gr.get(newLoc1) == null && gr.isValid(newLoc) && gr.get(newLoc) == null && gr.isValid(newLoc2) && gr.get(newLoc2) == null) {
				blocks.get(1).moveTo(newLoc1);
				blocks.get(0).moveTo(newLoc);
				blocks.get(2).moveTo(newLoc2);
				rotationPos = 1;
			}
		} else if (rotationPos == 1) {
			newLoc = new Location(blocks.get(0).getLocation().getRow() + 1,
					blocks.get(0).getLocation().getCol() + 1);
			newLoc1 = new Location(blocks.get(1).getLocation().getRow() -1,
					blocks.get(1).getLocation().getCol() - 1);
			newLoc2 = new Location(blocks.get(2).getLocation().getRow() - 2,
					blocks.get(2).getLocation().getCol());
			if (gr.isValid(newLoc1) && gr.get(newLoc1) == null && gr.isValid(newLoc) && gr.get(newLoc) == null && gr.isValid(newLoc2) && gr.get(newLoc2) == null) {
				blocks.get(0).moveTo(newLoc);
				blocks.get(1).moveTo(newLoc1);
				blocks.get(2).moveTo(newLoc2);
				rotationPos = 2;
			}		
		} else if (rotationPos == 2) {
			newLoc = new Location(blocks.get(0).getLocation().getRow() - 1,
					blocks.get(0).getLocation().getCol() + 1);
			newLoc1 = new Location(blocks.get(1).getLocation().getRow() + 1,
					blocks.get(1).getLocation().getCol() - 1);
			newLoc2 = new Location(blocks.get(2).getLocation().getRow(),
					blocks.get(2).getLocation().getCol() - 2);
			if (gr.isValid(newLoc1) && gr.get(newLoc1) == null && gr.isValid(newLoc) && gr.get(newLoc) == null && gr.isValid(newLoc2) && gr.get(newLoc2) == null) {
				blocks.get(0).moveTo(newLoc);
				blocks.get(1).moveTo(newLoc1);
				blocks.get(2).moveTo(newLoc2);
				rotationPos = 3;
			}		
		} else if (rotationPos == 3) {
			newLoc = new Location(blocks.get(0).getLocation().getRow() - 1,
					blocks.get(0).getLocation().getCol() - 1);
			newLoc1 = new Location(blocks.get(1).getLocation().getRow() + 1,
					blocks.get(1).getLocation().getCol() + 1);
			newLoc2 = new Location(blocks.get(2).getLocation().getRow() + 2,
					blocks.get(2).getLocation().getCol());
			if (gr.isValid(newLoc1) && gr.get(newLoc1) == null && gr.isValid(newLoc) && gr.get(newLoc) == null && gr.isValid(newLoc2) && gr.get(newLoc2) == null) {
				blocks.get(0).moveTo(newLoc);
				blocks.get(1).moveTo(newLoc1);
				blocks.get(2).moveTo(newLoc2);
				rotationPos = 0;
			}		
		}

	}

}