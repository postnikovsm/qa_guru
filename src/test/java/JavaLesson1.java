public class JavaLesson1 {
    public static void main(String[] args) {
        int a = 5;
        int b = 4;
        double c = 4.0;

        System.out.println(a+b);
        System.out.println(a-b);
        System.out.println(a*b);
        System.out.println(a/b);

        System.out.println(a+c);
        System.out.println(b-c);

        boolean result1 = a > b;
        boolean result2 = a < b;
        boolean result3 = a >= c;
        boolean result4 = b <= c;

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }
}
