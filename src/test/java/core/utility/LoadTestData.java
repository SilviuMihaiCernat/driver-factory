package core.utility;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class LoadTestData {
        private static final String  testDataFileLocation = "src/test/resources/environment.test.data/";
        private static final String  testDataFileSeparator = ";";

        /**
         * This returns a hash map that is parsed environment.test.data from a given file with the ";" separator.
         * File location is taken from the constant above
         */
        public static LinkedHashMap<String, String> loadTestData(String environment) {
            LinkedHashMap<String, String> testData = new LinkedHashMap<>();
            String fileName = getEnvFileName(environment);
            try(Scanner sc = new Scanner(new FileInputStream(testDataFileLocation +
                    fileName))){
                while(sc.hasNext()){
                    String input = sc.nextLine();
                    String[] testDataRow = input.split(testDataFileSeparator);
                    if (!testDataRow[0].isEmpty()){
                        testData.put(testDataRow[0], testDataRow[1]);
                    }
                }
            }
            catch (FileNotFoundException e){
                System.out.println("Error in LoadTestData : " + e.toString() + " in filename: " +
                        fileName);
            }
            return testData;
        }

        /**
         * This method retrieves the correct file if given a specific system property introduced in the test run by TestNG or Maven (-Dserver=SIT)
         */
        public static String getEnvFileName(String environment) {
            String fileName;
            switch(environment.toLowerCase()){
                case "sit":
                    fileName = "testDataSIT.csv";
                    break;
                default:
                    fileName = "testDataSIT.csv";
                    break;
            }
            return fileName;
        }
}
