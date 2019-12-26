import java.util.Scanner;

public class solutionMagna {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int[] v = new int[n];
			for (int i = 0; i < n; i++)
				v[i] = sc.nextInt();

			int high = 0;
			int spill = 0;
			for (int i = 0; i < n; i++) {
				high += sc.nextInt();
				if (high > v[i]) {
					spill += high - v[i];
					high = v[i];
				}
			}
			System.out.println(high + " " + spill);
		}
		sc.close();
	}
}
