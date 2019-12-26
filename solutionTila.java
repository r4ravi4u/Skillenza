import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class solutionTila {
	
	private static String nextTime(String str) {
		String[] time = str.split(":");
		final String res = "INVALID";
		if (time.length != 3)
			return res;

		List<Integer> list = new ArrayList<>();
		int[] hms = new int[3];
		int idx = 0;
		int val = 0;
		int factor = 3600;
		for (int i = 0; i < time.length; i++) {
			for (char ch : time[i].toCharArray()) {
				try {
					list.add(Character.getNumericValue(ch));
				} catch (NumberFormatException e) {
					return res;
				}
			}
			hms[i] = list.get(idx) * 10 + list.get(idx + 1);
			val += hms[i] * factor;
			factor /= 60;
			idx += 2;
		}
		
		boolean flag = true;
		for (int x : hms) {
			if (!checkValid(x, flag))
				return res;
			flag = false;
		}
		
		List<Integer> backup = new ArrayList<>(list);
		int[] result = new int[6];
		int[] base = new int[] { 36000, 3600, 600, 60, 10, 1 };
		for (int i = 1; i <= 86400; i++) {
			int next = val + i;
			if(next > 86400)
				return str;
			int index = 0;
			while (index < 6) {
				int digit = next / base[index];
				if (list.isEmpty() || !list.contains(digit))
					break;
				result[index] = digit;
				list.remove((Object) digit);
				next = next % base[index];
				index++;
			}
			if (index == 6) {
				break;
			} 
			else {
				result = null;
				result = new int[6];
				list.clear();
				list.addAll(backup);
			}
		}
		return String.valueOf(result[0]) + String.valueOf(result[1]) + ":" + String.valueOf(result[2])
				+ String.valueOf(result[3]) + ":" + String.valueOf(result[4]) + String.valueOf(result[5]);
	}
	
	private static boolean checkValid(int t, boolean flag) {
		if (flag)
			if (t < 0 || t > 23)
				return false;
		if (t < 0 || t > 59)
			return false;
		return true;
	}
	
	private static class Result {
		int diff;
		int val;
		int[] res;
		
		Result(int diff, int val, int[] res) {
			this.diff = diff;
			this.val = val;
			this.res = res;
		}
	}
	private static void permute(int[] arr, int index, Result dc) {
		if(arr[0] > 2 || (arr[0] == 2 && arr[1] > 3) || arr[2] > 5 || arr[4] > 5)
			return;
		
		if (index >= arr.length - 1) {
			int factor = 3600;
			int currVal = 0;
			for(int i = 0; i < arr.length;)	{
				currVal += (arr[i]*10 + arr[i+1])*factor;
				i += 2;
				factor /= 60;
			}
			if(currVal > dc.val)	{
				int currDiff = currVal - dc.val;
				if(currDiff < dc.diff)	{
					dc.diff = currDiff;
					dc.res = arr.clone();
				}
			}
			return;
		}

		for (int i = index; i < arr.length; i++) {
			int t = arr[index];
			arr[index] = arr[i];
			arr[i] = t;

			permute(arr, index + 1, dc);

			t = arr[index];
			arr[index] = arr[i];
			arr[i] = t;
		}
	}
	
	private static String nextClosestTime(String str) {
		String[] time = str.split(":");
		final String res = "INVALID";
		if (time.length != 3)
			return res;

		int[] hhmmss = new int[6];
		int[] hms = new int[3];
		int idx = 0;
		int val = 0;
		int factor = 3600;	//to get value in terms of seconds (86400 at max)
		for (int i = 0; i < time.length; i++) {
			for (char ch : time[i].toCharArray()) {
				try {
					hhmmss[idx++] = Character.getNumericValue(ch);
				} catch (NumberFormatException e) {
					return res;
				}
			}
			hms[i] = hhmmss[idx-2] * 10 + hhmmss[idx-1];
			val += hms[i] * factor;
			factor /= 60;
		}
		
		boolean flag = true;
		for (int x : hms) {
			if (!checkValid(x, flag))
				return res;
			flag = false;
		}
		
		Result result = new Result(Integer.MAX_VALUE, val, hhmmss);
		permute(hhmmss, 0, result);
		
		int[] temp = result.res;
		StringBuilder s = new StringBuilder();
		for(int i = 0;i<temp.length;)	{
			s.append(temp[i++]);
			s.append(temp[i++]);
			if(i < temp.length - 1)
				s.append(":");
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		try {
			for (int n = 0; n < t; n++) {
				String str = sc.next();
				System.out.println(nextTime(str));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}