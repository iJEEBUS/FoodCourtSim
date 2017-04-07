
/**
 * Write a description of class Location here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Location
{
    private int row;
    private int column;
    
    public Location(int r, int c)
    {
        row = r;
        column = c;
    }
    
    public void setRow(int r)
    {
        row = r;
    }
    
    public void setCol(int c)
    {
        column = c;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return column;
    }
}
