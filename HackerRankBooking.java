import java.util.*;

public class HackerRankBooking {
	
	private static class Data	{
		float score;
		int freq;
		
		Data(float score, int freq)	{
			this.score = score;
			this.freq = freq;
		}	
		
		@Override
		public String toString()	{
			return score + " : " + freq;
		}
	}
	
	private static class Entry implements Comparable<Entry>{
		int id;
		float score;
		
		Entry(int id, float score)	{
			this.id = id;
			this.score = score;
		}
		
		@Override
		public int compareTo(Entry o) {
			int x = Float.compare(score, o.score);
			
			if(x == 0)
				return id - o.id;	
			
			return -1*(x);
		}
		
		@Override
		public String toString()	{
			return id + " : " + score;
		}
	}
    // Complete the best_hotels function below.
    static void best_hotels() {
    	Scanner sc = new Scanner(System.in);
    	
    	Map<Integer, Data>	map = new HashMap<>();
    	int n = sc.nextInt();
    	while(n-- > 0)	{
    		int id = sc.nextInt();
    		float score = sc.nextFloat();
    		if(map.containsKey(id))	{
    			Data d = map.get(id);
    			d.score = (score + d.score*d.freq) / (d.freq + 1);
    			d.freq++;
    			continue;
    		}
    		map.put(id, new Data(score, 1));
    	}
    	//System.out.println(map);
    	List<Entry> list = new ArrayList<>(map.size());
    	for(Map.Entry<Integer, Data> e : map.entrySet()) {
    		int key = e.getKey();
    		Data val = e.getValue();
    		
    		Entry temp = new Entry(key, val.score);
    		list.add(temp);
    	}
    	
    	Collections.sort(list);
    	System.out.println(list);
    	
    	sc.close();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		best_hotels();
	}

}
