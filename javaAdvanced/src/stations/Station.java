package stations;

import java.util.Arrays;

public class Station {

    public int minPlatform(int[] arr, int[] dep) {

        Arrays.sort(arr);
        Arrays.sort(dep);
        int i = 0;
        int j = 0;
        int ans=Integer.MIN_VALUE;
        int count=0;
        while (i < arr.length) {
            if (arr[i] <= dep[j]) {
                count++;
                i++;
            }
            else{
                count--;
                j++;

            }
            ans = Math.max(ans, count);
        }
        return ans;

    }
}
