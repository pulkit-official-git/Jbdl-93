package multithreadedMergeSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MergeSort implements Callable<List<Integer>> {
    List<Integer>arr;
    ExecutorService ex;

    public MergeSort(List<Integer> arr,ExecutorService ex) {
        this.arr = arr;
        this.ex = ex;
    }

    @Override
    public List<Integer> call() throws Exception {

        if(arr.size()<=1){
            return arr;
        }

        int mid = arr.size()/2;

        List<Integer>left = new ArrayList<>();
        List<Integer>right = new ArrayList<>();
        for (int i = 0; i < mid; i++) {
            left.add(arr.get(i));
        }
        for (int i = mid; i < arr.size(); i++) {
            right.add(arr.get(i));
        }

        MergeSort m1 = new MergeSort(left,ex);
        MergeSort m2 = new MergeSort(right,ex);

        Future<List<Integer>> l1 =ex.submit(m1);
        Future<List<Integer>> l2 = ex.submit(m2);

        left = l1.get();
        right= l2.get();

        List<Integer>ans = new ArrayList<>();
        int i=0,j=0;
        while(i<left.size() && j<right.size()){
            if(left.get(i)<right.get(j)){
                ans.add(left.get(i));
                i++;
            }
            else{
                ans.add(right.get(j));
                j++;
            }
        }

        while(i<left.size()){
            ans.add(left.get(i++));
        }

        while(j<right.size()){
            ans.add(right.get(j++));
        }
        return ans;
    }
}
