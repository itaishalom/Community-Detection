import java.util.ArrayList;


public abstract class vertex implements Comparable{
	protected ArrayList<vertex> friends;
	protected boolean visited;
	protected int num;
	protected int coNum;
	public void setCoNum(int num)
	{
		this.coNum =num;
	}
	
	public int getCoNum()
	{
		return this.coNum;
	}
	public int getNum()
	{
		return num;
	}
	public void setNun(int num)
	{
		this.num = num;
	}
	public boolean isVisited()
	{
		return this.visited;
	}
	public void setVisited()
	{
		 this.visited= true;
	}
	public abstract ArrayList<vertex> getFriends();
	
	public int getNumOfFriends()
	{
		return friends.size();
	}
	@Override
	public int compareTo(Object obj) {
		final vertex other = (vertex) obj;
		return this.getNum() - other.getNum();
	}
	public abstract void addFriend(vertex vertex);// {
		// TODO Auto-generated method stub
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!node.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final vertex other = (vertex) obj;
	    if (this.num==other.getNum())
	    	return true;
	    return false;
	}
	public abstract String toString();
	
}
