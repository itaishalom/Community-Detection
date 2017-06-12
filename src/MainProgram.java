import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Produly written by Itai Shalom - first creates a network of nodes, runs dfs to get components
 * Then, finds cliques and turn them into nodes to, connect cliques if they have k-1 shared verticies.
 * And then again runs dfs to get communities.
 * @author Itai
 *
 */
public class MainProgram {


	static class CommunityComp implements Comparator<vertex>{
		@Override
		public int compare(vertex v1, vertex v2) {
			return v1.getNum() - v2.getNum();
		}
	}  

	static ArrayList<vertex> allCliques;
	static ArrayList<vertex> allNodes;
	public static void main(String args[]) throws FileNotFoundException, IOException
	{
		createArrayList();
		printNumOfNodesAndEdges();
		getConnectedComponents("Components",allNodes);
		allCliques = new ArrayList<vertex>();
		for(int i =0;i<allNodes.size();i++){
			findClique((node)allNodes.get(i));
		}
		createGraphOfCliqeus();
		getConnectedComponents("Communities",allCliques);
	}

	/**
	 * Connects the cliques if they have only 1 vertex which is not common
	 */
	public static void createGraphOfCliqeus()
	{
		for(int i=0;i<allCliques.size();i++)
		{
			for (int j=i;j<allCliques.size();j++)
			{
				 allCliques.get(i).addFriend(allCliques.get(j));
				 
			}
		}
	}

	/**
	 * Finds cliques
	 * @param n
	 */
	public static void findClique(node n)
	{

		ArrayList<node> A = new ArrayList<node>();
		ArrayList<vertex> B = new ArrayList<vertex>();

		A.add(n);
		B= n.getFriends();
		boolean found=false;
		for(int i=0;i<=B.size();i++)
		{
			ArrayList<node> Aa = new ArrayList<node>(A);
			ArrayList<vertex> Bb = new ArrayList<vertex>(B);
		//	if(i>0)
			//	Bb.remove(i-1);
			found = helper(Aa,Bb,4,i);
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
	/**
	 * Helper function to get cliques
	 * @param A
	 * @param B
	 * @param k
	 * @param start
	 * @return
	 */
	public static boolean helper(ArrayList<node> A,ArrayList<vertex> B,int k,int start)
	{
		while(B.size()>0)
		{
			if(B.size()<=start){
				start = B.size()-1;
			}
			node w = (node)B.remove(start);
			A.add(w);
			for (Iterator<vertex> iterator = B.iterator(); iterator.hasNext(); ) {
				node px = (node)iterator.next();

				if(!w.getFriends().contains(px))
				{
					iterator.remove();
				}
			}
		}
		return (A.size()>=k);
	}

	/**
	 * Runs a dfs on each vertex (to get connected components)
	 * @param text
	 * @param arr
	 */
	public static void getConnectedComponents(String text,ArrayList<vertex> arr){
		ArrayList<Integer> compSizes = new ArrayList<Integer>();
		int coNum = 0;
		for (int i=0;i<arr.size();i++){
			if(!arr.get(i).isVisited())
			{	
				compSizes.add(DFS_iterative(arr.get(i),coNum));
				coNum++;
			}
		}

		printResultForConnectedComponents(text, compSizes);
	}

	/**
	 * Prints the results
	 * @param text
	 * @param compSizes
	 */
	public static void printResultForConnectedComponents(String text, ArrayList<Integer> compSizes)
	{
		System.out.println("there are "+compSizes.size()+" "+text);
		System.out.println(text+"' sizes: ");
		if (compSizes.size()==0)
			return;
		int max = compSizes.get(0);
		for (int i=0;i<compSizes.size();i++){
			System.out.print(compSizes.get(i)+" ");
			if (compSizes.get(i)>max)
				max = compSizes.get(i);
		}
		System.out.print("-> So the max is "+max);
		if(text.equals("Communities"))
		{
			System.out.println(" cliques");
			TreeSet<vertex> comuTree = new TreeSet<vertex>(new CommunityComp());
			for(int i=0;i<allCliques.size();i++)
			{
				comuTree.add(allCliques.get(i));
			}
			ArrayList<String> results = new ArrayList<String>();
			
			for(vertex e:comuTree){
				clique c = (clique) e;

		
				if(results.size()==e.getCoNum()){
					results.add(c.toString());
				}
				else
				{
					String s = results.get(e.getCoNum());
					s += c.toString()+" ";

					s = deDup(s)+" ";

					results.set(e.getCoNum(), s) ;

				}
				//    System.out.println(c+" com: "+e.getCoNum());
			}

			for(int k=0;k<results.size();k++)
			{
				TreeSet<Integer> finalResults = new TreeSet<Integer>();
				String[] splited = results.get(k).split(" ");
				for(int j =0;j<splited.length;j++)
				{
					finalResults.add(Integer.valueOf(splited[j]));
				}
				String toArr="";
				for(Integer e:finalResults)
				{
					toArr+=Integer.toString(e)+",";
				}
				results.set(k, toArr) ;
			}
			System.out.println(text);
			for(int k=0;k<results.size();k++)
			{
				System.out.println(k+": "+results.get(k));
			}
		}
		else{
			System.out.println();
		}
		System.out.println("======================");
	
	}

	/**
	 * Removing duplicates for printing 
	 * @param s
	 * @return
	 */
	public static String deDup(String s) {
		return new LinkedHashSet<String>(Arrays.asList(s.split(" "))).toString().replaceAll("(^\\[|\\]$)", "").replace(", ", " ");
	}

	/**
	 * Implemented a DFS in order to find connected components ()
	 * @param n - Starting vertex
	 * @param coNum - component number
	 * @return
	 */
	public static int DFS_iterative(vertex n,int coNum){
		int size =0;
		Stack<vertex> s = new Stack<vertex>();
		s.push(n);
		while(!s.isEmpty())
		{
			vertex v = s.pop();
			if (!v.isVisited())
			{
				v.setVisited();
				v.setCoNum(coNum);
				size++;
				for(int i =0;i<v.getFriends().size();i++)
				{
					s.push(v.getFriends().get(i));
				}
			}
		}
		return size;
	}


	/**
	 * This function prints how much nodes and edges
	 */
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

	
	/**
	 * This functio reads the input file and creates the network
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void createArrayList() throws FileNotFoundException, IOException
	{
		int addedNum=0;
		allNodes = new ArrayList<vertex>();
		String path = System.getProperty("user.dir")+File.separator+"src"+File.separator+"fbegonet.txt";
		File file = new File(path);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] splited = line.split(" ");
				node a = new node(Integer.parseInt(splited[0]));
				node b = new node(Integer.parseInt(splited[1]));
				//Check for selfloop
				if(a.equals(b))
				{
					if(allNodes.contains(a))
					{
						continue;
					}else{
						addedNum++;
						allNodes.add(a);
						continue;
					}
				}
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
						if(!allNodes.get(allNodes.indexOf(a)).getFriends().contains(allNodes.get(allNodes.indexOf(b))))
							allNodes.get(allNodes.indexOf(a)).addFriend(allNodes.get(allNodes.indexOf(b)));
						else
							continue;
					}

				}
				if(!allNodes.contains(b)){
					b.setAddedNum(addedNum);
					vertex v = allNodes.get(allNodes.indexOf(a));
					b.addFriend(v);
					addedNum++;
					allNodes.add(b);
				}else{

					allNodes.get((allNodes.indexOf(b))).addFriend(allNodes.get(allNodes.indexOf(a)));
				}
			}
		}
	}
}

