import java.util.*;

public class HackerEarthCodeArena {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int len = sc.nextInt();
		String str = sc.next();
		
		int q = sc.nextInt();
		while(q-- > 0) {
			int type = sc.nextInt();
			int l = sc.nextInt();
			int r = -1;
			if(type == 0)
				r = sc.nextInt();
			
			if(r < 0)	{
				if(str.charAt(l) == '0')	{
					StringBuilder myString = new StringBuilder(str);
					myString.setCharAt(l, '1');
					str = myString.toString();
				}
				continue;
			}
			
			String bin = str.substring(l,r+1);
			int rem = 0;
			for(char ch : bin.toCharArray()) {
				if(ch == '1') {
					rem = (rem*2+1) % 3;
				}
				if(ch == '0') {
					rem = (rem*2) % 3;
				}
			}
			
			System.out.println(rem%3);
			
		}
		

	}

}
