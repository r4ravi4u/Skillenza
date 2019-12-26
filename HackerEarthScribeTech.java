import java.util.*;

public class HackerEarthScribeTech {
	
	static long solve(int N,int[] T){
		if(N < 1 || T == null || T.length < 1)
			return 0;
		
        long res = T[0];
        int curr = T[0];
        for(int i = 1; i < N; i++)	{
        	if(T[i] < curr)	{
        		curr = T[i];
        	}
        	res += curr;
        }
        return res;
    }
	
	static int minimumJumps(int X, int[] arr, int k1, int k2, int start){
        
		Set<Integer> set = new HashSet<>();
        for(int x : arr)	{
        	set.add(x);
        	if(X == x || x == k1)
        		return 0;
        }
        
        int res = 1;
        int dest = k1 + start;
        boolean flag = true;
        while(dest < X)	{
        	dest += k1;
        	if(!set.contains(dest))	{
        		res++;
        		flag = true;
        		continue;
        	}
        	
        	if(flag)	{
        		dest -= (k1+k2);
        		if(dest > 0 && !set.contains(dest))	{
        			res++;
        			flag = false;
        			continue;
        		}
        		if(dest < 1)
        			return 0;
        		
        		//continue;
        	}
        	
        	return 0;
        }
        
        if(dest == X)
        	return res;
        
    	if(dest - k2 + k1 == X)	{
    		if(!set.contains(dest-k2) && !set.contains(dest+k1) && !set.contains(dest - k2 + k1))
    			return res+2;
    	}
    	if(dest - k2 == X && !set.contains(dest-k2))
    		return res + 1;
       
    	if(dest - k2 < 0)	{
    		while(dest - k2 < 0)	{
    			dest += k1;
        		res++;
        		if(set.contains(dest))
        			return 0;
    		}
    		if(set.contains(dest - k2))
    			return 0;
    		
    		res += 1;	//for dest-k2
    		return res + minimumJumps(X, arr, k1, k2, dest-k2);
    	}
        
        return 0;       
    }

	public static void main(String[] args) {
		/*
		 * int N = 3; int[] T = new int[] {5,5,5}; System.out.println(solve(N, T));
		 */
		int X = 15;
		int k1 = 10;
		int k2 = 25;
		int[] arr = new int[] {5};
		System.out.println(minimumJumps(X, arr, k1, k2, 0));
	}

}
