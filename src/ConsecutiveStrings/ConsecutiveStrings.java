package ConsecutiveStrings;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by oahnus on 2018/3/23
 * 9:57.
 *
 * You are given an array strarr of strings and an integer k.
 * Your task is to return the first longest string consisting of k consecutive strings taken in the array.
 * #Example: longest_consec(["zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"], 2) --> "abigailtheta"
 * n being the length of the string array, if n = 0 or k > n or k <= 0 return "".
 */
public class ConsecutiveStrings {
    public static String longestConsec(String[] strarr, int k) {
        if (strarr.length == 0 || k < 0 || k > strarr.length) {
            return "";
        }
        int[] lenArr = Arrays.stream(strarr).mapToInt(String::length).toArray();
        int max = 0;
        int index = 0;
        for (int i = 0; i < lenArr.length; i++) {
            int sum = 0;
            for (int j = 0; j < k && j < lenArr.length - i; j++) {
                sum += lenArr[i + j];
            }
            if (sum > max) {
                max = sum;
                index = i;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0;i < k; i++) {
            res.append(strarr[index + i]);
        }

        return res.toString();
    }

    private static void testing(String actual, String expected) {
        assertEquals(expected, actual);
    }

    public static void main(String... args) {
        testing(longestConsec(new String[] {"zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"}, 2), "abigailtheta");
        testing(longestConsec(new String[] {"ejjjjmmtthh", "zxxuueeg", "aanlljrrrxx", "dqqqaaabbb", "oocccffuucccjjjkkkjyyyeehh"}, 1), "oocccffuucccjjjkkkjyyyeehh");
        testing(longestConsec(new String[] {}, 3), "");
        testing(longestConsec(new String[] {"itvayloxrp","wkppqsztdkmvcuwvereiupccauycnjutlv","vweqilsfytihvrzlaodfixoyxvyuyvgpck"}, 2), "wkppqsztdkmvcuwvereiupccauycnjutlvvweqilsfytihvrzlaodfixoyxvyuyvgpck");
        testing(longestConsec(new String[] {"wlwsasphmxx","owiaxujylentrklctozmymu","wpgozvxxiu"}, 2), "wlwsasphmxxowiaxujylentrklctozmymu");
        testing(longestConsec(new String[] {"zone", "abigail", "theta", "form", "libe", "zas"}, -2), "");
        testing(longestConsec(new String[] {"it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"}, 3), "ixoyx3452zzzzzzzzzzzz");
        testing(longestConsec(new String[] {"it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"}, 15), "");
        testing(longestConsec(new String[] {"it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"}, 0), "");
    }
}
