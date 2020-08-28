package daily;

/**
 * @author ZhangD
 * @since 2020-08-28
 * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
 *
 * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
 *
 * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。
 *
 * 标签:字符串
 * 字符串的切割转为数组是耗时操作,不如直接操作字符串.因为牵扯到数组的创建,用流的话遍历更多.还是用switch case和直接操作字符串最好
 * switch case 的写法,swith(){case 条件: 操作; break; default:操作;}
 */
public class JudgeCircle {
    //流虽然很方便,但是比较慢.
    // public boolean judgeCircle(String moves) {
    //     String[] split = moves.split("");
    //     Long rs = Arrays.stream(split).filter("R"::equals).count();
    //     Long ls = Arrays.stream(split).filter("L"::equals).count();
    //     Long us = Arrays.stream(split).filter("U"::equals).count();
    //     Long ds = Arrays.stream(split).filter("D"::equals).count();
    //     return rs.equals(ls) && us.equals(ds);
    // }

    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        int length = moves.length();
        for (int i=0;i<length;i++) {
            char move = moves.charAt(i);
            switch (move) {
                case 'R':
                    x--;
                    break;
                case 'L':
                    x++;
                    break;
                case 'U':
                    y--;
                    break;
                case 'D':
                    y++;
                    break;
                default:
                    return false;
            }
        }
        return x == 0 && y == 0;
    }

    public static void main(String[] args) {
        String moves = "RLUDUDUDUDUDRLRLRLRL";
        boolean b = new JudgeCircle().judgeCircle(moves);
        System.out.println(b);
    }
}
