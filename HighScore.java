import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class consists exclusively of static methods that are used to read and write
 * High Scores to a textfile for the purpose of retrieval at a later stage.
 *
 * @author  Bradley Fourie
 */

public class HighScore {

    /**
     * Takes in an ArrayList of Integers as a parameter, and then this method checks if a
     * file called high_scores.txt exists, if not then it creates the file, then each entry
     * of the ArrayList is converted to a type String and then this String value is stored
     * on a new line. If the next entry of the ArrayList is not the last entry in the ArrayList
     * then the index of the BufferedReader points to the next line in the textfile.
     *
     * @param listHighScores the ArrayList of high scores to be written to the textfile
     *
     */
    public static void writeHighScores(ArrayList<Integer> listHighScores) {
        File file = new File("high_scores.txt");

        try{

            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(int i=0; i<listHighScores.size(); i++){
                bufferedWriter.write( String.valueOf(listHighScores.get(i)) );

                if(i != listHighScores.size()-1) {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            fileWriter.close();

        }catch (IOException ioe){
            //This exception occurs if we try to write to a textfile that doesnt exist, however
            // in the code above we have checked that case by creating the textfile ourselves,
            // therefore we do not have to do anything this catch statement, this is only
            // here for syntax purposes.
        }

    }

    /**
     * Checks if a file called high_scores.txt exists if the file doesnt exists, return an empty
     * ArrayList of type Integer, otherwise read each individual line from the textfile and convert
     * the line from type String to type Integer and add the Integer to the ArrayList, and then
     * return the ArrayList of high scores.
     *
     * @param
     * @return an ArrayList of Integers that have been read from the textfile that stores
     * the high scores.
     */
    public static ArrayList<Integer> readHighScores(){
        ArrayList<Integer> listHighScores = new ArrayList<>();

        File file = new File("high_scores.txt");
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while( (line) != null){
                listHighScores.add( Integer.parseInt(line) );
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            // This exception occurs if we try to read from a textfile that doesnt exist, however
            // in the code above we have checked that case by creating the textfile ourselves,
            // therefore we do not have to do anything this catch statement, this is only
            // here for syntax purposes.
        }

        return listHighScores;
    }

    /**
     * This method takes in an integer and compares this value with each other value in the
     * ArrayList of high scores to see if it is bigger than or smaller than the 5 current
     * values in the ArrayList. The value is added as the last entry in the ArrayList, the
     * ArrayList is then sorted in descending order using the sort() method of the ArrayList
     * class, and the last (smallest) value in the ArrayList is removed.
     *
     * @param score the new score that the player achieved in latest game
     * @return the ArrayList of the new 5 new HighScores
     * @see ArrayList
     */
    public static ArrayList<Integer> checkHighScore(int score){
        Integer currentScore = new Integer(score);

        ArrayList<Integer> listHighScores = readHighScores();

        listHighScores.add(currentScore);

        Collections.sort(listHighScores, Collections.reverseOrder());

        if(listHighScores.size() == 6){
            listHighScores.remove(5);
        }

        writeHighScores(listHighScores);

        return listHighScores;
    }

}
