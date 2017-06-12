import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


public class MainProgram {

	//Go through the lines
	//Build map of nodes
	//build adj matrix
	//run dfs
	static ArrayList<clique> allCliques;
	static ArrayList<node> allNodes;
	public static void main(String args[]) throws FileNotFoundException, IOException
	{
	//	
		
		
		createArrayList();
		
		printNumOfNodesAndEdges();
		getConnectedComponents(true);
		allCliques = new ArrayList<clique>();
		for(int i =0;i<allNodes.size();i++){
			findClique(allNodes.get(i));
		}
		createGraphOfCliqeus();
	}

	public static void createGraphOfCliqeus()
	{
		for(int i=0;i<allCliques.size();i++)
		{
			for (int j=i+1;j<allCliques.size();j++)
			{
				allCliques.get(i).isFriend(allCliques.get(j));
			}
		}
		
	}

	public static void findClique(node n)
	{
		ArrayList<node> A = new ArrayList<node>();
		ArrayList<node> B = new ArrayList<node>();

		A.add(n);
		B= n.getFriends();
		boolean found=false;
		for(int i=0;i<B.size();i++)
		{
			ArrayList<node> Aa = new ArrayList<node>(A);
			ArrayList<node> Bb = new ArrayList<node>(B);
			if(i>0)
				Bb.remove(i-1);
			found = helper(Aa,Bb,4);
			if(found)
			{		
				clique c = new clique(Aa);
				if(!allCliques.contains(c))
				{
					c.setNun(allCliques.size());
					allCliques.add(c);
				}
			}
		}

	}
	public static boolean helper(ArrayList<node> A,ArrayList<node> B,int k)
	{
		while(A.size()<k && B.size()>0)
		{
			node w = B.remove(0);
			A.add(w);
			for (Iterator<node> iterator = B.iterator(); iterator.hasNext(); ) {
				node px = iterator.next();

				if(!w.getFriends().contains(px))
				{
					iterator.remove();
				}
			}
		}
		return (A.size()==k);
	}

	public static void getConnectedComponents(boolean printResult){


		ArrayList<Integer> compSizes = new ArrayList<Integer>();
		for (int i=0;i<allNodes.size();i++){
			if(!allNodes.get(i).isVisited())
			{		
				compSizes.add(DFS_iterative(allNodes.get(i)));
			}
		}
		if(printResult)
			printResultForConnectedComponents(compSizes);
	}
	
	public static void printResultForConnectedComponents(ArrayList<Integer> compSizes)
	{
		System.out.println("there are "+compSizes.size()+" components");
		System.out.println("Components' sizes: ");
		int max = compSizes.get(0);
		for (int i=0;i<compSizes.size();i++){
			System.out.print(compSizes.get(i)+" ");
			if (compSizes.get(i)>max)
				max = compSizes.get(i);
		}
		System.out.println("-> So the max is "+max);
	//	return compSizes.size();
	}
	/*
	public static int dfs(node startNode,int compSize)  
	{  
		ArrayList<node> neighbours=  startNode.getFriends();//findNeighbours(adjacency_matrix,node);  
		for (int i = 0; i < neighbours.size(); i++) {  
			node n=neighbours.get(i);  
			if(n!=null && !n.isVisited())  
			{  
				n.setVisited();//=true;
				compSize++;
				compSize = dfs(n,compSize);  



			}  
		}  
		return compSize;
	}  
	 */
	public static int DFS_iterative(node n){
		int size =0;
		Stack<node> s = new Stack<node>();
		s.push(n);
		while(!s.isEmpty())
		{
			node v = s.pop();
			if (!v.isVisited())
			{
				v.setVisited();
				size++;
				for(int i =0;i<v.getFriends().size();i++)
				{
					s.push(v.getFriends().get(i));
				}
			}
		}
		return size;
	}


	public static void printNumOfNodesAndEdges()
	{
		System.out.println("Num of nodes: "+allNodes.size());
		int countEdges =0;
		for (int i=0;i<allNodes.size();i++)
		{
			countEdges += allNodes.get(i).getNumOfFriends();
		}
		System.out.println("Num of edges: "+countEdges/2);
	}

	public static void createArrayList() throws FileNotFoundException, IOException
	{
		int addedNum=0;
		allNodes = new ArrayList<>();
		String path = System.getProperty("user.dir")+File.separator+"src"+File.separator+"fbegonet2.txt";
		File file = new File(path);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] splited = line.split(" ");
				node a = new node(Integer.parseInt(splited[0]));
				node b = new node(Integer.parseInt(splited[1]));
				//     
				if(!allNodes.contains(a))
				{
					if(!allNodes.contains(b))
					{
						a.addFriend(b);	
					}else{
						a.addFriend(allNodes.get(allNodes.indexOf(b)));
					}

					a.setAddedNum(addedNum);
					addedNum++;
					allNodes.add(a);
				}
				else
				{
					if(!allNodes.contains(b))
					{
						allNodes.get(allNodes.indexOf(a)).addFriend(b);	
					}else{
						allNodes.get(allNodes.indexOf(a)).addFriend(allNodes.get(allNodes.indexOf(b)));
					}

				}
				if(!allNodes.contains(b)){
					b.setAddedNum(addedNum);
					b.addFriend(allNodes.get(allNodes.indexOf(a)));
					addedNum++;
					allNodes.add(b);
				}else{

					allNodes.get((allNodes.indexOf(b))).addFriend(allNodes.get(allNodes.indexOf(a)));
				}
			}
		}
	}
}
/*
  		ArrayList<node> allNodes2 = new ArrayList<>(allNodes);
		clique first = new clique(allNodes);
		allNodes2.remove(0);
		allNodes2.add(allNodes.get(0));
		for (int i = 0;i<allNodes2.size();i++){
			System.out.println(allNodes2.get(i));
			System.out.println(allNodes.get(i));
			System.out.println("===========");
		}
		clique second = new clique(allNodes2);
		System.out.println(first.equals(second));
 */ 
