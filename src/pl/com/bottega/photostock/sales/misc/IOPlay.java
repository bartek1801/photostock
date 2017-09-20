package pl.com.bottega.photostock.sales.misc;

import javax.sound.midi.Soundbank;
import java.io.*;

public class IOPlay {


    static class  Person implements Serializable{

        static final long serialVersionUID = 1L;

        int age;
        String name;

        String gender;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    public static void main(String[] args) {
       // basicRead();
       // basicReadTrywidthResources();
       // characterRead();
       // bufferedRead();
       // basicWrite();
        //bufferedRead();
        //writer();
        //printWriter();
        //writeObjects();
        readObjects();
    }

    private static void readObjects() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\bartek\\IdeaProjects\\Pliki_do_czytania\\objects.bin"))) {
            Object o;
            while ((o = ois.readObject()) != null){
                Person p = (Person) o;
                System.out.printf("%s, %d", p.name, p.age);
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException eofException){

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeObjects() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\bartek\\IdeaProjects\\Pliki_do_czytania\\objects.bin"))) {
            oos.writeObject(new Person(17, "Jan Nowak"));
            oos.writeObject(new Person(22, "Janina Nowak"));
            oos.writeObject(new Person(19, "Marcin Nowak"));
            oos.writeObject(new Person(18, "Krystyna Nowak"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printWriter() {
        try (OutputStream outputStream = new FileOutputStream("C:\\Users\\bartek\\IdeaProjects\\Pliki_do_czytania\\test_zapis3.txt");
             PrintStream printStream = new PrintStream(outputStream);
        ){
            printStream.println("Zażółć gęślą jaźń");
            printStream.println("Ala ma kota");

        }
        catch (FileNotFoundException e){
            System.out.println("Nie udało się utworzyć zapisu");
        }
        catch (IOException e){
            System.out.println("Błąd wyjścia");
        }
    }

    private static void writer() {
        try (OutputStream outputStream = new FileOutputStream("C:\\Users\\bartek\\IdeaProjects\\Pliki_do_czytania\\test_zapis2.txt");
             Writer writer = new OutputStreamWriter(outputStream);
        ){
            writer.write("Zażółć gęślą jaźń\r\n");
        }
        catch (FileNotFoundException e){
            System.out.println("Nie udało się utworzyć zapisu");
        }
        catch (IOException e){
            System.out.println("Błąd wyjścia");
        }
    }

    private static void basicWrite() {
        try (OutputStream outputStream = new FileOutputStream("C:\\Users\\bartek\\IdeaProjects\\Pliki_do_czytania\\test_zapis.txt", true)){
            outputStream.write("Zażółć gęślą jaźń".getBytes());
            //outputStream.write("Zażółć gęślą jaźń".getBytes("CP1250"));
        }
        catch (FileNotFoundException e){
            System.out.println("Nie udało się utworzyć zapisu");
        }
        catch (IOException e){
            System.out.println("Błąd wyjścia");
        }
    }

    private static void bufferedRead() {
        try (InputStream inputStream = new FileInputStream("C:\\Users\\bartek\\IdeaProjects\\Pliki_do_czytania\\test3.txt")) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Nie ma takiego pliku");
        }
        catch (IOException e){
            System.out.println("Błąd wejścia");
        }
    }

    private static void characterRead() {
        try (InputStream inputStream = new FileInputStream("C:\\Users\\bartek\\IdeaProjects\\Pliki_do_czytania\\test.txt")) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF8");
            System.out.println(inputStreamReader.getEncoding());
            int c;
            while ((c = inputStreamReader.read()) != -1) {
                System.out.print((char) c);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Nie ma takiego pliku");
        }
        catch (IOException e){
            System.out.println("Błąd wejścia");
        }
    }

    private static void basicReadTrywidthResources() {//w bloku () można umieszczać tylko obiekty implementuące interfejs Closable od Javy 7
        try (InputStream inputStream = new FileInputStream("C:\\Users\\bartek\\IdeaProjects\\Pliki_do_czytania\\test.txt")) {
            int b;
            while ((b = inputStream.read()) != -1) {
                System.out.print((char) b);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Nie ma takiego pliku");
        }
        catch (IOException e){
            System.out.println("Błąd wejścia");
        }

    }


    public static void basicRead(){
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\bartek\\IdeaProjects\\Pliki_do_czytania\\test.txt");
            int b;
            while ((b = inputStream.read()) != -1){
                System.out.print((char) b);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Nie ma takiego pliku");
        }
        catch (IOException e){
            System.out.println("Błąd wejścia");
        }
        finally {
            if (inputStream != null)
            try {
                inputStream.close();
            } catch (IOException e) {
                System.out.println("Błąd zamykania pliku");;
            }
        }
    }
}
