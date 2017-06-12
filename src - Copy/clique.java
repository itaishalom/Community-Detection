import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;


public class clique {

	TreeSet<node> cliqueMembers = new TreeSet<node>();
	ArrayList<clique> cliqueFriends = new ArrayList<clique>();
	private int num;
	private boolean visited;
	public clique (ArrayList<node> newClique)
	{
		cliqueMembers = new TreeSet<node>(newClique);
	}
	public boolean isVisited()
	{
		return this.visited;
	}
	public void setVisited()
	{
		 this.visited= true;
	}
	public TreeSet<node> getSet(){
		return cliqueMembers;
	}
	
	public void addFriend(clique c)
	{
		this.cliqueFriends.add(c);
	}
	
	public void setNun(int num)
	{
		this.num = num;
	}
	
	public int getNun()
	{
		return this.num;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!clique.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		final clique other = (clique) obj;

		if (this.cliqueMembers.equals(other.cliqueMembers))
			return true;
		return false;
	}
	public boolean isFriend(clique other)
	{
		TreeSet<node> otherClicqueSet = new TreeSet<node>(other.getSet());

		otherClicqueSet.removeAll(this.cliqueMembers);
		if (otherClicqueSet.size()==1)
		{
			cliqueFriends.add(other);
			other.addFriend(this);
			return true;
		}
		return false;
	}
	public String toString()
	{
		String s=Integer.toString(this.num)+"->";
		for (int i = 0; i < cliqueFriends.size(); i++) 
		{
			s+= cliqueFriends.get(i).getNun()+" ";
		}
		s = s.trim();
		return s;//
	}
	
	/*
	public String toString()
	{
		String s="(";

		//get the Iterator
		Iterator<node> itr = cliqueMembers.iterator();

		
		while(itr.hasNext())
			s+=(itr.next())+",";
	s= s.substring(0, s.length()-1);
	s +=")";
	return s;
	}
	*/
}


