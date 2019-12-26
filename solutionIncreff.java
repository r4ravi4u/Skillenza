import java.util.Scanner;

public class solutionIncreff {
	static String distributeTShirt(int[] age, int len) {
		int[] result = new int[len];
		if (len < 2)
			return "1";
		int n = 1;
		result[0] = n;
		for (int i = 1; i < len; i++) {
			if (age[i] > age[i - 1]) {
				result[i] = ++n;
				continue;
			}
			if (age[i] == age[i - 1]) {
				n = 1;
				result[i] = n;
				continue;
			}
			n = 1;
			result[i] = n;
			if (result[i - 1] <= n) {
				int j = i - 1;
				while (j >= 0) {
					result[j]++;
					j--;
					if (j >= 0 && age[j] <= age[j + 1])
						break;
					if (j >= 0 && age[j] > age[j + 1] && result[j] > result[j + 1])
						break;
				}
			}
		}
		StringBuilder str = new StringBuilder();
		for (int i : result)
			str.append(i + " ");
		return str.toString().trim();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		try {
			for (int itr = 0; itr < t; itr++) {
				int len = sc.nextInt();
				int[] age = new int[len];
				if (len < 2)	{
					for (int i = 0; i < len; i++)
						age[i] = sc.nextInt();
					
					System.out.println("1");
					continue;
				}				
				
				int[] result = new int[len];
				int n = 1;
				result[0] = n;
				age[0] = sc.nextInt();
				for (int i = 1; i < len; i++) {
					age[i] = sc.nextInt();
					if (age[i] > age[i - 1]) {
						result[i] = ++n;
						continue;
					}
					n = 1;
					result[i] = n;
					if (age[i] == age[i - 1])
						continue;
					
					if (result[i - 1] <= n) {
						int j = i - 1;
						while (j >= 0) {
							result[j]++;
							j--;
							if (j >= 0 && age[j] <= age[j + 1])
								break;
							if (j >= 0 && age[j] > age[j + 1] && result[j] > result[j + 1])
								break;
						}
					}
					
				}
				StringBuilder str = new StringBuilder();
				for (int i : result)
					str.append(i + " ");
				System.out.println(str.toString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
