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

    public static void CSVReader(String fileName) {
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
                    }
                    if (arrayCsvFiles.length <= 5) {
                        throw new TooFewFieldsException(" Too few fields in " + Arrays.toString(arrayCsvFiles));
                    }
                } catch (TooManyFieldsException | TooFewFieldsException e) {
                    System.out.println(e);
                }

                PrintWriter csvWriter = null;


                switch (arrayCsvFiles[4]) {
                    case "CCB":
                        csvWriter = new PrintWriter(new FileOutputStream(Cartoon_Comics, true));
                        csvWriter.print(Arrays.toString(arrayCsvFiles));
                        break;
                    case "HCB":
                        csvWriter = new PrintWriter(new FileOutputStream(Hobbies_Collectives, true));
                        csvWriter.print(Arrays.toString(arrayCsvFiles));
                        break;
                    case "MTV":
                        csvWriter = new PrintWriter(new FileOutputStream(Movies_Tv_Books, true));
                        csvWriter.print(Arrays.toString(arrayCsvFiles));
                        break;
                    case "MRB":
                        csvWriter = new PrintWriter(new FileOutputStream(Music_Radio_Books, true));
                        csvWriter.print(Arrays.toString(arrayCsvFiles));
                        break;
                    case "NEB":
                        csvWriter = new PrintWriter(new FileOutputStream(Nostalgia_Eclectic_Books, true));
                        csvWriter.print(Arrays.toString(arrayCsvFiles));
                        break;
                    case "OTR":
                        csvWriter = new PrintWriter(new FileOutputStream(Old_Time_Radio_Books, true));
                        csvWriter.print(Arrays.toString(arrayCsvFiles));
                        break;
                    case "SSM":
                        csvWriter = new PrintWriter(new FileOutputStream(Sports_Sports_Memorabilia, true));
                        csvWriter.println(Arrays.toString(arrayCsvFiles));
                        break;
                    case "TPA":
                        csvWriter = new PrintWriter(new FileOutputStream(Trains_Planes_Automobiles, true));
                        csvWriter.println(Arrays.toString(arrayCsvFiles));
                        break;
                }

                if (csvWriter != null) {
                    csvWriter.println(Arrays.toString(arrayCsvFiles));
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

    public static void do_part1() {
        String[] arr = inputFileReader("part1_input_file_names.txt");
        for (String file : arr) {
            if (!is_empty(file)) {
                CSVReader(file);
            }
        }

    }


    public static void main(String[] args) {

        //csvReader();
        do_part1();
//    do_part1();
//    do_part3();


    }
}