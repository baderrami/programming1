
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter please the first number");
        String op1 = reader.nextLine();
        System.out.println("Enter please the second number");
        String op2 = reader.nextLine();
        System.out.println("Enter please the third number");
        String op3 = reader.nextLine();
        System.out.println(plus(plus(op1, op2), op3));
    }

    public static String plus(String fir, String sec) {
        String result = "";

        if (!isValid(sec) || !isValid(fir)){
            throw new NumberFormatException();
        }


        if (fir.charAt(0) == 43) {
            fir = withouPlus(fir);
        }

        if (sec.charAt(0) == 43) {
            sec = withouPlus(sec);
        }

        if (fir.charAt(0)>='0' && fir.charAt(0)<='9' && sec.charAt(0)>='0' && sec.charAt(0)<='9'){
            result+=add(fir,sec);
        }


        if (fir.charAt(0)==45 && sec.charAt(0)==45){
            fir=withouPlus(fir);
            sec=withouPlus(sec);
            result+="-";
            result+= add(fir,sec);
        }

        if(fir.charAt(0)==45){
            fir=withouPlus(fir);
            if (firstIsMoreThanSecond(fir,sec)){
                result="-";
            }
            result+=sub(sec,fir);
            if (fir.equals(sec)){
                result="0";
            }
        }

        if(sec.charAt(0)==45){
            sec=withouPlus(sec);
            if (firstIsMoreThanSecond(sec,fir)){
                result="-";
            }
            result+=sub(fir,sec);
            if (fir.equals(sec)){
                result="0";
            }

        }

        return result;
    }

    public static boolean firstIsMoreThanSecond(String fir, String sec){
        boolean result=true;
        if (fir.length()>sec.length()){
            result=true;
        }else if(fir.length()<sec.length()){
            result =false;
        }else if (fir.length()==sec.length()){
            for (int i = 0; i<=fir.length()-1; i++){
                int fD = fir.charAt(i) - '0';
                int sD = sec.charAt(i) - '0';

                if (fD>=sD){
                    result=true;
                }else if (fD<sD){
                    result=false;
                }
            }
        }
        return result;
    }

    public static String add(String fir, String sec){
        String res = "";
        int workSum = 0;
        int addition = 0;
        int length = 0;

        if (fir.length()>=sec.length()){
            length=fir.length()-1;
        }else {
            length=sec.length()-1;
        }

        if (fir.length()>sec.length()){
            sec=withZeros(sec, fir.length()-sec.length());
        }else if (sec.length()>fir.length()){
            fir=withZeros(fir,sec.length()-fir.length());
        }

        for (int i = length; i>=0; i--){
            int digF = fir.charAt(i) - '0';
            int digS = sec.charAt(i) - '0';

            workSum = digF+digS+addition;
            addition=workSum/10;
            workSum = workSum%10;
            res=workSum+res;
        }

        if (addition!=0){
            res=addition+res;
        }

        return res;

    }

    public static String sub(String fir, String sec){
        String res = "";
        int working = 0;
        int flag = 0;
        int length = 0;

        if (fir.length()>=sec.length()){
            length=fir.length()-1;
        }else {
            length=sec.length()-1;
        }


        if (fir.length()>sec.length()){
            sec=withZeros(sec, fir.length()-sec.length());
            res+=subHelper(fir,sec);
        }else if (sec.length()>fir.length()){
            fir=withZeros(fir,sec.length()-fir.length());
            res+=subHelper(sec,fir);
        }else if(sec.length()==fir.length()){
            if (whoIsFirst(fir,sec)){
                res+=subHelper(fir,sec);
            }else {
                res+=subHelper(sec,fir);
            }


        }
        return cutZero(res);
        //return res;
    }

    public static String subHelper(String fir, String sec){
        String subb = "";
        int length = 0;
        if (fir.length()>=sec.length()){
            length=fir.length()-1;
        }else {
            length=sec.length()-1;
        }

        int working=0;
        int flag=0;
        for (int i = length; i>=0; i--){
            int fDigit = fir.charAt(i) - '0'-flag;
            flag=0;
            int sDigit = sec.charAt(i) - '0';
            if (fDigit<sDigit){
                fDigit+=10;
                flag++;
            }
            working=fDigit-sDigit;
            subb+=working;
        }

        subb=subHInversion(subb);

        return subb;
    }

    public static String subHInversion(String str){
        int counter =str.length()-1;
        String subb2 = "";
        while (counter>=0){
            subb2 += str.charAt(counter);
            counter--;
        }

        return subb2;
    }

    public static boolean whoIsFirst(String fir, String sec){
        boolean flag = true;
        for (int i = 0; i<=fir.length()-1; i++){
            int fD = fir.charAt(i);
            int sD = sec.charAt(i);
            if (fD>sD){
                flag=true;
                break;
            }else if (fD<sD){
                flag=false;
                break;
            }
        }
        return flag;
    }

    public static String withouPlus(String strNum){
        String ret = "";
        if (strNum.charAt(0)==43 || strNum.charAt(0)==45){
            int counter = 1;
            while (counter<strNum.length()){
                ret+=strNum.charAt(counter);
                counter++;
            }
        }
        return ret;
    }

    public static String withZeros(String str, int num){
        int r = num;
        String newOp = "";
        for (int i = 0; i<=r-1; i++){
            newOp+=0;
        }
        newOp+=str;
        str=newOp;
        return str;
    }

    public static boolean isValid(String str){
        int firstDigitIndex = 0;
        for (int i = firstDigitIndex; i < str.length(); i++) {
            if (str.charAt(i) < '0' && str.charAt(0)!=43 && str.charAt(0)!=45 || str.charAt(i) > '9' && str.charAt(0)!=43 && str.charAt(0)!=45) {
                return false;
            }
        }
        return true;
    }

    public static String cutZero(String str){
        String result = "";
        int flag = 0;
        for (int i = 0; i <= str.length() - 1; i++) {
            int fD = str.charAt(i) - '0';
            if (fD == 0) {
                result += "";
            } else {
                flag=i;
                break;
            }
        }

        for (int i = flag; i<= str.length()-1; i++){
            int fD = str.charAt(i) - '0';
            result+=fD;
            flag++;
        }

        return result;
    }
}