import java.util.Arrays;
import java.util.Random;

public class RandomArrayGenerator {
    public static void main(String[] args) {
        int arraySize = 15; // 数组的大小，可以根据需要进行调整
//        int[] randomArray = generateRandomArray(arraySize);
        int[] randomArray = {3,7,11,9,13,3,8,7,1,8,3,4,12,13,2};
        // 输出生成的随机数组
        System.out.println("随机生成的数组：");
        for (int num : randomArray) {
            System.out.print(num + ",");
        }

        System.out.println();
        int[] sorted = randomArray;
        Arrays.sort(sorted);
        for (int num : sorted) {
            System.out.print(num + ",");
        }



    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(15) + 1; // 生成1到99之间的随机整数
        }

        return array;
    }
}
