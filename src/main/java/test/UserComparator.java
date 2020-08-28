package test;

import test.entity.Student;

import java.util.Comparator;

/**
 * @author ZhangD
 * @since 2020-08-28
 * 可以这么写,但是实际更多的情况是用匿名内部类或lambda表达式来表示
 * list<Student>().sort((o1, o2) -> o1.get().comparaTo(o2.get());
 *
 */
public class UserComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
       return  o1.getScore().compareTo(o2.getScore());
    }
}
