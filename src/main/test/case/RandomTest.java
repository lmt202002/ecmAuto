/**
 * Created by lvshr on 2017/5/9.
 */

public class RandomTest {
    public static void main(String[] args) {
        double max=2;
        double min=1;
        double s = (min+Math.random()*(max-min))*100;
        int money=(int)s;
        System.out.println(money);
    }
}