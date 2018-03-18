package LengthOfMissingArray;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by oahnus on 2018/3/18
 * 19:56.
 *
 *
 */
public class LengthOfMissingArray {

    /**
     * use Stream
     * @param arrayOfArrays
     * @return
     */
    public static int getLengthOfMissingArrayByStream(Object[][] arrayOfArrays) {
        if(arrayOfArrays == null || arrayOfArrays.length == 0) {
            return 0;
        }

        int[] lenArr = Arrays.stream(arrayOfArrays).mapToInt(array -> array.length).sorted().toArray();
        for (int i = 0 ;i < lenArr.length - 1;i++) {
            if (lenArr[i] == 0) {
                return 0;
            }
            if (lenArr[i] + 1 != lenArr[i + 1]) {
                return lenArr[i] + 1;
            }
        }
        return 0;
    }

    /**
     * codewar user JurenZak's solution
     * @param arrayOfArrays
     * @return
     */
    public static int getLengthOfMissingArrayByJurenZak(Object[][] arrayOfArrays)
    {
        if (arrayOfArrays == null || arrayOfArrays.length == 0)
            return 0;
        for (Object[] arr : arrayOfArrays) {
            if (arr == null || arr.length == 0)  return 0;
        }

        Integer[] arr = Arrays.asList(arrayOfArrays).stream()
                .map( el -> el.length)
                .sorted()
                .toArray(Integer[]::new);

        int currentSum = Arrays.asList(arr).stream().reduce(0,(a,b) -> a+b);
        int fullSum = IntStream.rangeClosed(arr[0], arr[arr.length-1]).sum();

        return fullSum - currentSum;

    }

    public static int getLengthOfMissingArray(Object[][] arrayOfArrays) {
        if(arrayOfArrays == null || arrayOfArrays.length == 0) {
            return 0;
        }
        int[] lenArr = new int[arrayOfArrays.length];
        for (int i = 0; i < lenArr.length ; i++) {
            if (arrayOfArrays[i] == null || arrayOfArrays[i].length == 0) {
                return 0;
            }
            int len = arrayOfArrays[i].length;
            lenArr[i] = len;
        }
        sort(lenArr);

        for (int i = 0 ;i < lenArr.length - 1;i++) {
            if (lenArr[i] + 1 != lenArr[i + 1]) {
                return lenArr[i] + 1;
            }
        }
        return 0;
    }

    public static void sort(int[] arr) {
        int length = arr.length;
        int lastChange = length;
        int temp;
        while (lastChange > 0) {
            length = lastChange;
            lastChange = 0;
            for (int i = 1 ;i < length; i++) {
                if (arr[i] < arr[i - 1]) {
                    temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    lastChange = i;
                }
            }
        }
    }

    public static void RandomTests() {
        for(int r=0;r<40;r++) {
            int startLength = (int)Math.floor(Math.random() * 5);
            int endLength = (int)Math.floor(Math.random() * 9) + startLength + 3;

            int missingLength = (int)Math.floor(Math.random() * (endLength - startLength - 1)) + startLength + 1;

            Object[][] arrayOfArrays = new Object[endLength-startLength][];

            int count = 0;
            for(int i=startLength;i<=endLength;i++)
            {
                if(i != missingLength)
                {
                    Object[] arr = new Object[i];
                    for(int p=0;p<i;p++)
                    {
                        arr[p] = (int)Math.floor(Math.random() * 5);
                    }
                    arrayOfArrays[count] = arr;
                    count++;
                }
            }

            if(startLength == 0)
            {
                missingLength = 0;
            }

            for(int i=0;i<endLength-startLength;i++)
            {
                int rand1 = (int)Math.floor(Math.random() * (endLength-startLength));
                int rand2 = (int)Math.floor(Math.random() * (endLength-startLength));

                Object[] temp = arrayOfArrays[rand1];
                arrayOfArrays[rand1] = arrayOfArrays[rand2];
                arrayOfArrays[rand2] = temp;
            }

            String message = "Wrong for " + Arrays.deepToString(arrayOfArrays);
            assertEquals(message, missingLength, getLengthOfMissingArray(arrayOfArrays));
        }
    }

    public static void main(String... args) {
        int[] arr = new int[] {1, 4, 2,5};
        sort(arr);
        for (int i = 0 ;i < arr.length ;i ++) {
            System.out.print(arr[i]);
        }
        long start = System.currentTimeMillis();
        assertEquals(3, getLengthOfMissingArray(new Object[][] { new Object[] { 1, 2 }, new Object[] { 4, 5, 1, 1 }, new Object[] { 1 }, new Object[] { 5, 6, 7, 8, 9 }} ));
        System.out.println("run time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        assertEquals(2, getLengthOfMissingArray(new Object[][] { new Object[] { 5, 2, 9 }, new Object[] { 4, 5, 1, 1 }, new Object[] { 1 }, new Object[] { 5, 6, 7, 8, 9 }} ));
        System.out.println("run time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        assertEquals(2, getLengthOfMissingArray(new Object[][] { new Object[] { null }, new Object[] { null, null, null } } ));
        System.out.println("run time: " + (System.currentTimeMillis() - start));

        assertEquals(5, getLengthOfMissingArray(new Object[][] { new Object[] { 'a', 'a', 'a' }, new Object[] { 'a', 'a' }, new Object[] { 'a', 'a', 'a', 'a' }, new Object[] { 'a' }, new Object[] { 'a', 'a', 'a', 'a', 'a', 'a' }} ));

        assertEquals(0, getLengthOfMissingArray(new Object[][] { }));

        RandomTests();
    }
}
