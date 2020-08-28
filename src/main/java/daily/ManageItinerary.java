package daily;

import test.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangD
 * @since 2020-08-27
 * 重新安排行程
 *
 * 标签:深度优先搜索DFS,图
 * 本身是个欧拉图的问题,一笔画问题.
 */
public class ManageItinerary {
    /**
     * 错误原因:没有把所有情况考虑进去,只考虑了部分情况就开始答题,导致思路错误.
     * @param args
     */
    // public List<String> findItinerary(List<List<String>> tickets) {
    //     List<String> list = new ArrayList<>();
    //     list.add("JFK");
    //     return findNext(tickets, list, "JFK");
    // }
    // private List<String> findNext(List<List<String>> tickets, List<String> list, String first){
    //     int size = tickets.size();
    //     if(size ==0){
    //         return list;
    //     }else {
    //         List<String> nexts = tickets.stream().filter(lists -> lists.get(0).equals(first))
    //                 .map(lists -> lists.get(1)).collect(Collectors.toList());
    //         String next = nexts.get(0);
    //         tickets.removeIf(lists -> lists.get(0).equals(first) && lists.get(1).equals(next));
    //         list.add(next);
    //         findNext(tickets, list, next);
    //     }
    //     //这个return好像没有意义
    //     return list;
    // }



    public static void main(String[] args) {
        List<List<String>> tickects = new ArrayList<>();
        List<String> ticket1 = new ArrayList<>();
        List<String> ticket2 = new ArrayList<>();
        List<String> ticket3 = new ArrayList<>();
        List<String> ticket4 = new ArrayList<>();
        List<String> ticket5 = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        ticket1.add(0,"JFK");
        ticket1.add(1,"ABC");
        ticket2.add(0,"ABC");
        ticket2.add(1,"DEF");
        ticket3.add(0,"DEF");
        ticket3.add(1,"GHI");
        ticket4.add(0,"GHI");
        ticket4.add(1,"JKL");
        ticket5.add(0,"JKL");
        ticket5.add(1,"MNO");
        tickects.add(ticket1);
        tickects.add(ticket2);
        tickects.add(ticket3);
        tickects.add(ticket4);
        tickects.add(ticket5);
        // List<String> itinerary = new ManageItinerary().findItinerary(tickects);
        // System.out.println(itinerary);
    }
}
