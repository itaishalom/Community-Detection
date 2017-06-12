import java.util.ArrayList;


public abstract class vertex implements Comparable{

	protected boolean visited;
	protected int num;
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
	
	/*@Override

	public int compareTo(Object obj) {
		final vertex other = (node) obj;
		return this.getNum() - other.getNum();
	}
	*/
	//public abstract void addFriend(vertex vertex); 
		// TODO Auto-generated method stub
		
	
}
