import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class HackerEarthScribeTechGraph {
	
	static class Edge
	{
		int v;	//vertex name
		String str;
		
		Edge(int v, String str)
		{
			this.v = v;
			this.str = str;
		}

		@Override
		public String toString() {
			return "Edge [v=" + v + ", str=" + str + "]";
		}	
		
	}
	
	static class Graph {
		private Map<Integer, List<Edge>> map;

		public Graph() {
			map = new HashMap<>();
		}
		
		public void addEdge(int start, int end , String s)	{
			Edge e = new Edge(end, s);
			
			if(map.containsKey(start))	{
				List<Edge> val = map.get(start);
				val.add(e);
				return;
			}
			List<Edge> val = new ArrayList<>();
			val.add(e);
			map.put(start, val);
		}
		
		public boolean contains(int src)
		{
			return map.containsKey(src);
		}

		public List<Edge> getAdjacentVertices(Integer v) {
			return map.get(v);
		}

		@Override
		public String toString() {
			return "Graph [map=" + map + "]";
		}
	
	}
	
	static boolean palindrome(String str)	{
		Map<Character, Integer> map = new HashMap<>();
		for(char ch : str.toCharArray())	{
			if(map.containsKey(ch))	{
				int val = map.get(ch);
				map.put(ch, ++val);
				continue;
			}
			map.put(ch, 1);
		}
		
		boolean flag = false;
		for(Map.Entry<Character, Integer> e : map.entrySet())	{
			if(e.getValue() % 2 != 0)	{
				if(flag)
					return false;
				flag = true;	
			}
		}
		return true;
	}
	
	static boolean pathExistsPalindrome(Graph g, int src, int dest)	{
		
		Queue<LinkedHashMap<Integer, StringBuilder>> q = new LinkedList<>();
		LinkedHashMap<Integer, StringBuilder> path = new LinkedHashMap<>();	
		
		path.put(src, new StringBuilder());
		q.offer(path);
		
		while(!q.isEmpty())	{
			path=q.poll();
			if(path == null)
				continue;
			
			int last = (int)((Map.Entry)path.entrySet().toArray()[path.size() -1]).getKey();
			
			if(last == dest)	{
				//check if palindrome or not
				if(palindrome(path.get(last).toString()))	{
					return true;
				}
			}
			
			List<Edge> adj = g.getAdjacentVertices(last);
			if(adj == null)
				continue;
			for(int i = 0; i < adj.size(); i++)	{
				if(path.containsKey(adj.get(i).v))
					continue;
				
				LinkedHashMap<Integer, StringBuilder> curr = new LinkedHashMap<>(path);
				Edge e = adj.get(i);
				StringBuilder result = (StringBuilder)((Map.Entry)path.entrySet().toArray()[path.size() -1]).getValue();
				StringBuilder temp = new StringBuilder(result);
				temp.append(e.str);
				curr.put(e.v, temp);				
				q.offer(curr);
			}				
		}
		
		return false;
	}
	static String[] solve(String[] qry, String[] edg, int N){
        if(qry == null || edg == null || edg.length < 1 || qry.length < 1 || N < 2)
        	return null;
        
		Graph g = new Graph();
        for(String s : edg)	{
			String[] str = s.split(" ");
			int v1 = Integer.parseInt(str[0]);
			int v2 = Integer.parseInt(str[1]);
			g.addEdge(v1, v2, str[2]);
			g.addEdge(v2, v1, str[2]);
		}
        
        String[] result = new String[qry.length];
        int index = 0;
        for(String s : qry)	{
			String[] str = s.split(" ");
			int v1 = Integer.parseInt(str[0]);
			int v2 = Integer.parseInt(str[1]);
			
			if(v1 == v2)	{
				result[index++] = "YES";
				continue;
			}
			
			if(!(g.contains(v1) && g.contains(v2)))	{
				result[index++] = "NO";
				continue;
			}
				
			if(pathExistsPalindrome(g, v1, v2)) {
				result[index++] = "YES";
			}
			else
				result[index++] = "NO";
			
        }
        
		return result;
    }

	public static void main(String[] args) {

		String[] edg = new String[] {"1 3 bc", "1 2 abc", "2 3 c"};
		String[] qry = new String[] {"1 2", "2 3", "3 1", "3 3"};
		int N = 3;
		
		String[] res = solve(qry, edg, N);
		
		for(String s : res)
			System.out.println(s);
	}

}
