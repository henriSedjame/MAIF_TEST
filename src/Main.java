import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        String str = "abc-de-f!g";

        String result = reverseAlphaNumeric(str);

        System.out.println(result);

    }

    public static String reverseAlphaNumeric(String str) {

        StringBuilder builder = new StringBuilder();


        List<AbstractMap.SimpleEntry<Integer, String>> entries = IntStream.range(0, str.length())
                .mapToObj(i -> {
                    String letter = String.valueOf(str.charAt(i));

                    if (!isAlphaNum(letter)) {
                        return new AbstractMap.SimpleEntry<Integer, String>(i, letter);
                    }else {
                        builder.append(letter);
                    }

                    if(i == str.length()-1) builder.reverse();
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());


        entries.forEach(entry -> builder.insert(entry.getKey(), entry.getValue()));


        return builder.toString();
    }

    public static boolean isAlphaNum(String letter) {
        Pattern pattern  = Pattern.compile("(\\w|\\d){1}");
        Matcher matcher = pattern.matcher(letter);
        return matcher.matches();
    }
}

