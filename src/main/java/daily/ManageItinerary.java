package daily;


import java.util.*;

/**
 * @author ZhangD
 * @since 2020-08-27
 * 232:重新安排行程
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 * <p>
 * 说明:
 * 如果存在多种有效的行程，你应该按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
 * 所有的机场都用三个大写字母表示（机场代码）。
 * 假定所有机票至少存在一种合理的行程。
 * <p>
 * <p>
 * 标签:深度优先搜索DFS,图
 * 本身是个欧拉图的问题,一笔画问题.
 * 可以把from和to理解为端点,from+to组装为一条线,转换为半/全欧拉图问题.假定三说明了必定为欧拉图,就省略了判断步骤.
 * <p>
 * 概念:
 * 强连通:两个端点间存在连通关系(对于有向图),即A->B有一条通路,B->A也有一条通路,称AB端点强连通
 * 有向图的极大强连通子图，称为强连通分量(strongly connected components),极大的意思为通过最多的路径依旧连通.
 * 特殊情况:对于不存在两两连通的端点,单个端点也可以视为强连通分量.
 * 半欧拉,全欧拉,对应欧拉通路和欧拉回路
 * 通路:所有边走一次,不重复,不要求回到原点
 * 回路:所有边走一次,不重复,且回到原点
 */
public class ManageItinerary {
    /**
     * 错误原因:没有把所有情况考虑进去(如果出现死胡同,但是贪心的认为这个是最好的,就会出现问题),只考虑了部分情况就开始答题,导致思路错误.
     *
     */
    // public List<String> findItinerary(List<List<String>> tickets) {
    //     List<String> list = new ArrayList<>();
    //     list.add("JFK");
    //     return findNext(tickets, list, "JFK");
    // }
    // private List<String> findNext(List<List<String>> tickets, List<String> list, String first ){
    //     int size = tickets.size();
    //     if(size ==0){
    //         return list;
    //     }else {
    //         List<String> nexts = tickets.stream().filter(lists -> lists.get(0).equals(first))
    //                 .map(lists -> lists.get(1)).sorted().collect(Collectors.toList());
    //         String next = nexts.get(0);
    //         tickets.removeIf(lists -> lists.get(0).equals(first) && lists.get(1).equals(next));
    //         list.add(next);
    //         findNext(tickets, list, next);
    //     }
    //     return list;
    // }

    /**
     * 正确解法:
     */
    /**
     * 将二维数组转为map,结构为<from,PriorityQueue<to>>,存储边
     */
    private Map<String, PriorityQueue<String>> map = new HashMap<>();

    /**
     * 存放行程的链表,存储点
     */
    private List<String> itinerary = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        tickets.forEach(strings -> {
            String from = strings.get(0);
            String to = strings.get(1);
            if (!map.containsKey(from)) {
                map.put(from, new PriorityQueue<>());
            }
            map.get(from).offer(to);
        });
        dfs2("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    /**
     * 对于第一层递归,是把该端点所有的边全部遍历,才把端点加入列表(算法中可以叫入栈).
     * 那么对于所有层递归都是这样的.可以看出是反向入栈
     */
    private void dfs1(String from) {
        /*
         * 如果map中含有from,且from对应的to的数量大于0,则移除最优to,把这个to当成from继续dfs
         */
        while (map.containsKey(from) && map.get(from).size() > 0) {
            String to = map.get(from).poll();
            dfs1(to);
        }
        itinerary.add(from);
    }

    /**
     * 递归转迭代
     */
    private void dfs2(String from) {
        Stack<String> stack = new Stack<>();
        stack.push(from);
        //外层循环
        while (!stack.isEmpty()) {
            PriorityQueue<String> priorityQueue;
            //内层循环,包括递归限制条件.
            while ((priorityQueue = map.get(stack.peek())) != null && priorityQueue.size() > 0) {
                stack.push(priorityQueue.poll());
            }
            itinerary.add(stack.pop());
            // //这里再倒序插入就使得不用再进行集合翻转了
            // itinerary.add(0, stack.pop());
        }
    }

    public static void main(String[] args) {
        List<List<String>> tickects = new ArrayList<>();
        List<String> ticket1 = new ArrayList<>();
        List<String> ticket2 = new ArrayList<>();
        List<String> ticket3 = new ArrayList<>();
        List<String> ticket4 = new ArrayList<>();
        List<String> ticket5 = new ArrayList<>();
        ticket1.add(0, "JFK");
        ticket1.add(1, "ABC");
        ticket2.add(0, "ABC");
        ticket2.add(1, "DEF");
        ticket3.add(0, "DEF");
        ticket3.add(1, "GHI");
        ticket4.add(0, "GHI");
        ticket4.add(1, "JKL");
        ticket5.add(0, "JKL");
        ticket5.add(1, "MNO");
        tickects.add(ticket1);
        tickects.add(ticket2);
        tickects.add(ticket3);
        tickects.add(ticket4);
        tickects.add(ticket5);
        List<String> itinerary = new ManageItinerary().findItinerary(tickects);
        System.out.println(itinerary);
    }
}
