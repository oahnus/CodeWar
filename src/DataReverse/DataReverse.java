package DataReverse;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by oahnus on 2018/3/20
 * 9:06.
 *
 * A stream of data is received and needs to be reversed.
 * Each segment is 8 bits meaning the order of these segments need to be reversed:
 * 11111111 00000000 00001111 10101010
 * (byte1) (byte2) (byte3) (byte4)
 * 10101010 00001111 00000000 11111111
 * (byte4) (byte3) (byte2) (byte1)
 * Total number of bits will always be a multiple of 8. The data is given in an array as such:
 * [1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,1,0,1,0,1,0]
 */
public class DataReverse {
    public static int[] DataReverse(int[] data) {
        int[] newData = new int[data.length];

        int byteNum = data.length / 8;
        for (int i = 0; i < byteNum; i++) {
            System.arraycopy(data, i * 8, newData, (byteNum - 1 - i) * 8, 8);
        }
        return newData;
    }

    /**
     * 真的是什么题目都能用流做。。。。
     * codewar user clickclackdoggo's solution
     * @param data
     * @return
     */
    public static int[] DataReverseByclickclackdoggo(int[] data) {
        return java.util.stream.IntStream.range(0, data.length)
                .map(i -> data[data.length - 8 - (i / 8 * 8) + (i % 8)])
                .toArray();
    }

    @Test
    public void test() {
        int[] data1= {0,1,0,1,0,1,0,1,1,0,1,0,1,0,1,0};
        int[] data2= {1,0,1,0,1,0,1,0,0,1,0,1,0,1,0,1};
        assertArrayEquals(data2, DataReverse.DataReverse(data1));

        int[] data3= {1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1};
        int[] data4= {1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0};
        assertArrayEquals(data4, DataReverse.DataReverse(data3));
    }
}
