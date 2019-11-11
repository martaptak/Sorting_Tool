package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static String inputFile;

    public static void main(final String[] args) {

        String dataType = "word";
        String sortingType = "natural";

        boolean byCount = false;
        boolean sortIntegers = false;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-sortingType":
                    sortingType = args[i + 1];
                    if (!sortingType.equals("natural") && !sortingType.equals("byCount")) {
                        System.out.println("No sorting type defined!");
                    } else {
                        i++;
                    }
                    break;
                case "-sortIntegers":
                    sortIntegers = true;
                    break;
                case "-inputFile":
                    inputFile = args[i + 1];
                    i++;
                    break;
                case "-outputFile":
                    String outputFile = args[i + 1];
                    i++;
                    break;
                case "-dataType":
                    dataType = args[i + 1];
                    if (!dataType.equals("word") && !dataType.equals("long") && !dataType.equals("line")) {
                        System.out.println("No data type defined!");
                    } else {
                        i++;
                    }
                    break;
                default:
                    System.out.println(arg + " isn't a valid parameter. It's skipped.");
                    break;
            }
        }
        if (sortIntegers) {
            sortIntegers();
        } else {

            if (sortingType.equals("byCount")) {
                byCount = true;
            }

            switch (dataType) {
                case "word":
                default:
                    calculateWords(byCount);
                    break;
                case "long":
                    calculateLong(byCount);
                    break;
                case "line":
                    calculateLines(byCount);
                    break;
            }
        }


    }

    private static void sortIntegers() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();

        while (scanner.hasNextLong()) {
            int number = scanner.nextInt();
            numbers.add(number);
        }

        System.out.println(String.format("Total numbers: %d.", numbers.size()));
        Collections.sort(numbers);
        System.out.print("Sorted data: ");
        numbers.forEach(number -> System.out.print(number + " "));
    }


    private static void calculateLines(boolean byCount) {
        Scanner scanner;
        if(inputFile != null){
            try {
                scanner = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
        else {
            scanner	 = new Scanner(System.in);
        }


        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        lines.sort(Comparator.comparing(String::length).thenComparing(String::compareTo));
        System.out.println(String.format("Total lines: %d.", lines.size()));

        if (byCount) {
            Map<String, Integer> linesToCount = new HashMap<>();
            for (String line : lines) {
                linesToCount.put(line, Collections.frequency(lines, line));
            }

            linesToCount.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(longIntegerEntry -> {
                int percent = (int) Math.floor(((double) longIntegerEntry.getValue() / (double) lines.size()) * 100);
                System.out.println(String.format("%s: (%d time(s), %d%%).", longIntegerEntry.getKey(),
                        longIntegerEntry.getValue(), percent));
            });
        } else {
            String max = Collections.max(lines, Comparator.comparing(String::length).thenComparing(String::compareTo));
            int count = Collections.frequency(lines, max);
            int percent = (int) Math.floor(((double) count / (double) lines.size()) * 100);
            System.out.println("The longest line:");
            System.out.println(max);
            System.out.println(String.format("(%d time(s), %d%%)", count, percent));
        }


    }

    private static void calculateLong(boolean byCount) {

        Scanner scanner;
        if(inputFile != null){
            try {
                scanner = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
        else {
            scanner	 = new Scanner(System.in);
        }

        List<Long> numbers = new ArrayList<>();
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            numbers.add(number);
        }
        Collections.sort(numbers);
        System.out.println(String.format("Total numbers: %d.", numbers.size()));
        if (byCount) {
            Map<Long, Integer> numbersToCount = new HashMap<>();
            for (Long number : numbers) {
                numbersToCount.put(number, Collections.frequency(numbers, number));
            }

            numbersToCount.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(longIntegerEntry -> {
                int percent = (int) Math.floor(((double) longIntegerEntry.getValue() / (double) numbers.size()) * 100);
                System.out.println(String.format("%s: (%d time(s), %d%%).", longIntegerEntry.getKey(),
                        longIntegerEntry.getValue(), percent));
            });
        } else {
            Long max = Collections.max(numbers);
            int count = Collections.frequency(numbers, max);
            int percent = (int) Math.floor(((double) max / (double) numbers.size()) * 100);
            System.out.println(String.format("The greatest number: %d (%d time(s), %d%%).", max, count,
                    percent));
        }
    }

    private static void calculateWords(boolean byCount) {
        Scanner scanner;
        if(inputFile != null){
            try {
                scanner = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
        else {
            scanner	 = new Scanner(System.in);
        }
        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            String word = scanner.next();
            words.add(word);
        }
        Collections.sort(words);
        System.out.println(String.format("Total words: %d.", words.size()));
        if (byCount) {
            Map<String, Integer> wordsToCount = new HashMap<>();
            for (String word : words) {
                wordsToCount.put(word, Collections.frequency(words, word));
            }

            wordsToCount.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(longIntegerEntry -> {
                int percent = (int) Math.floor(((double) longIntegerEntry.getValue() / (double) words.size()) * 100);
                System.out.println(String.format("%s: (%d time(s), %d%%).", longIntegerEntry.getKey(),
                        longIntegerEntry.getValue(), percent));
            });
        } else {
            String max = Collections.max(words, Comparator.comparing(String::length));
            int count = Collections.frequency(words, max);

            int percent = (int) Math.floor(((double) count / (double) words.size()) * 100);
            System.out.println(String.format("The longest word: %s (%d time(s), %d%%).", max, count, percent));
        }
    }
}
