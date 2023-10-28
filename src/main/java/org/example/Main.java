package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
//---------------------------------------------
//    /**
//     *
//     * @param inputFilesNames this method reads the file part1_input_file_names.txt that contains all the csv files inside.
//     * @return
//     */
//    private static String[] readInputFileNames(String inputFilesNames) {
//        String Part1_txt = "part1_input_file_names.txt";
//        String[] csvFiles = new String[100];
//        int i = 0;
//        try (BufferedReader reader = new BufferedReader(new FileReader(Part1_txt))) {
//            String line = reader.readLine(); //reads the number which is n of top of part1_input_file_names.txt
//            while ((line = reader.readLine()) != null) { //Keeps reading the line till it reaches an empty line
//                csvFiles[i++] = line.trim();
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading the main input file: " + e.getMessage());
//
//
//        }
//        return Arrays.copyOf(csvFiles, i);
//    }
//
//    private static void CsvFileEmpty(String csvFilePath) {
//
//        try {
//            long lineCount = Files.lines(Paths.get(csvFilePath)).count();
//            if (lineCount == 0) {
//                System.out.println("The CSV file " + csvFilePath + " is empty.");
//            } else {
//                System.out.println("The CSV file " + csvFilePath + " is not empty.");
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading the CSV file " + csvFilePath + ": " + e.getMessage());
//        }
//    }
//     public static void do_part1()
//     {
//     String Part1_txt = "part1_input_file_names.txt";
//     String [] csvFilePaths = readInputFileNames(Part1_txt);
//        for (int i =0 ; i< csvFilePaths.length && csvFilePaths[i] != null; i++){
//            CsvFileEmpty(csvFilePaths[i]);
//        }
//     }
//---------------------------------------------------
// ---------------------------------------------------


    public static boolean is_empty(String fileName) {
        boolean isEmpty = false;
        //     long lineCount = Files.lines(Paths.get(csvFilePath)).count();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            long lineCount = reader.lines().count();
            if (lineCount == 0 ) {
                isEmpty = true;
                System.out.println("FIle is empty " + fileName);
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
    public static String[] fileReader(String fileName)// Function to check if its empty or not
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

    public static void do_part1() {
        String[] arr = fileReader("part1_input_file_names.txt");
        for (String file : arr) {
            is_empty(file);


        }
    }

    //---OG


//        public static void do_part1() //throws IOException
//        {
//            try{
//
//                BufferedReader reader = new BufferedReader(new FileReader("part1_input_file_names.txt"));
//
//                String line = reader.readLine(); // first line is always the amount of texts
//                int counter = 0;
//                String [] csvFiles = new String[Integer.parseInt(line)];
//                while((line = reader.readLine()) != null)
//                {
//                    //System.out.println(line);
//                    csvFiles[counter] = line;
//                    counter++;
//                }
//
//                for (String item: csvFiles) {
//                    System.out.println(item);
//                }
//
//                reader.close();
//
//            }
//            catch (FileNotFoundException e){
//                System.out.println("part1 input files not found");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//
//        }


    public static void main(String[] args) {


        //csvReader();
        do_part1();
//    do_part1();
//    do_part3();


    }
}