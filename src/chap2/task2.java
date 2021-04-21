package chap2;

public class task2 {
    final static int MAX_NUM = 1000;

    public static void main(String[] args) {
        for (int i = 1; i < MAX_NUM; i++) {
            int k = i;
            while (k != 0) {
                if (isPrime(k))
                    k /= 10;
                else
                    break;
            }
            if (k == 0) System.out.print(i + " ");
        }
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;
        return true;
    }
}
