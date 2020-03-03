import java.util.Scanner;

public class UnlimitedInteger {
    private String value;

    public UnlimitedInteger(String value) {
        if (!value.matches("[+-]?\\d+")) {
            throw new NumberFormatException();
        }
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public UnlimitedInteger plus(UnlimitedInteger op) {
        String one = this.toString();
        String two = op.toString();
        String res = Main.plus(one,two);
        return new UnlimitedInteger(res);
    }

    public UnlimitedInteger times(UnlimitedInteger op) {
        String one = this.toString();
        String two = op.toString();
        String res = Multi.times(one, two);
        return new UnlimitedInteger(res);
    }


    public static UnlimitedInteger polynomial(UnlimitedInteger a, UnlimitedInteger b, UnlimitedInteger c, UnlimitedInteger x) {
        return a.times(x.times(x)).plus(b.times(x)).plus(c);
    }



    public static void main(String[] args) {
        System.out.println("Please enter the values of your Polonomyals as follows: ax2 + bx + c");
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter please a");
        String a = reader.nextLine();
        UnlimitedInteger aU = new UnlimitedInteger(a);
        System.out.println("Enter please b");
        String b = reader.nextLine();
        UnlimitedInteger bU = new UnlimitedInteger(b);
        System.out.println("Enter please c");
        String c = reader.nextLine();
        UnlimitedInteger cU = new UnlimitedInteger(c);
        System.out.println("Enter please x");
        String x = reader.nextLine();
        UnlimitedInteger xU = new UnlimitedInteger(x);

        System.out.println(polynomial(aU, bU, cU, xU));
    }
}