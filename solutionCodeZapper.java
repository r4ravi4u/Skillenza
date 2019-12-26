import java.util.Scanner;
public class solutionCodeZapper {	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-- > 0) {
			long rows = sc.nextInt();
			long cols = sc.nextInt();
			long r = sc.nextInt();
			long c = sc.nextInt();
			long k = sc.nextInt();		
			System.out.println((rows * cols) - ((r+k >= rows ? rows : r+k) - (r-k <= 1 ? 1 : r-k) + 1) * ((c+k >= cols ? cols : c+k) - (c-k <= 1 ? 1 : c-k) + 1));
		}
		sc.close();
	}
}
