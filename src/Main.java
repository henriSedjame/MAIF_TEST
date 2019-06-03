import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        String str = "ab.c-de-f!g96_.";

        String result = reverseAlphaNumeric(str);

        System.out.println(result);

    }

    private static String reverseAlphaNumeric(String str) {

        StringBuilder builder = new StringBuilder();

        IntStream.range(0, str.length())
                .mapToObj(i -> handleIndex(str, builder, i))
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .forEach(entry -> builder.insert(entry.getKey(), entry.getValue()));

        return builder.toString();
    }

    private static AbstractMap.SimpleEntry<Integer, String> handleIndex(String str, StringBuilder builder, int i) {

        String letter = String.valueOf(str.charAt(i));

        AbstractMap.SimpleEntry<Integer, String> result = null;

        if (!isAlphaNum(letter)) result = new AbstractMap.SimpleEntry<Integer, String>(i, letter);
        else builder.append(letter);

        if(i == str.length()-1) builder.reverse();

        return result;
    }

    private static boolean isAlphaNum(String letter) {
        Pattern pattern  = Pattern.compile("([A-Za-z]|\\d)");
        Matcher matcher = pattern.matcher(letter);
        return matcher.matches();
    }
}

