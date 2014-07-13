import java.util.Stack;

/**
 * The Class Agent.
 * Represents the actual agent doing the actions
 */
public class Agent
{
	
	/** The Constant DEPTH_LIMIT. */
	private static final int DEPTH_LIMIT = 10;
	
	/** The grid. */
	private Grid grid;
	
	/** The y pos. */
	private int xPos,yPos;
	
	/** The cost. */
	private double cost;
	
	/** The final depth. */
	private int finalDepth;
	
	/** The prev action. */
	private Action prevAction;
	
	/** The solution stack. */
	private Stack<Agent> solutionStack;
	
	/**
	 * The Enum Action, enumerates the actions that can be done by this agent
	 */
	public enum Action
	{
		SUCK,
		GO_NORTH,
		GO_EAST,
		GO_SOUTH,
		GO_WEST
	}
	
	/**
	 * Instantiates a new agent.
	 *
	 * @param grid the grid
	 * @param xPos the x pos
	 * @param yPos the y pos
	 */
	public Agent(Grid grid, int xPos, int yPos)
	{
		this.grid = grid;
		this.xPos = xPos;
		prevAction = null;
		this.yPos = grid.getHeight()-yPos-1;
	}
	
	/**
	 * Copy constructor
	 *
	 * @param agent the agent
	 */
	private Agent(Agent agent)
	{
		this.copy(agent);
	}
	
	/**
	 * Copy the values from agent to this object
	 *
	 * @param agent the agent
	 */
	private void copy(Agent agent)
	{
		this.grid = new Grid(agent.grid);
		this.xPos = agent.xPos;
		this.yPos = agent.yPos;
		this.finalDepth = agent.finalDepth;
		this.cost = agent.cost;
		this.prevAction = agent.prevAction;
	}
	
	/**
	 * Do action from given
	 *
	 * @param action the action
	 * @return the cost of the action
	 */
	public double doAction(Action action)
	{
		prevAction = action;
		switch(action)
		{
			case GO_EAST: return doGoEast();
			case GO_NORTH: return doGoNorth();
			case GO_SOUTH: return doGoSouth();
			case GO_WEST: return doGoWest();
			case SUCK: return doSuck();
		}
		
		return 0;
	}

	/**
	 * Do the suck action
	 *
	 * @return the double
	 */
	private double doSuck()
	{
		this.grid.cleanRoom(xPos, adjustYPos());
		return 1.0;
	}

	/**
	 * Go west.
	 *
	 * @return the cost
	 */
	private double doGoWest()
	{
		if(xPos>0)
		{
			xPos--;
		}
		return 1.0;
	}

	/**
	 * Go south.
	 *
	 * @return the cost
	 */
	private double doGoSouth()
	{
		if(yPos<this.grid.getHeight()-1)
		{
			yPos++;
		}
		return 1.0;
	}

	/**
	 * Go north.
	 *
	 * @return the cost
	 */
	private double doGoNorth()
	{
		if(yPos>0)
		{
			yPos--;
		}
		return 1.0;
	}

	/**
	 * Go east.
	 *
	 * @return the cost
	 */
	private double doGoEast()
	{
		if(xPos<this.grid.getWidth()-1)
		{
			xPos++;
		}
		return 1.0;
	}
	
	/**
	 * Initiates the Depth Limited Search
	 */
	public void findSolution()
	{
		cost = 0;
		finalDepth = 0;
		
		solutionStack = new Stack<Agent>();
		
		Agent solution = new Agent(this);
		solution.cost = 0;
		solution = this.DLS(solution,0);
		if(solution!=null)
		{
			this.copy(solution);
			
			System.out.println("SOLUTION: ");
			for(int i=1;i<=solutionStack.size();i++)
			{
				System.out.println("DEPTH: "+i+"| ACTION DONE: "+solutionStack.get(i-1).prevAction+"\n"+solutionStack.get(i-1));
			}
			System.out.println("COST: "+this.cost+"| FINAL DEPTH: "+this.finalDepth+"\n");
		}
		else
		{
			System.out.println("No solution found");
		}
	}

	/**
	 * The actual recursive Depth-Limited Search
	 *
	 * @param agent the agent from the previous recursion
	 * @param depth the current depth
	 * @return the agent, null if no solution within depth limit is found
	 */
	private Agent DLS(Agent agent,int depth)
	{
		if(depth>=0 && depth<=DEPTH_LIMIT)
		{
			if(agent.grid.isAllClean())
			{
				agent.finalDepth = depth;
				return agent;
			}
			else
			{
				for(Action action:Action.values())
				{
					Agent copy = new Agent(agent);
					
					copy.cost += copy.doAction(action);
					solutionStack.push(copy);
					
					copy = DLS(copy,depth+1);
					if(copy!=null)
					{
						return copy;
					}
					else
					{
						solutionStack.pop();
					}
				}
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Current Position: ("+xPos+","+adjustYPos()+")\n");
		sb.append(this.grid.toString());
		return sb.toString();
	}
	
	/**
	 * Adjust y coordinate
	 *
	 * @return the int
	 */
	private int adjustYPos()
	{
		return grid.getHeight()-yPos-1;
	}
	
}
