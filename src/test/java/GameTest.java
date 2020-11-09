import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

  private Game game;

  @org.junit.jupiter.api.Test
  void getrollFromFile() {
  }

  @org.junit.jupiter.api.Test
  void score() {
  }

  @Test
  public void score_should_be_103(){
    game = new Game();
    ArrayList<Integer> framesPlayed = new ArrayList<>(List.of(0, 3, 5, 0, 9, 1, 2, 5, 3, 2, 4, 2, 3, 3, 4, 6, 10, 10, 2, 5));
    int expected = 103;

    game.getRollsFromFile(framesPlayed);
    game.printAll();

    int actual = game.score();

    assertEquals(expected,actual);
  }

  @Test
  public void score_should_be_300(){
    game = new Game();
    ArrayList<Integer> framesPlayed = new ArrayList<>(List.of(10, 10, 10, 10, 10, 10, 10, 10 , 10 ,10, 10, 10));
    int expected = 300;

    game.getRollsFromFile(framesPlayed);
    game.printAll();
    int actual = game.score();

    assertEquals(expected,actual);
  }

  @Test
  void should_not_give_bonus_points(){
    game = new Game();
    ArrayList<Integer> framesPlayed = new ArrayList<>(List.of(2, 3, 4, 4, 2, 2, 3, 3, 2, 1, 3, 2, 4, 5, 2, 6, 1, 1, 4, 2));

    game.getRollsFromFile(framesPlayed);
    game.printAll();
  }

  @Test
  public void next_to_last_frame_is_spare(){
    game = new Game();
    ArrayList<Integer> framesPlayed = new ArrayList<>(List.of(0, 3, 5, 0, 9, 1, 2, 5, 3, 2, 4, 2, 3, 3, 4, 6, 1, 9, 10, 2, 5));
    int expected = 92;

    game.getRollsFromFile(framesPlayed);
    game.printAll();

    int actual = game.score();

    assertEquals(expected,actual);
  }
}