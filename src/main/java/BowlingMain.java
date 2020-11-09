import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BowlingMain {
  public static void main(String[] args) throws FileNotFoundException {

    Scanner sc = new Scanner(new File(args[0]));
    ArrayList<Integer> fileScore = new ArrayList<>();

    while(sc.hasNext()){
      try{
        fileScore.add(Integer.parseInt(sc.next().replace(",","")));
      }catch (NumberFormatException e){
        System.out.println("Not a number");
      }
    }
    Game game = new Game();
    game.getRollsFromFile(fileScore);
    System.out.println("score "+game.score());

  }
}
