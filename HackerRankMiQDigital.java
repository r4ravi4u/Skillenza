import java.io.IOException;
import java.util.*;

interface A	{
	void add() throws Throwable;
	
	default void print()	{
		System.out.println("print A");
	}
	
	strictfp static void print1()	{
		System.out.println("print1 A");
	}
}

interface C {
	void add() throws Exception;
	
	default void print2()	{
		System.out.println("print C");
	}
	
	static void print1()	{
		System.out.println("print1 C");
	}
}

class B implements C, A	{

	public void add() throws Exception{
		System.out.println("add B");
	}
	
	public void sub() throws Throwable{
		System.out.println("sub B");
	}
}

class Z	{
	
	Z(int x)	{
		System.out.println("Z");
	}
	
	{
		System.out.println("init Z");
	}
	
	static
	{
		System.out.println("static init Z");
	}
}

class Y extends Z	{
	Y(int x)	{
		super(x);
		System.out.println("Y");
	}
	
	Y()	{
		super(5);
		System.out.println("Y no args");
	}
	
	{
		System.out.println("init Y");
	}
	
	static
	{
		System.out.println("static init Y");
	}
}

public class HackerRankMiQDigital {
	
	static int[] GoodSubArray(int[] arr, int K, int N){
        if(arr == null || N < 1)
        	return null;
        
        int[] res = new int[N];
        Arrays.fill(res, 1);
        int curr = 1;
        for(int i = 0; i < N-1; i++) {
        	int j = i;
        	while(i < N-1 && arr[i+1] == arr[i] + K)	{
        		curr++;
        		i++;
        	}
        	//int temp = i;
        	while(j < i)	{
        		res[j++] = curr--;
        	}
        	curr = 1;
        }
        
        return res;
    }
	
	private static class Graph {
		private Map<Integer, List<Integer>> map;

		Graph(int size) {
			map = new HashMap<>(size);
		}
		
		void addEdge(int start, int end)	{
						
			if(map.containsKey(start))	{
				List<Integer> val = map.get(start);
				if(val.get(0) == 0)
					val.remove(0);
				val.add(end);
				return;
			}
			List<Integer> val = new ArrayList<>();
			val.add(end);
			map.put(start, val);
		}
		
		List<Integer> getAdjacentVertices(Integer v) {
			return map.get(v);
		}

		@Override
		public String toString() {
			return "Graph [map=" + map + "]";
		}	
	}
	
	private static void solveUtil(Graph g, int src, int dest, int[] res)	{
		
		Queue<List<Integer>> q = new LinkedList<>();
		//Set<Integer> path = new LinkedHashSet<>();
		List<Integer> pathL = new ArrayList<>();
		
		//path.add(src);
		pathL.add(src);
		q.offer(pathL);
		
		while(!q.isEmpty())	{
			pathL=q.poll();
			if(pathL == null)
				continue;
			
			int size = pathL.size() - 1;
			int last = pathL.get(size);//path.stream().skip(size).findFirst().get();
			
			if(last == dest)	{
				if(size > 2 && size >= res[1]*2 + 1)	{
					int rem = size % 2;
					int mid = (size-1)/2;
					
					if(mid > res[1])	{
						res[0] = pathL.get(mid);//path.stream().skip(size- mid - 1).findFirst().get();
						res[1] = mid;
						
						if(rem == 0)	{
							int temp = pathL.get(mid+1);//path.stream().skip(size- mid).findFirst().get();
							res[0] = res[0] < temp ? res[0] : temp;
						}
					}					
					else if(mid == res[1])	{
						int temp = pathL.get(mid);//path.stream().skip(size- mid - 2).findFirst().get();
						res[0] = res[0] < temp ? res[0] : temp;
						
						if(rem == 0)	{
							temp = pathL.get(mid+1);//path.stream().skip(size- mid - 1).findFirst().get();
							res[0] = res[0] < temp ? res[0] : temp;
						}
					}
				}					
			}
			
			List<Integer> adj = g.getAdjacentVertices(last);
			if(adj == null)
				continue;
			for(int i = 0; i < adj.size(); i++)	{
				int temp = adj.get(i);
				boolean flag = false;
				for(int x : pathL)	{
					if(temp == x)	{
						flag = true;
						break;
					}
				}
				if(flag)
					continue;
					
				List<Integer> curr = new ArrayList<>(pathL);
				curr.add(temp);
				q.offer(curr);
			}				
		}
	}
	
	static int[] solve(int[][] edges, int N){
        // return an array with arr[0] = center of the circle and arr[1] = radius
        // of the circle.
        
		int[] res = new int[2];
		res[0] = 1;
		res[1] = 0;
		Graph g = new Graph(N);
		for(int i = 0; i < N-1; i++)	{
			g.addEdge(edges[i][0], edges[i][1]);
			g.addEdge(edges[i][1], 0);
		}
		
		//System.out.println(g);
		
		//Get all paths from 1 to 0(Leaf Node)
		solveUtil(g, 1, 0, res);
		return res;
    
    }
	
	public static void main(String[] args) {
		/*
		 * int[] arr = new int[] {3, 6, 8, 12, 15}; int K = 3; int N = 5;
		 * 
		 * int[] res = GoodSubArray(arr, K, N);
		 * 
		 * for(int x : res) System.out.println(x);
		 */
		
		/*
		int[][] edges = new int[][] {{1, 2},
										{1, 8},
										{2, 4},
										{4, 6},
										{4, 7},
										{7, 5},
										{5, 11},
										{8, 3},
										{3, 9},
										{9, 10},
									};
		
		int N = 11;
		
		int[] res = solve(edges, N);
		for(int x : res)
			System.out.println(x);
			*/
		
		new Y();
	}

}
