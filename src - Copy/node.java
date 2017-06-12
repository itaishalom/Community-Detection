import java.util.ArrayList;


public class node implements Comparable{
	private ArrayList<node> friends;
	private int name;
	private int addedNum;
	private boolean visited;
	private int originalNumber;
	public node(int name)
	{
		this.friends = new ArrayList<node>();
		this.name = name;
		this.visited = false;
	}
	
	/*
	public void setOriginalNumber(int num)
	{
		this.originalNumber = num;
	}
	public int getOriginalNumber(int num)
	{
		return this.originalNumber;// = num;
	}
	*/
	public boolean isVisited()
	{
		return this.visited;
	}
	public void setVisited()
	{
		 this.visited= true;
	}
	
	public void setAddedNum(int addedNum)
	{
		this.addedNum =addedNum;
	}
	
	public void addFriend(node n)
	{
		friends.add(n);
	}
	
	public int getNumOfFriends()
	{
		return friends.size();
	}
	
	public String toString()
	{
		String s = "["+Integer.toString(this.name)+"]->{";
		for (int i=0;i<friends.size();i++)
		{
			s+= friends.get(i).name+" ";
		}
		s.trim();
		s+="}";
		return s;
	}
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!node.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final node other = (node) obj;
	    if (this.name==other.name)
	    	return true;
	    return false;
	}
	
	public ArrayList<node> getFriends()
	{
		return friends;
	}
	public int getAdded(){
		return this.addedNum;
	}

	@Override
	public int compareTo(Object obj) {
		final node other = (node) obj;
		return this.name - other.name;
	}
	
}
