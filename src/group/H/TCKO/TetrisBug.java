package group.H.TCKO;

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class TetrisBug extends Bug
{
    private Color color;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public TetrisBug(Color _color)
    {
        color = _color;
        setDirection(180);
    }
    
    public void move() {
    	Grid<Actor> gr = getGrid(); 
    	if (gr == null) 
    		return; 
    	Location loc = getLocation(); 
    	Location next = loc.getAdjacentLocation(getDirection()); 
    	if (gr.isValid(next)) 
    		moveTo(next); 
    	else 
    		removeSelfFromGrid();
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        
    }
}
