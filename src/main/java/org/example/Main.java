package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int selectedCSV = 1;//attribute for switch in Part 3, to select in submenu.
    static Book[][] bookFiles = new Book[8][500]; // array of arrays being filled below

    /**
     * @param fileName This method checks if there are empty files in the file "part1_input_file_names.txt"  and displays it.
     * @return
     */
    public static boolean is_empty(String fileName) {
        boolean isEmpty = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            long lineCount = reader.lines().count();
            if (lineCount == 0) {
                isEmpty = true;
                System.out.println("File is empty " + fileName);
            }

        } catch (FileNotFoundException e) {
            System.out.println("part1 input files not found / corrupted : " + fileName);
        }
        return isEmpty;
    }

    /**
     * It separates each line of the text file into different files in the array. In order to be used by other methods, and read the array.
     * @param fileName
     * @return
     */


    public static String[] inputFileReader(String fileName) {
        String[] arrayCsvFiles = null;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine(); // first line is always the amount of texts e.g = 16
            arrayCsvFiles = new String[Integer.parseInt(line)]; // Array initialized to the amount of texts in the file. eg = 16
            int counter = 0;

            while ((line = reader.readLine()) != null) {
                arrayCsvFiles[counter] = line;
                counter++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("part1 input files not found");
        } catch (IOException e) {
            System.out.println("IO exception");
        }
        return arrayCsvFiles;
    }

    /**
     * This method throws the different type of Syntax Exceptions. Creates the different files based on genre and the syntax_error.txt file.
     * Reads each file in the array arrayCsvFiles[]. From each file, separates each line into fields.
     * @param fileName
     */
    public static void CSVReaderPart1(String fileName) {
        String[] arrayCsvFiles = null;
        BufferedReader reader = null;
        File Cartoon_Comics = new File("outputFiles/Cartoons_Comics.csv");
        File Hobbies_Collectives = new File("outputFiles/Hobbies_Collectives.csv");
        File Movies_Tv_Books = new File("outputFiles/Movies_Tv_Books.csv");
        File Music_Radio_Books = new File("outputFiles/Music_Radio_Books.csv");
        File Nostalgia_Eclectic_Books = new File("outputFiles/Nostalgia_Eclectic_Books.csv");
        File Old_Time_Radio_Books = new File("outputFiles/Old_Time_Radio_Books.csv");
        File Sports_Sports_Memorabilia = new File("outputFiles/Sports_Sports_Memorabilia.csv");
        File Trains_Planes_Automobiles = new File("outputFiles/Trains_Planes_Automobiles.csv");
        File syntax_error_file = new File("outputFiles/syntax_error_file.txt");
        try {
            reader = new BufferedReader(new FileReader(fileName));

            String line;
            int counter = 0;
            PrintWriter csvWriter = null;

            while ((line = reader.readLine()) != null) {
                arrayCsvFiles = line.split(",");
                String csvLine = null;
                try {
                    if (arrayCsvFiles.length > 6) {
                        throw new TooManyFieldsException();
                    } else if (arrayCsvFiles.length < 6) {
                        throw new TooFewFieldsException();
                    } else {

                        switch (arrayCsvFiles[4]) {
                            case "CCB":
                                csvWriter = new PrintWriter(new FileOutputStream(Cartoon_Comics, true));
                                break;
                            case "HCB":
                                csvWriter = new PrintWriter(new FileOutputStream(Hobbies_Collectives, true));
                                break;
                            case "MTV":
                                csvWriter = new PrintWriter(new FileOutputStream(Movies_Tv_Books, true));
                                break;
                            case "MRB":
                                csvWriter = new PrintWriter(new FileOutputStream(Music_Radio_Books, true));
                                break;
                            case "NEB":
                                csvWriter = new PrintWriter(new FileOutputStream(Nostalgia_Eclectic_Books, true));
                                break;
                            case "OTR":
                                csvWriter = new PrintWriter(new FileOutputStream(Old_Time_Radio_Books, true));
                                break;
                            case "SSM":
                                csvWriter = new PrintWriter(new FileOutputStream(Sports_Sports_Memorabilia, true));
                                break;
                            case "TPA":
                                csvWriter = new PrintWriter(new FileOutputStream(Trains_Planes_Automobiles, true));
                                break;
                            default:
                                throw new UnknownGenreException("Unknown genre.");

                        }

                        if (csvWriter != null) {
                            csvLine = String.join(",", arrayCsvFiles); // Proper CSV format
                            csvWriter.println(csvLine);
                        }
                    }
                } catch (TooFewFieldsException | TooManyFieldsException | UnknownGenreException e) {
                    csvWriter = new PrintWriter(new FileOutputStream(syntax_error_file, true));
                    csvWriter.println("\n\nsyntax error in file : " + fileName + "\n====================" +
                            "\nError: " + e + "\nRecord: " + Arrays.toString(arrayCsvFiles));
                } finally {
                    if (csvWriter != null)
                        csvWriter.close();
                }

            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("CSVReader files not found" + e);
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }
/**
 * Implements inputFileReader(), is_empty() and CSVReaderPart1() methods.
 */
    public static void do_part1() {
        String[] arr = inputFileReader("part1_input_file_names.txt");
        for (String file : arr) {
            if (!is_empty(file)) {
                CSVReaderPart1(file);
            }
        }

    }


    // PART 2

    /**
     * This method throws the different types of Semantic Exceptions.  If no exception is thrown creates book
     * object and writes objects to corresponding serialized file (based on genre). Binary files created in do_part2.
     * @param reader
     * @param outputStreams an array of different binary files created in Part2(). Array sent by Part2().
     * @throws IOException
     */
    //if outputStreams is open and closed multiples times, it throws an error, it becomes corrupted.
    public static void CSVReaderPart2(BufferedReader reader, ObjectOutputStream[] outputStreams) throws IOException {
        String line;
        int Year = 0;
        File semantic_error_file = new File("outputBinaryFiles/semantic_error_file.txt");
        PrintWriter semanticErrorWritter = new PrintWriter(new FileOutputStream(semantic_error_file, true));

        while ((line = reader.readLine()) != null) {

            String[] arrayCsvFiles = line.split(",");
            String yearString = arrayCsvFiles[5].replaceAll("\\D+", "");
            double price = Double.parseDouble(arrayCsvFiles[2]);
            try {
                // --- Sieving to check for exceptions, if no exception write to file
                if (price < 0) {
                    throw new BadPriceException("The price is negative.");
                }
                if (yearString.length() == 4) {
                    Year = Integer.parseInt(yearString);
                    if ((Year < 1995) || (Year > 2010)) {
                        throw new BadYearException("Year out of Range.");
                    }
                }

                if (arrayCsvFiles[3].length() == 10) {
                    int sum = 0;
                    for (int i = 0; i < 10; i++) {
                        char num = arrayCsvFiles[3].charAt(i);
                        int asciiValue = num - 48;
                        sum += asciiValue;
                    }
                    if (sum % 11 != 0) { // If not multiple of 11 throw an exception
                        throw new BadIsbn10Exception("Bad ISBN-10 exception");
                    }
                }
                if (arrayCsvFiles[3].length() == 13) {
                    int sum = 0;
                    for (int i = 1; i < arrayCsvFiles[3].length(); i++) {
                        char num = arrayCsvFiles[3].charAt(i);
                        int asciiValue = num - 48;
                        sum += asciiValue;
                    }
                    if (sum % 13 != 0) { // If not multiple of 13 throw an exception
                        throw new BadIsbn13Exception("Bad ISBN-13 exception");
                    }

                }
                // --- End of exception checks
                // If no exception thrown creates book object and writes to corresponding serialized file.
                //Here I can implement the missing field exception and write into syntax error. As the book objects separates each field.
                Book book = new Book(arrayCsvFiles[0], arrayCsvFiles[1], price, arrayCsvFiles[3], arrayCsvFiles[4], Year);
                switch (book.getGenre()) {
                    case "CCB":
                        outputStreams[0].writeObject(book);
                        break;
                    case "HCB":
                        outputStreams[1].writeObject(book);
                        break;
                    case "MTV":
                        outputStreams[2].writeObject(book);
                        break;
                    case "MRB":
                        outputStreams[3].writeObject(book);
                        break;
                    case "NEB":
                        outputStreams[4].writeObject(book);
                        break;
                    case "OTR":
                        outputStreams[5].writeObject(book);
                        break;
                    case "SSM":
                        outputStreams[6].writeObject(book);
                        break;
                    case "TPA":
                        outputStreams[7].writeObject(book);
                        break;
                }
            } catch (BadPriceException | BadYearException | BadIsbn10Exception | BadIsbn13Exception e) {
                semanticErrorWritter.println(e);
            } finally {
                semanticErrorWritter.close();
            }
        }
    }

    /**
     * This method creates the binary files. Reads the csv files one by one and opens and creates the binary files.  Uses CSVReaderPart2()
     */
    //if outputStreams is open and closed multiples times, it throws an error, it becomes corrupted.
    public static void do_part2() {
        ObjectOutputStream [] outputStreams = new ObjectOutputStream[8];
        BufferedReader reader = null;

        try {
            // Open ObjectOutputStream's
            outputStreams[0] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Cartoons_Comics.csv.ser"));
            outputStreams[1] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Hobbies_Collectives.csv.ser"));
            outputStreams[2] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Movies_Tv_Books.csv.ser"));
            outputStreams[3] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Music_Radio_Books.csv.ser"));
            outputStreams[4] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Nostalgia_Eclectic_Books.csv.ser"));
            outputStreams[5] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Old_Time_Radio_Books.csv.ser"));
            outputStreams[6] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Sports_Sports_Memorabilia.csv.ser"));
            outputStreams[7] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Trains_Planes_Automobiles.csv.ser"));

            // Loop through filenames to read each of them and sieve(cierne) through
            String[] arr = {"outputFiles/Cartoons_Comics.csv", "outputFiles/Hobbies_Collectives.csv", "outputFiles/Movies_TV_Books.csv", "outputFiles/Music_Radio_Books.csv", "outputFiles/Nostalgia_Eclectic_Books.csv", "outputFiles/Old_Time_Radio_Books.csv", "outputFiles/Sports_Sports_Memorabilia.csv", "outputFiles/Trains_Planes_Automobiles.csv"};
            for (String item : arr) {
                reader = new BufferedReader(new FileReader(item));
                CSVReaderPart2(reader, outputStreams);
            }

            // Close all the ObjectOutputStream's and reader once all files have written to them
            for (ObjectOutputStream item : outputStreams) {
                item.close();
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exceptionn");
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

    // PART 3

    /**
     *
     * @param fileName
     * @return an array of Book objects
     * This methods reads the objects (deserializes) and creates an array of Book objects and return this array.
     */
    public static Book[] deserialize(ObjectInputStream fileName) {
        Book[] books = new Book[500];// in order to read the objects of one file.csv.ser. Only reads one.
        int counter = 0;

        try {
            while (true) {
                Book book = (Book) fileName.readObject();
                if (book == null) {
                    System.out.println("Error null book");
                    break; // Break if a null book is encountered
                }
                books[counter] = new Book(book); // Using copy constructor
                counter++;
            }
        } catch (EOFException e) {
            System.out.println("EOF has been reached.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return books;
    }


    /**
     * This method  reads one by one using the method deserialize() and
     * the double array static bookFiles [][] is being filled to use it later in the menu of Part 3 and submenu.
     */
    public static void BinaryFileReader_PT3() {
        ObjectInputStream[] filenames;
        int counter = 0;
        try {
            filenames = new ObjectInputStream[8];
            filenames[0] = new ObjectInputStream(new FileInputStream("outputBinaryFiles/Cartoons_Comics.csv.ser"));
            filenames[1] = new ObjectInputStream(new FileInputStream("outputBinaryFiles/Hobbies_Collectives.csv.ser"));
            filenames[2] = new ObjectInputStream(new FileInputStream("outputBinaryFiles/Movies_Tv_Books.csv.ser"));
            filenames[3] = new ObjectInputStream(new FileInputStream("outputBinaryFiles/Music_Radio_Books.csv.ser"));
            filenames[4] = new ObjectInputStream(new FileInputStream("outputBinaryFiles/Nostalgia_Eclectic_Books.csv.ser"));
            filenames[5] = new ObjectInputStream(new FileInputStream("outputBinaryFiles/Old_Time_Radio_Books.csv.ser"));
            filenames[6] = new ObjectInputStream(new FileInputStream("outputBinaryFiles/Sports_Sports_Memorabilia.csv.ser"));
            filenames[7] = new ObjectInputStream(new FileInputStream("outputBinaryFiles/Trains_Planes_Automobiles.csv.ser"));

            for (ObjectInputStream item : filenames) {
                bookFiles[counter] = deserialize(item); // Saving to static array of Book's after deserialization
                counter++;
            }
            //For each loop to close files
            for (ObjectInputStream item : filenames) {
                item.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void SubMenuCounter(int[] books) {
        switch (selectedCSV) {
            case 1:
                System.out.println("viewing: " + " 1  Cartoons_Comics_Books.csv.ser       (" + books[0] + " records)\n");
                break;
            case 2:
                System.out.println("viewing: " + " 2  Hobbies_Collectibles_Books.csv.ser  (" + books[1] + ")\n");
                break;
            case 3:
                System.out.println("viewing: " + " 3  Movies_TV.csv.ser (" + books[2] + ") \n");
                break;
            case 4:
                System.out.println("viewing: " + "4  Music_Radio_Books.csv.ser (" + books[3] + ")\n");
                break;
            case 5:
                System.out.println("viewing: " + " 5  Nostalgia_Eclectic_Books.csv.ser (" + books[4] + ")\n");
                break;
            case 6:
                System.out.println("viewing: " + " 6  Old_Time_Radio.csv.ser (" + books[5] + ")\n");
                break;
            case 7:
                System.out.println("viewing: " + " 7  Sports_Sports_Memorabilia.csv.ser   (" + books[6] + ")\n");
                break;
            case 8:
                System.out.println("viewing: " + " 8  Trains_Planes_Automobiles.csv.ser  (" + books[7] + ")\n");
                break;
        }
        Scanner sc = new Scanner(System.in);
        int upTobookNumber = sc.nextInt() - 1;
        int counter = 0;
        for (Book book : bookFiles[selectedCSV - 1]) {
            if (book != null && counter <= upTobookNumber)
                System.out.println(book.toString());
            else if (book == null) {
                System.out.println("EOF has been reached");
                System.out.println();
                break;
            } else {
                System.out.println();
                break;
            }
            counter++;
        }
    }

    /**
     * Method for  Main menu. When selecting 's' it counts how many records there are in each binary file from bookFiles[][].
     */
    public static void do_part3() {
        BinaryFileReader_PT3();      // fill bookFiles with books
        String[] arr = {"Cartoons_Comics.csv.ser", "Hobbies_Collectives.csv.ser", "Movies_TV_Books.csv.ser", "Music_Radio_Books.csv.ser", "Nostalgia_Eclectic_Books.csv.ser", "Old_Time_Radio_Books.csv.ser", "Sports_Sports_Memorabilia.csv.ser", "Trains_Planes_Automobiles.csv.ser"};
        int[] books = new int[8];
        System.out.println();
        Scanner sc = new Scanner(System.in);
        String input;
        int submenuOption;
        do {
            System.out.println("-----------------------------\n" +
                    "          Main Menu\n" +
                    "-----------------------------\n" +
                    " v  View the selected file:\t" + arr[selectedCSV - 1] + "\n" +
                    " s  Select a file to view\n" +
                    " x  Exit\n" +
                    "-----------------------------");
            input = sc.next();
            switch (input.charAt(0)) {
                case 'v':
                    break;
                case 's':
                    // Count amount of books in lists
                    int counter = 0;
                    //When selecting 's' it counts how many records there are in each binary file.
                    for (Book[] item : bookFiles) {
                        int bookCounter = 0;
                        for (Book innerItem : item) {
                            if (innerItem != null && !innerItem.isEmpty())
                                bookCounter++;
                        }
                        books[counter] = bookCounter;
                        counter++;
                    }
                    System.out.println("------------------------------\n" +
                            "        File Sub-Menu\n" +
                            "------------------------------\n" +
                            " 1  Cartoons_Comics_Books (" + books[0] + ") records\n" +
                            " 2  Hobbies_Collectibles_Books (" + books[1] + ") records)\n" +
                            " 3  Movies_TV (" + books[2] + ")  records\n" +
                            " 4  Music_Radio_Books (" + books[3] + ") records\n" +
                            " 5  Nostalgia_Eclectic_Books (" + books[4] + ") records\n" +
                            " 6  Old_Time_Radio (" + books[5] + ") records\n" +
                            " 7  Sports_Sports_Memorabilia   (" + books[6] + ") records\n" +
                            " 8  Trains_Planes_Automobiles  (" + books[7] + ") records\n" +
                            " 9  Exit\n" +
                            "------------------------------\n" +
                            "Enter Your Choice: ");
                    submenuOption = sc.nextInt();
                    selectedCSV = submenuOption;
                    SubMenuCounter(books);
                    break;
                case 'x':
                    break;
                default:
                    System.out.println("Wrong input, please select v ,s or x. ");
                    break;
            }
        } while (input.charAt(0) != 'x');
    }


    public static void main(String[] args) {
        do_part1();
        do_part2();
        do_part3();
    }
}