import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HighScore {

    public HighScore(){
        //empty constructor
    }
    //method to check if it is a highscore
    //method to print from textfile
    //method to write to and create textfile
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
                //to make sure a newline isnt created that does not contain any text
                if(i != listHighScores.size()-1) {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();

        }catch (IOException ioe){
        }

    }

    //reads the textfile and populates an arraylist from it
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

        } catch (FileNotFoundException fnfe) {
            //do nothing if the file isnt found
        } catch (IOException ioe) {
            //do nothing
        }

        return listHighScores;
    }

    //takes in an argument high score and then checks if it is a high score and returns new arraylist
    public static ArrayList<Integer> checkHighScore(int score){
        Integer currentScore= new Integer(score);

        ArrayList<Integer> listHighScores = readHighScores();

        listHighScores.add(currentScore);

        Collections.sort(listHighScores, Collections.reverseOrder());

        if(listHighScores.size() == 6){
            listHighScores.remove(5);
        }

        //write to textfile
        writeHighScores(listHighScores);

        return listHighScores;
    }

}
