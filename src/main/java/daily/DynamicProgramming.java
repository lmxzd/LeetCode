package daily;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
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
             * 暴力递归 n=45 耗时5s左右,指数级别. 时间复杂度.O(2^n),空间复杂度O(1)
             */
            // if (n == 0) {
            //     return 0;
            // }
            // if (n == 1) {
            //     return 1;
            // }
            // return fib(n - 1) + fib(n - 2);
            /**
             * 带备忘录的递归 n =45 耗时1ms左右. O(n),空间复杂度O(n),降维打击,自顶向下
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
             * 状态压缩 降低空间复杂度.时间复杂度没有上升,但是常数其实大了一点.,因为时间复杂度变成常数,所以不再需要数组.时O(n),空O(1)
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
     * 每种硬币的数量是无限的。所以n和n-i(状态)的状态之间互不影响,说明具有最优子结构.符合动态规划问题.
     */
    static class CoinChange {
        public static void main(String[] args) {
            int[] coins = {1, 2, 5, 205};
            int amount = 1000000;
            Long start = System.currentTimeMillis();
            int i = coinChange(coins, amount);
            System.out.println(i);
            Long end = System.currentTimeMillis();
            System.out.println("耗时:" + (end - start));

        }


        public static int coinChange(int[] coins, int amount) {
            /**
             * 带备忘录的递归解法.自顶而下,amount稍微大一点就栈溢出了StackOverflow 时间复杂度为O(kn)k是coins的length,n是amount的值
             * 空间为O(n)
             */
            // int[] dpArray = new int[amount + 1];
            // Arrays.fill(dpArray, Integer.MAX_VALUE);
            // return dpHelper(coins, amount, dpArray);
            /**
             * dpTable自底而上的迭代解法,可以看出迭代代码更少,而且不会出现栈溢出.时O(kn),空O(n)
             */
            int[] dpArray = new int[amount + 1];
            Arrays.fill(dpArray, amount + 1);
            return dpTableHelper(coins, amount, dpArray);
        }

        /**
         * dpTable的辅助函数
         *
         * @param coins   硬币种类集合
         * @param amount  钱总数
         * @param dpArray 辅助dp数组
         * @return 当前状态的状态值.
         */
        static int dpTableHelper(int[] coins, int amount, int[] dpArray) {
            dpArray[0] = 0;
            //外层循环控制可以输出的dp[i]
            for (int i = 0; i < amount + 1; i++) {
                //内层循环控制最小值.多个比较最小值,用这种方式两两比较,或者用排序的方法来做也可以.
                for (int coin : coins) {
                    if (i - coin < 0) {
                        continue;
                    }
                    //这里记录值,-1的就没有记录,直接跳出,还是原来的值.
                    dpArray[i] = Math.min(dpArray[i], dpArray[i - coin] + 1);
                }
            }
            int res = dpArray[amount];
            return (res == amount + 1) ? -1 : res;
        }

        /**
         * 备忘录的辅助函数
         *
         * @param coins   硬币种类集合
         * @param amount  钱总数
         * @param dpArray 辅助dp数组
         * @return 当前状态的状态值.
         */
        static int dpHelper(int[] coins, int amount, int[] dpArray) {
            if (amount == 0) {
                return 0;
            }
            int min = Integer.MAX_VALUE;
            //备忘录取值
            if (dpArray[amount] != Integer.MAX_VALUE) {
                return dpArray[amount];
            }
            for (int coin : coins) {
                if (amount - coin < 0) {
                    continue;
                }
                //这一步只是在递归寻找上一子问题的值
                dpArray[amount - coin] = dpHelper(coins, amount - coin, dpArray);
                //如若子问题无值,跳出循环找别的同级子问题.
                if (dpArray[amount - coin] == -1) {
                    continue;
                }
                //比较各子问题的值,这里比较的实际是当前子问题父问题的值,因为父问题实际就是当前子问题+1
                min = Math.min(min, dpArray[amount - coin] + 1);
            }
            //这一步是记录求出来的当前状态的值.此时-1也会被记录.
            dpArray[amount] = min;
            int res = dpArray[amount];
            return res == Integer.MAX_VALUE ? -1 : res;
        }
    }

    /**
     * 3.分割回文串(131)
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     * 返回 s 所有可能的分割方案。回文串:前后读取顺序一致.
     */
    static class SplitPalindromeString {
        public static void main(String[] args) {

            String s = "char1";
            char c = s.charAt(0);
            String[] split = s.split("");
            int i;
            for (i = 0; i < split.length; i++) {
                System.out.println(i);
            }
            System.out.println(i);
            System.out.println(Arrays.toString(split));
        }

        public List<List<String>> partition(String s) {
            List result = new ArrayList();
            boolean[][] status = new boolean[s.length()][s.length()];
            Arrays.fill(status, true);
            String[] baseArray = s.split("");
            int length = baseArray.length;
            //转换为半矩阵
            for (int i = 0; i < length; i++) {
                for (int j = length - i; j > i; j--) {
                    status[i][j] = baseArray[i].equals(baseArray[j]);
                }
            }
            //半矩阵上的点,做垂线
            int cursor = 0;
            for (double i = 0, j = 0; i < status.length && j < status.length; i = i + 0.5, j = j + 0.5) {
                if (i == cursor + 0.5) {

                }
            }
        }
    }
}
