import java.util.ArrayList;


public class node extends vertex implements Comparable{
	

	private int addedNum;
	public node(int name)
	{
		this.friends = new ArrayList<vertex>();
		this.num = name;
	}
	

	
	public void setAddedNum(int addedNum)
	{
		this.addedNum =addedNum;
	}
	

	

	
	public String toString()
	{
		String s = "["+Integer.toString(this.num)+"]->{";
		for (int i=0;i<friends.size();i++)
		{
			s+= friends.get(i).getNum()+" ";
		}
		s.trim();
		s+="}";
		return s;
	}
	/*
	public String toString()
	{
		String s = "["+Integer.toString(this.num)+"]->{";
	
		return s;
	}
	*/
	
	public ArrayList<vertex> getFriends()
	{
		return friends;
	}
	public int getAdded(){
		return this.addedNum;
	}





	@Override
	public void addFriend(vertex vertex) {
		
		friends.add(vertex);
	}
	
}
