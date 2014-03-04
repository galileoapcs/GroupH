package group.H.TCKO;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Jumper extends Bug
{
    private int steps;

    public Jumper()
    {
        steps = 0;
    }
    
    public void jump() {
    	Grid<Actor> gr = getGrid();
    	Location loc = getLocation();
    	Location next = loc.getAdjacentLocation(getDirection());
    	Location twoAway = next.getAdjacentLocation(getDirection());
    	if (gr.isValid(twoAway))
    		moveTo(twoAway);
    	else
    		removeSelfFromGrid();
    }
    
    public boolean canJump() {
    	Grid<Actor> gr = getGrid();
    	if (gr == null) return false;
    	Location loc = getLocation();
    	Location next = loc.getAdjacentLocation(getDirection());
    	Location twoAway = next.getAdjacentLocation(getDirection());
    	if (gr.isValid(twoAway) && gr.get(twoAway) == null) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void act()
    {
    	if (canJump()) {
    		jump();
    		steps+= 2;
    	} else {
    		if (canMove()) {
                move();
                steps++;
            } else {
                turn();
                turn();
                steps = 0;
            }
    	}
    }
}
