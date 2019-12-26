import java.util.*;

public class CrossOver {
	
	private static boolean binSearch(int[] arr, int l, int r, int x, Set<Integer> set)	{
		if (r >= l) { 
            int mid = l + (r - l) / 2;       
  
            if (arr[mid] == x && !set.contains(mid))	{
            	set.add(mid);
            	return true;            	 
            }
            
            else if(arr[mid] == x)	{
            	return binSearch(arr, l, mid - 1, x, set) || binSearch(arr, mid + 1, r, x, set);
            }
            
            if (arr[mid] > x) 
                return binSearch(arr, l, mid - 1, x, set); 
  
            return binSearch(arr, mid + 1, r, x, set); 
        } 
		
		return false; 
	}
	
	private static int getWays(int[] a, int[] b, int sum)	{
		//sort longer array so that we can apply binary search for target - element in another array
		
		int[] temp;
		boolean flag = false;
		
		if(a.length >= b.length)	{
			temp = new int[a.length];
			System.arraycopy(a, 0, temp, 0, a.length);
			flag = true;
		}			
		else	{
			temp = new int[b.length];
			System.arraycopy(b, 0, temp, 0, b.length);
		}			
		
		Arrays.parallelSort(temp);
		
		Set<Integer> set = new HashSet<>();	//to contain indices matched
		int ways = 0;
		if(flag)	{//use b and temp
			for(int x : b)	{
				while(binSearch(temp, 0, temp.length-1, sum-x, set))
					ways++;
			}
		}
		else	{//use a and temp
			for(int x : a)	{
				while(binSearch(temp, 0, temp.length-1, sum-x, set))
					ways++;
			}
		}
		return ways;		
	}
	
	int[] coolFeature(int[] a, int[] b, int[][] query) {
		
		int[] result = new int[query.length];
		Arrays.fill(result, -1);
		
		int index = 0;
		for(int i = 0; i < query.length; i++)	{
			if(query[i].length == 3)	{//form 0,i,x
				b[query[i][1]] = query[i][2];
				continue;
			}
			//means its of form 1,x and hence we need to calculate ways
			result[index++] = getWays(a, b, query[i][1]);
		}
		
		if(index < query.length)	{
			int[] finalRes = new int[index];
			
			System.arraycopy(result, 0, finalRes, 0, index);
			return finalRes;
		}
		
		return result;
	}

	public static void main(String[] args) {
		int[] a = new int[] {3,1,1};//{1,2,2};
		int[] b = new int[] {3,2,1};//{2,3};
		int[][] query = new int[][]	{
										{0,0,4},//{1,4},
										{0,0,4},//{0,0,3},
										{1,2},//{1,5}
									};
								
		int[] res = new CrossOver().coolFeature(a, b, query);
		
		for(int i : res)
			System.out.println(i);
	}

}
