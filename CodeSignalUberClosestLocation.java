import java.util.*;

public class CodeSignalUberClosestLocation {

	private static class DistanceAddress implements Comparable<DistanceAddress> {
		double dist;
		String word;

		public DistanceAddress(double dist, String word) {
			super();
			this.dist = dist;
			this.word = word;
		}

		@Override
		public int compareTo(DistanceAddress o) {
			return Double.compare(dist, o.dist);
		}

		@Override
		public String toString() {
			return dist + " : " + word;
		}
	}

	private static boolean isDistance1(String s1, String s2) {
		
		if(s1 == null && s2 == null)
	        return true;
    
        if(s2 == null)
            return false;
		
		int m = s1.length(), n = s2.length();

		if (Math.abs(m - n) > 1)
			return false;

		int count = 0;

		int i = 0, j = 0;
		while (i < m && j < n) {
			if (s1.charAt(i) != s2.charAt(j)) {
				if (count == 1)
					return false;
				
				if (m > n)
					i++;
				else if (m < n)
					j++;
				else {
					i++;
					j++;
				}
				count++;
			}

			else	{
				i++;
				j++;
			}
		}

		if (i < m || j < n)
			count++;

		return count == 1;
	}

	String closestLocation(String address, int[][] objects, String[] names) {

		double[] dist = new double[objects.length];
		for (int i = 0; i < objects.length; i++) {
			if (objects[i].length == 2) {
				dist[i] = Math.sqrt(Math.pow(objects[i][0], 2) + Math.pow(objects[i][1], 2));
				continue;
			}
			// means their is line parallel to any 1 axis

			// if parallel to X axis (Horizontal line) means objects[i][1] == objects[i][3]
			if (objects[i][1] == objects[i][3]) {
				if ((objects[i][0] <= 0 && objects[i][2] >= 0) || (objects[i][2] <= 0 && objects[i][0] >= 0))
					dist[i] = Math.abs(objects[i][1]);
				else {
					int x;
					if (objects[i][0] <= 0 && objects[i][2] <= 0) // Both Negative Side
						x = objects[i][0] > objects[i][2] ? objects[i][0] : objects[i][2];
					else // Both Positive Side
						x = objects[i][0] < objects[i][2] ? objects[i][0] : objects[i][2];

					dist[i] = Math.sqrt(Math.pow(x, 2) + Math.pow(objects[i][1], 2));
				}
				continue;
			}

			// if parallel to Y axis (Vertical line) means objects[i][0] == objects[i][2]
			if ((objects[i][1] <= 0 && objects[i][3] >= 0) || (objects[i][3] <= 0 && objects[i][1] >= 0))
				dist[i] = Math.abs(objects[i][0]);
			else {
				int y;
				if (objects[i][1] <= 0 && objects[i][3] <= 0) // Both Negative Side
					y = objects[i][1] > objects[i][3] ? objects[i][1] : objects[i][3];
				else // Both Positive Side
					y = objects[i][1] < objects[i][3] ? objects[i][1] : objects[i][3];

				dist[i] = Math.sqrt(Math.pow(objects[i][0], 2) + Math.pow(y, 2));
			}
		}

		List<DistanceAddress> list = new ArrayList<>(dist.length);
		for (int i = 0; i < dist.length; i++)
			list.add(new DistanceAddress(dist[i], names[i]));

		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			/*
			 * Check for each possible combination : 1. the typed in address is identical to
			 * the prefix of the object's address 2. they differ only by one symbol 3.
			 * user's input has one extra symbol 4. user's input has one missing symbol
			 */
			DistanceAddress d = list.get(i);
			// 1. the typed in address is identical to the prefix of the object's address
			if (d.word.startsWith(address))
				return d.word;

			int len = address.length();
			int len1 = d.word.length();
			String start, start1 = null, start2 = null;
			if (len1 > len)	{
				start = d.word.substring(0, len-1).toLowerCase().trim();
				start1 = d.word.substring(0, len).toLowerCase().trim();
				start2 = d.word.substring(0, len+1).toLowerCase().trim();
			}				
			else if(len-len1 < 2)	{
				start = d.word.substring(0, len-1).toLowerCase().trim();
				start1 = d.word.substring(0, len).toLowerCase().trim();
			}
			else
				start = d.word.toLowerCase().trim();

			if (isDistance1(address.toLowerCase(), start) || isDistance1(address.toLowerCase(), start1) || isDistance1(address.toLowerCase(), start2))
				return d.word;
		}
		return null;
	}

	public static void main(String[] args) {
		String address = "Location";//"Lobction";//Lcation";
		int[][] objects = new int[][] {{1,1}, {3,3}};//, {2,1,2,4}, {-4,-3,6,-3}};
		String[] names = {	"Locati", 
				 			"Location"};//{ "loocationnnn", "lcationaaaa"};

		System.out.println(new CodeSignalUberClosestLocation().closestLocation(address, objects, names));

	}

}
