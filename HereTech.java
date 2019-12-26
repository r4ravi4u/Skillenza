package practice.test;

/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;

public class HereTech {
  
  private static class ListUtil implements Comparable<ListUtil>	{
		
    List<Integer> list;

    ListUtil(int size, int n)	{
      list = new ArrayList<>(Collections.nCopies(size, n));
    }

    ListUtil(List<Integer> list)	{
      this.list = new ArrayList<>(list);
    }

    @Override
    public String toString()	{
      StringBuilder str = new StringBuilder();
      str.append('[');
      boolean flag = false;
      for(int x : list)	{
        if(flag)
          str.append(',');
        str.append(x);
        flag = true;
      }
      str.append(']');
      return str.toString();
    }

    @Override
    public int compareTo(ListUtil o) {
      for(int i = 0; i < list.size(); i++) {
        if(list.get(i) == o.list.get(i))
          continue;

        return list.get(i) - o.list.get(i);
      }
      return 0;
    }
  }
  
  private static boolean dpWays(int[] tanks, int qty) {
    int dp[] = new int[qty + 1];
    dp[0] = 1;
    int len = tanks.length;

    for (int i = 0; i < len; i++) {
      for (int j = tanks[i]; j < qty; j++) {
        dp[j] += dp[j - tanks[i]];
        if (dp[j] > 1)
          return true;
      }
    }
    return false;
  }
  
  private static void getAllWays(int[] tanks, int qty, int index, ListUtil each, List<ListUtil> result,
			Map<Integer, Integer> map) {
    for (int i = index; i < tanks.length; i++) {
      int target = qty - tanks[i];

      ListUtil tempRes = new ListUtil(each.list);
      int idx = map.get(tanks[i]);
      tempRes.list.set(idx, tempRes.list.get(idx)+1);

      if (target < 0)
        break;

      if (target == 0) {
        result.add(tempRes);
        break;
      } else
        getAllWays(tanks, target, i, tempRes, result, map);
    }
  }
  
  private static String listToString(List<ListUtil> list)	{
    StringBuilder str = new StringBuilder();
    for(ListUtil res : list) {
      str.append(res);
    }
    return str.toString();
  }
  
  public static void main(String[] args) throws IOException {
    //Way 1 to read from Console
    /*InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line = in.readLine();*/
    
    //Way 2 to read from Console
    Scanner sc = new Scanner(System.in);
    String line = sc.nextLine();

    String[] tokens = line.split(",");
    int len = tokens.length;

    int[] tanks = new int[len - 1];
    for (int i = 0; i < len - 1; i++) {
      String curr = tokens[i];
      if (i == 0) {
        if (len == 2) // means there is only single element like -> (xy)
          curr = curr.substring(1, curr.length() - 1);
        else
          curr = curr.substring(1);
        tanks[i] = Integer.parseInt(curr);
        continue;
      }
      if (i == len - 2) {
        curr = curr.substring(0, curr.length() - 1);
        tanks[i] = Integer.parseInt(curr);
        continue;
      }
      tanks[i] = Integer.parseInt(curr);
    }
    int qty = Integer.parseInt(tokens[len - 1].substring(1)); // removal of space

    // tanks array contains individual quantity (sorted manner), qty contains final qty to be filled
    // Lets find if any solution exists or not, if Not min qty to be added we need to output
    // we are doing this as it will lead to better performance result in case solution does not exist
    boolean flag = dpWays(tanks, qty);
    if (!flag) {
      // means no solution exists
      int add1 = tanks[0] * 2 - qty;
      int add2 = tanks[0] + tanks[1] - qty;

      System.out.println(add1 < add2 ? add1 : add2);
    }

    // Here now we are sure that solution exists and hence we need to give solution

    Map<Integer, Integer> map = new HashMap<>(); // used to store index to be used while building result
    for (int i = 0; i < tanks.length; i++)
      map.put(tanks[i], i);

    List<ListUtil> result = new ArrayList<>();
    ListUtil each = new ListUtil(tanks.length, 0);
    
    getAllWays(tanks, qty, 0, each, result, map);

    //We need to sort result lists and hence made ListUtil class
    Collections.sort(result);

    System.out.println(listToString(result));

    sc.close();
  }
}