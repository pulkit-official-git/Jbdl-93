package multithreadedMergeSort;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService ex = Executors.newCachedThreadPool();
        List<Integer> arr = List.of(4,5,1,7,8,9,3,2);
        MergeSort mergeSort = new MergeSort(arr,ex);
        Future<List<Integer>> ans =ex.submit(mergeSort);

        System.out.println(ans.get());
    }
}
