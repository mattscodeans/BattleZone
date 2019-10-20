public class Location
{
    private int coorX;
    private int coorY;
    
    public Location(int x, int y) {
        coorX = x;
        coorY = y;
    }
    
    public int getX()
    {
        return coorX;
    }
    
    public int getY()
    {
        return coorY;
    }
    public boolean equals(Location l)
    {
        if(this.getX() == l.getX() && this.getY() == l.getY())
        {
            return true;
        }
      return false;
    }
    public String toString()
    {
    	return "" + coorX + " " + coorY;
    }
}
