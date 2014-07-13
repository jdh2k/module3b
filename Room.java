/**
 * The Class Room.
 * Provides abstraction to a room.
 * Is either "dirty" or "clean"
 */
public class Room
{

	/** Value indicating if this room is dirty*/
	private boolean dirty;

	/**
	 * Instantiates a new room.
	 *
	 * @param dirty the dirty
	 */
	public Room(boolean dirty)
	{
		this.dirty = true;
	}
	
	/**
	 * Copy constructor
	 *
	 * @param room the room
	 */
	public Room(Room room)
	{
		this.dirty = room.dirty;
	}

	/**
	 * Checks if is dirty.
	 *
	 * @return true, if is dirty
	 */
	public boolean isDirty()
	{
		return dirty;
	}

	/**
	 * Sets the dirty.
	 *
	 * @param dirty the new dirty
	 */
	public void setDirty(boolean dirty)
	{
		this.dirty = dirty;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return isDirty()?"Dirty":"Clean";
	}
	
}
