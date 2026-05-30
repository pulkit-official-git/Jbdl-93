package lambda;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamClient {


    /*
    * Intermediate:-
    * 1. it will return the reference
    * 2. limit(), filter(), map
    * 3. to execute intermediate methods completely we need a terminal method
    * 4. we can use multiple intermediate methods on top of another
    *
    *
    * Terminal:-
    * 1. it will return the exact output
    * 2. ex:- count(), collect(), findfirst(), reduce()
    * 3. after you implement a terminal method, you cannot again use the same
    *
    *
    * H.W.
    *
    * parallel streams (threads)
    * flatmap
    * [[1,2,3],
    * [3,4,5,6,7,8,9],
    * [4,5,6,7,78,8,8,8,8,8]
    * [1,2,3,3,4,5,6,6,7,8,9,9,9,]
    *
    *
    * */
    public static void main() {

        List<Integer> ls = List.of(8,7,1,3,4,6,9,5);


//        Integer ans = Integer.MAX_VALUE;
//        for(int i=0;i<ls.size();i++){
//            ans = Math.min(ans,ls.get(i));
//        }


        System.out.println(ls.stream());

        System.out.println(ls.stream().limit(5).distinct().count());

        List<Integer> evenNos = ls
                .stream()
                .filter((e)->e%2==0)
                .collect(Collectors.toList());

        System.out.println(evenNos);

        List<Integer> evenSquaredNos = ls
                .stream()
                .filter((e)->e%2==0)
                .map(x->x*x)  //very important
                .collect(Collectors.toList());

        System.out.println(evenSquaredNos);


        List<Integer> evenSquaredSortedNos = ls
                .stream()
                .filter(e->e%2==0)
                .map(x->x*x)
                .sorted((a,b)->a-b)
                .collect(Collectors.toList());

        System.out.println(evenSquaredSortedNos);



        Optional<Integer> first = ls
                .stream()
                .filter(e->e%2==0)
                .map(x->x*x)
                .sorted((a,b)->a-b)
                .findFirst();

        System.out.println(first.get());


        Integer sum = ls
                .stream()
                .filter(e->e%2==0)
                .map(x->x*x)
                .sorted((a,b)->a-b)
                .reduce(0,(a,b)->a+b);

        System.out.println(sum);

        Integer smallestNo = ls
                .stream()
                .filter(e->e%2==0)
                .map(x->x*x)
                .sorted((a,b)->a-b)
                .reduce(Integer.MAX_VALUE,(a,b)->Math.min(a,b));

        System.out.println(smallestNo);


        Integer largestNo = ls
                .stream()
                .filter(e->e%2==0)
                .map(x->x*x)
                .sorted((a,b)->a-b)
                .reduce(Integer.MIN_VALUE,(a,b)->Math.max(a,b));


        System.out.println(largestNo);


    }
}
