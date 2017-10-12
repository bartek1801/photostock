package pl.com.bottega.photostock.sales.misc;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class WordsMapFile {


    public static void main(String[] args) {

        String input = "C:\\Users\\bartek\\IdeaProjects\\Pliki_do_czytania\\plik_in.txt";
        String output = "C:\\Users\\bartek\\IdeaProjects\\Pliki_do_czytania\\plik_out.txt";

        try (
                BufferedReader bfIn = new BufferedReader(new InputStreamReader(new FileInputStream(input)));
                PrintStream psOut = new PrintStream(new FileOutputStream(output));
        ){
            process(bfIn, psOut);
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void process(BufferedReader input, PrintStream output) throws IOException {
        Map<String, Integer> map = mapWords(input);
        writeMap(output, map);
    }

    private static void writeMap(PrintStream output, Map<String, Integer> map) {
        map.entrySet().forEach(new Consumer<Map.Entry<String, Integer>>() {
            @Override
            public void accept(Map.Entry<String, Integer> entryString) {
                output.printf("%s -> %d", entryString.getKey(), entryString.getValue());
                output.println();
            }
        });
    }

    private static Map<String, Integer> mapWords(BufferedReader input) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        String line;
        while ( (line = input.readLine()) != null){
            line = line.toLowerCase();
            String[] words = line.split("\\s+");
            putIntoMap(map, words);
        }
        return map;
    }

    private static void putIntoMap(Map<String, Integer> map, String[] words) {
        for (String word : words){
            int count = map.getOrDefault(word, 0);
            map.put(word, count + 1);
        }
    }


}
