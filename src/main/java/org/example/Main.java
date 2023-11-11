package org.example;

import java.io.*;
import java.util.Arrays;

public class Main {

    /**
     * @param fileName
     * @return
     */
    public static boolean is_empty(String fileName) {
        boolean isEmpty = false;
        //     long lineCount = Files.lines(Paths.get(csvFilePath)).count();
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
     * @param fileName
     * @return
     */
    public static String[] inputFileReader(String fileName)// Function to check if its empty or not
    {
        String[] arrayCsvFiles = null;
        try {

            //File csvEmpty = new File("books1996.csv.txt");
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine(); // first line is always the amount of texts = 16
            arrayCsvFiles = new String[Integer.parseInt(line)]; // Array initialized to 16
            int counter = 0;

            while ((line = reader.readLine()) != null) {
                arrayCsvFiles[counter] = line;
                //System.out.println(line);
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

    public static void CSVReaderPart1(String fileName) {
        String[] arrayCsvFiles = null;
        BufferedReader reader = null;
        //PrintWriter csvWriter = null;
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
            //File csvEmpty = new File("books1996.csv.txt");
            reader = new BufferedReader(new FileReader(fileName));
            //csvWriter = new PrintWriter(new FileOutputStream(new File("/outputFiles/Cartoons_Comics.csv"),true));

            String line;
            int counter = 0;

            while ((line = reader.readLine()) != null) {
                arrayCsvFiles = line.split(",");
                try {
                    if (arrayCsvFiles.length > 6) {
                        throw new TooManyFieldsException(" Too many fields in " + Arrays.toString(arrayCsvFiles));
                    } else if (arrayCsvFiles.length < 6) {
                        throw new TooFewFieldsException(" Too few fields in " + Arrays.toString(arrayCsvFiles));
                    } else {
                        PrintWriter csvWriter = null;


                        switch (arrayCsvFiles[4]) {
                            case "CCB":
                                csvWriter = new PrintWriter(new FileOutputStream(Cartoon_Comics, true));
//                                csvWriter.print(Arrays.toString(arrayCsvFiles));
                                break;
                            case "HCB":
                                csvWriter = new PrintWriter(new FileOutputStream(Hobbies_Collectives, true));
//                                csvWriter.print(Arrays.toString(arrayCsvFiles));
                                break;
                            case "MTV":
                                csvWriter = new PrintWriter(new FileOutputStream(Movies_Tv_Books, true));
//                                csvWriter.print(Arrays.toString(arrayCsvFiles));
                                break;
                            case "MRB":
                                csvWriter = new PrintWriter(new FileOutputStream(Music_Radio_Books, true));
//                                csvWriter.print(Arrays.toString(arrayCsvFiles));
                                break;
                            case "NEB":
                                csvWriter = new PrintWriter(new FileOutputStream(Nostalgia_Eclectic_Books, true));
//                                csvWriter.print(Arrays.toString(arrayCsvFiles));
                                break;
                            case "OTR":
                                csvWriter = new PrintWriter(new FileOutputStream(Old_Time_Radio_Books, true));
//                                csvWriter.print(Arrays.toString(arrayCsvFiles));
                                break;
                            case "SSM":
                                csvWriter = new PrintWriter(new FileOutputStream(Sports_Sports_Memorabilia, true));
//                                csvWriter.println(Arrays.toString(arrayCsvFiles));
                                break;
                            case "TPA":
                                csvWriter = new PrintWriter(new FileOutputStream(Trains_Planes_Automobiles, true));
//                                csvWriter.println(Arrays.toString(arrayCsvFiles));
                                break;
                        }

                        if (csvWriter != null) {
                            String csvLine = String.join(",", arrayCsvFiles); // Proper CSV format
                            csvWriter.println(csvLine);
                            csvWriter.close();
                        }
                    }
                } catch (TooFewFieldsException e) {
                    System.out.println(e);
                } catch (TooManyFieldsException e) {
                    System.out.println(e);
                }


            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("CSVReader files not found" + e);
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

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
     * CSV reader for part2. Reading the csv files created in Part 1.
     *
     * @param fileName
     */
    public static void CSVReaderPart2(String fileName) {
        BufferedReader reader = null;
        ObjectOutputStream[] outputStreams = new ObjectOutputStream[8];
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            int Year = 0;


            while ((line = reader.readLine()) != null) {

                outputStreams[0] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Cartoons_Comics.csv.ser", true));
                outputStreams[1] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Hobbies_Collectives.csv.ser", true));
                outputStreams[2] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Movies_Tv_Books.csv.ser", true));
                outputStreams[3] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Music_Radio_Books.csv.ser", true));
                outputStreams[4] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Nostalgia_Eclectic_Books.csv.ser", true));
                outputStreams[5] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Old_Time_Radio_Books.csv.ser", true));
                outputStreams[6] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Sports_Sports_Memorabilia.csv.ser", true));
                outputStreams[7] = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Trains_Planes_Automobiles.csv.ser", true));

                String[] arrayCsvFiles = line.split(",");
                String yearString = arrayCsvFiles[5].replaceAll("\\D+", "");
                double price = Double.parseDouble(arrayCsvFiles[2]);

                if (price < 0) {
                    throw new BadPriceException("The price is negative. Bad Price!");
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

                Book book = new Book(arrayCsvFiles[0], arrayCsvFiles[1], price, arrayCsvFiles[3], arrayCsvFiles[4], Year);

//                ObjectOutputStream oos = null;
                try {
                    switch (book.getGenre()) {
                        case "CCB":
//                            oos = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Cartoons_Comics.csv.ser", true));
                            outputStreams[0].writeObject(book);
                            break;
                        case "HCB":
//                            oos = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Hobbies_Collectives.csv.ser", true));
                            outputStreams[1].writeObject(book);
                            break;
                        case "MTV":
//                            oos = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Movies_Tv_Books.csv.ser", true));
                            outputStreams[2].writeObject(book);
                            break;
                        case "MRB":
//                            oos = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Music_Radio_Books.csv.ser", true));
                            outputStreams[3].writeObject(book);
                            break;
                        case "NEB":
//                            oos = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Nostalgia_Eclectic_Books.csv.ser", true));
                            outputStreams[4].writeObject(book);
                            break;
                        case "OTR":
//                            oos = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Old_Time_Radio_Books.csv.ser", true));
                            outputStreams[5].writeObject(book);
                            break;
                        case "SSM":
//                            oos = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Sports_Sports_Memorabilia.csv.ser", true));
                            outputStreams[6].writeObject(book);
                            break;
                        case "TPA":
//                            oos = new ObjectOutputStream(new FileOutputStream("outputBinaryFiles/Trains_Planes_Automobiles.csv.ser", true));
                            outputStreams[7].writeObject(book);
                            break;
                    }
                    for (ObjectOutputStream item : outputStreams) {
                        item.close();
                    }
//                    if (oos != null) {
//                        oos.writeObject(book);
//                        oos.close();
//                    }
                } catch (IOException e) {
                    System.out.println("IO exception switch");
                    for (ObjectOutputStream item : outputStreams) {
                        if (item != null) {
                            try {
                                item.close();
                            } catch (IOException ex) {
                                System.out.println("IO exception");
                            }
                        }
                    }
//                    if (oos != null) {
//                        try {
//                            oos.close();
//                        } catch (IOException ex) {
//                            System.out.println("IO exception");
//                        }
//                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exceptionn");
        } catch (IOException e) {
            System.out.println("IO exception");
        } catch (BadPriceException e) {
            System.out.println("Bad Price Exception");
        } catch (BadYearException e) {
            System.out.println("Bad Year Exception");
        } catch (BadIsbn10Exception e) {
            System.out.println("Bad ISBN-10 Exception.");
        } catch (BadIsbn13Exception e) {
            System.out.println("Bad ISBN-13 Exception.");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("IO exception");
                }
            }
        }
    }

    public static void do_part2() {
        String[] arr = {"outputFiles/Cartoons_Comics.csv", "outputFiles/Hobbies_Collectibles.csv", "outputFiles/Movies_TV_Books.csv", "outputFiles/Music_Radio_Books.csv", "outputFiles/Nostalgia_Eclectic_Books.csv", "outputFiles/Old_Time_Radio_Books.csv", "outputFiles/Sports_Sports_Memorabilia.csv", "outputFiles/Trains_Planes_Automobiles.csv"};
        for (String item : arr) {
            CSVReaderPart2(item);
        }
    }

    public static Book[] deserialize(ObjectInputStream fileName) {
        Book[] books = new Book[100];
        int counter = 0;

        try {
            while (true) {
                Book book = (Book) fileName.readObject();
                if (book == null) {
                    System.out.println("Error Null book");
                    break; // Break if a null book is encountered
                }
                books[counter] = new Book(book); // Using copy constructor
                counter++;
            }
        } catch (EOFException e) {
            System.out.println("EOF has been reached");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    public static void do_part3() {
        ObjectInputStream[] filenames;
        Book bookFiles[][] = new Book[8][10]; // array of arrays
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
                bookFiles[counter] = deserialize(item);
                counter++;
            }

            //For each loop to close files
            for (ObjectInputStream item : filenames) {
                item.close();
            }


            // Count amount of books in lists

            for (Book[] item : bookFiles) {
                int bookCounter = 0;
                for (Book innerItem : item) {
                    if (innerItem != null && !innerItem.isEmpty())
                        bookCounter++;
                }
                System.out.println("items in book List: " + bookCounter);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {

        //csvReader();
        do_part1();
        do_part2();
//        do_part3();


    }
}