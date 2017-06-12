import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;


public class clique extends vertex{

	TreeSet<node> cliqueMembers = new TreeSet<node>();
	
	

	public clique (ArrayList<node> newClique)
	{
		friends = new ArrayList<vertex>();
		cliqueMembers = new TreeSet<node>(newClique);
	}

	public TreeSet<node> getSet(){
		return cliqueMembers;
	}
	
	public boolean contains(node n){
		return cliqueMembers.contains(n);
	}
	
	public void addFriend(clique c)
	{
		this.friends.add(c);
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
		TreeSet<node> thisClicqueSet = new TreeSet<node>(this.getSet());
		otherClicqueSet.removeAll(this.cliqueMembers);
		thisClicqueSet.removeAll(other.getSet());
		if (otherClicqueSet.size()==1||thisClicqueSet.size()==1)
		{
			friends.add(other);
			other.addFriend(this);
			return true;
		}
		return false;
	}
	public String toString()
	{
		String s="";
		
		for(vertex e:cliqueMembers){
            s+=e.getNum()+" ";
        }
		/*
		s += Integer.toString(this.getNum())+"->[";
		for(int i=0;i<this.getFriends().size();i++)
		{
			s+=this.getFriends().get(i).getNum()+",";
		}
		s+="]";
		*/
		return s;
	}
	/*
	public String toString()
	{
		String s=Integer.toString(this.num)+"->";
		for (int i = 0; i < friends.size(); i++) 
		{
			s+= friends.get(i).getNum()+" ";
		}
		s = s.trim();
		return s;//
	}
	*/
	@Override
	public ArrayList<vertex> getFriends() {
		// TODO Auto-generated method stub
		return friends;
	}
	@Override
	public void addFriend(vertex vertex) {
		isFriend((clique) vertex);
		
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


