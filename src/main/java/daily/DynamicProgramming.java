package daily;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangD
 * @since 2021-01-04
 * 动态规划相关题目
 */
@Slf4j
public class DynamicProgramming {

    /**
     * 1.Fibonacci sequence 斐波那契数列
     * 通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和
     * 求数列的第n个数的值
     */
    static class Fibonacci {
        public static void main(String[] args) {
            Long start = System.currentTimeMillis();
            System.out.println(fib(3));
            Long end = System.currentTimeMillis();
            System.out.println("耗时:" + (end - start));
        }

        public static long fib(int n) {
            /**
             * 暴力递归 n=45 耗时5s左右,指数级别. O(2^n)
             */
            // if (n == 0) {
            //     return 0;
            // }
            // if (n == 1) {
            //     return 1;
            // }
            // return fib(n - 1) + fib(n - 2);
            /**
             * 带备忘录的递归 n =45 耗时1ms左右. O(n),降维打击,自顶向下
             */
            // long[] fibArray = new int[n];
            // fibArray[1] = 1;
            // return helper(fibArray, n);
            /**
             * dpTable的迭代,自底向上 和备忘录差不多1ms,优点是迭代之与递归的优点.
             */
            // long[] fibArray = new long[n];
            // fibArray[1] = 1;
            // return dpHelper(fibArray, n);
            /**
             * 状态压缩 降低空间复杂度.时间复杂度没有上升,但是常数其实大了一点.,因为时间复杂度变成常数,所以不再需要数组.
             */
            return dpReduceHelper(n);
        }

        /**
         * 备忘录辅助函数
         *
         * @param fibArray 备忘录
         * @param n        状态
         * @return 状态值
         */
        static long helper(long[] fibArray, int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            if (fibArray[n] != 0) {
                return fibArray[n];
            }
            //自顶而下
            fibArray[n] = helper(fibArray, n - 1) + helper(fibArray, n - 2);
            return fibArray[n];
        }

        /**
         * dpTable辅助函数
         *
         * @param fibArray dpTable
         * @param n        状态
         * @return 状态值
         */
        static long dpHelper(long[] fibArray, int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            //自底而上
            for (int i = 2; i <= n; i++) {
                fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
            }
            return fibArray[n];
        }

        /**
         * 经过状态压缩后的dpTable辅助函数
         *
         * @param n 状态
         * @return 状态值
         */
        static long dpReduceHelper(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            long pre = 0L;
            long next = 1L;
            //自底而上
            for (int i = 2; i <= n; i++) {
                //状态压缩,因为用到的实际就两个值,可以直接用常数,不用数组.
                long sum = pre + next;
                pre = next;
                next = sum;
            }
            return next;
        }
    }

    /**
     * 2.凑零钱问题
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 每种硬币的数量是无限的。
     */
    static class CoinChange {
        public int coinChange(int[] coins, int amount) {
            //base case
            if (amount == 0) {
                return 0;
            }
            // Integer n = amount;
            // List<Integer> dpList = new ArrayList<>(n);
            // dpList.forEach(origin -> origin = amount + 1);
            //
            int i = min()

        }
    }
}
