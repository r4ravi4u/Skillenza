import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class ListNode {
	 public int val;
	 public ListNode next;
	 ListNode(int x) { val = x; next = null; }
}

public class solutionCMS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			String str = sc.next();
			boolean flag = true;
			int sum = 0;
			for (int i = str.length() - 1; i >= 0; i--) {
				char ch = str.charAt(i);
				if (ch != '0') {
					int num = ch - '0';
					if (flag) {
						num = num * 2;
						if (num >= 10)
							num = 1 + num % 10;
					}
					sum += num; 
				}
				flag = !flag;
			}
			if (sum%10 == 0)
				System.out.println("0");
			else
				System.out.println(10 - sum%10);
		}
		sc.close();
	}
}
