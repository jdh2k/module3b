/**
 * The Class Grid.
 * Represents the actual grid the agent is working on
 */
public class Grid
{
	
	/** The rooms. */
	private Room[][] rooms;
	
	/** The width and height of the rooms. */
	private int height,width;

	/**
	 * Instantiates a new grid.
	 *
	 * @param height the height
	 * @param width the width
	 */
	public Grid(int height,int width)
	{
		this.height = height;
		this.width = width;
		rooms = new Room[height][width];
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				rooms[i][j] = new Room(true);
			}
		}
	}
	
	/**
	 * Copy constructor
	 *
	 * @param grid the grid to be copied
	 */
	public Grid(Grid grid)
	{
		this.height = grid.height;
		this.width = grid.width;
		rooms = new Room[height][width];
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				this.rooms[i][j] = new Room(grid.rooms[i][j]);
			}
		}
	}
	
	/**
	 * Clean room given the coordinates
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void cleanRoom(int x,int y)
	{
		rooms[y][x].setDirty(false);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				sb.append("("+j+", "+adjustHeight(i)+", "+rooms[adjustHeight(i)][j]+") ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * Checks if all rooms are clean
	 *
	 * @return true, if is all clean
	 */
	public boolean isAllClean()
	{
		boolean ret=true;
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				if(rooms[i][j].isDirty())
				{
					return false;
				}
			}
		}
		return ret;
	}
	
	/**
	 * Adjust height to the coordinate system used
	 *
	 * @param y the y coordinate
	 * @return the adjust y coordinate
	 */
	private int adjustHeight(int y)
	{
		return height-y-1;
	}
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}
	
}
