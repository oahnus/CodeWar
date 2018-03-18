package PlayingWithPassphrases;

import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by oahnus on 2018/3/18
 * 17:10.
 *
 * Everyone knows passphrases. One can choose passphrases from poems, songs, movies names
 * and so on but frequently they can be guessed due to common cultural references.
 * You can get your passphrases stronger by different means. One is the following:
 * choose a text in capital letters including or not digits and non alphabetic characters,
 * 1. shift each letter by a given number but the transformed letter must be a letter (circular shift),
 * 2. replace each digit by its complement to 9,
 * 3. keep such as non alphabetic and non digit characters,
 * 4. downcase each letter in odd position, upcase each letter in even position (the first character is in position 0),
 * 5. reverse the whole result.
 * #Example:
 * your text: "BORN IN 2015!", shift 1
 * 1 + 2 + 3 -> "CPSO JO 7984!"
 * 4 "CpSo jO 7984!"
 * 5 "!4897 Oj oSpC"
 */
public class PlayingWithPassphrases {
    public static String playPass(String s, int n) {
        char[] chars = s.toCharArray();
        for (int idx = 0; idx < chars.length; idx++) {
            if (!Character.isLetterOrDigit(chars[idx])) {
                continue;
            }
            if (Character.isDigit(chars[idx])) {
                chars[idx] = Character.forDigit(9 - Character.getNumericValue(chars[idx]), 10);
                continue;
            }
            chars[idx] = nextNChar(chars[idx], n);
            if (idx % 2 == 0) {
                chars[idx] = Character.toUpperCase(chars[idx]);
            } else {
                chars[idx] = Character.toLowerCase(chars[idx]);
            }
        }

        return new StringBuffer(String.valueOf(chars)).reverse().toString();
    }

    public static char nextNChar(char ch, int n) {
        n %= 26;
        if (ch >= 'a' && ch <= 'z'- n) {
            return (char) (ch + n);
        }
        if (ch > 'a' - n) {
            return (char)(n - ('z' - ch) - 1 + 'a');
        }
        if (ch >= 'A' && ch <= 'Z'- n) {
            return (char) (ch + n);
        }
        if (ch > 'A' - n) {
            return (char)(n - ('Z' - ch) - 1 + 'A');
        }
        return ch;
    }

    // codewar user SithFire's solution

    private static int count = 0;
    public static String playPassBySithFire(final String s, final int n) {
        count = 0;

        return new StringBuilder()
            .append(
                s.toUpperCase()
                    .chars()
                    .map(i -> Character.isDigit(i) ? 57 - Character.getNumericValue(i) : i)
                    .mapToObj(c -> (char) c)
                    .map(c -> (c <= 90 && c >= 65) ? (char) (c + (n % 26)) : c)
                    .map(c -> c > 90 ? (char) ((c % 91) + 65) : c)
                    .map(Object::toString)
                    .map(i -> (count++ % 2) == 0 ? i.toUpperCase() : i.toLowerCase())
                    .collect(Collectors.joining("")))
            .reverse()
            .toString();
    }

    public static void main(String... args) {
        assertEquals("!!!vPz fWpM J", playPass("I LOVE YOU!!!", 1));
        assertEquals("4897 NkTrC Hq fT67 GjV Pq aP OqTh gOcE CoPcTi aO",
                playPass("MY GRANMA CAME FROM NY ON THE 23RD OF APRIL 2015", 2));
        System.out.println(playPass("IN 2012 TWO CAMBRIDGE UNIVERSITY RESEARCHERS ANALYSED PASSPHRASES FROM THE AMAZON PAY SYSTEM...", 20));
        assertEquals("...gYnMsM SuJ HiTuGu yBn gIlZ MyMuLbJmMuJ XyMsFuHu mLyBwLuYmYl sNcMlYpChO YaXcLvGuW IqN 7897 hC",
                playPass("IN 2012 TWO CAMBRIDGE UNIVERSITY RESEARCHERS ANALYSED PASSPHRASES FROM THE AMAZON PAY SYSTEM...", 20));
        assertEquals("...wOdCiC IkZ XyJkWk oRd wYbP CoCkBrZcCkZ NoCiVkXk cBoRmBkOcOb iDsCbOfSxE OqNsBlWkM YgD 7897 xS",
                playPass("IN 2012 TWO CAMBRIDGE UNIVERSITY RESEARCHERS ANALYSED PASSPHRASES FROM THE AMAZON PAY SYSTEM...", 10));
        assertEquals(")1308( qZuR Ae pQeEqDp gAk qYuF M ZaBg qOzA",
                playPass("ONCE UPON A TIME YOU DRESSED SO FINE (1968)", 12));
    }
}
