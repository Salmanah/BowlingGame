import java.util.ArrayList;

public class Game {
  private final int TOTAL_FRAMES = 10;
  private final int TOTAL_NR_PINS = 10;
  private Frame [] frames = new Frame[TOTAL_FRAMES];

  public void getRollsFromFile(ArrayList<Integer> rollsFromFile){
    int rollIndex = 0;

    for (int frame = 0; frame < TOTAL_FRAMES; frame++){
      if(frame == 9 && rollsFromFile.get(rollIndex) + rollsFromFile.get(rollIndex+1) >= TOTAL_NR_PINS){ // strike or spare on last frame
        frames[frame] = new Frame(rollsFromFile.get(rollIndex),rollsFromFile.get(rollIndex+1),rollsFromFile.get(rollIndex+2));
        rollIndex += 2;
      }else if(rollsFromFile.get(rollIndex) == TOTAL_NR_PINS){ // single strike
        frames[frame] = new Frame(10);
        rollIndex ++;
      }else{ // regular throws
        frames[frame] = new Frame(rollsFromFile.get(rollIndex), rollsFromFile.get(rollIndex+1));
        rollIndex += 2;
      }
    }
  }

  int score(){
    int score = 0;
    int lastFrame = 9;
    int nextToLast = 8;

    for (int frame = 0; frame < TOTAL_FRAMES; frame++){
      if(frame == lastFrame){
        score = calculateLastFrame(frame, score);
      }else if(frame == nextToLast && frames[frame].getFirstRoll() == TOTAL_NR_PINS){
        score += TOTAL_NR_PINS + frames[frame+1].getFirstRollCalculations() + frames[frame+1].getSecondRollCalculations();
      }else{
       score = calculateRest(frame, score);
      }
    }
    return score;
  }
  private int calculateLastFrame(int frame, int score){
    if(isStrike(frame)){
      score += TOTAL_NR_PINS + frames[frame].getSecondRollCalculations() + frames[frame].getThirdRollCalculations();
    }else if(isSpare(frame)){
      score += TOTAL_NR_PINS + frames[frame].getThirdRollCalculations();
    }
    return score;
  }


  private int calculateRest(int frame, int score){
    if(frames[frame].getFirstRoll() == TOTAL_NR_PINS){
      score += TOTAL_NR_PINS +  frames[frame+1].getFirstRollCalculations() + frames[frame+2].getFirstRollCalculations();
    }else if(frames[frame].getFirstRoll() + frames[frame].getSecondRoll() == TOTAL_NR_PINS ){
      score += TOTAL_NR_PINS + frames[frame+1].getFirstRollCalculations();
    }else{
      score += frames[frame].getFirstRollCalculations() + frames[frame].getSecondRollCalculations();
    }
    return score;
  }


  private boolean isStrike(int frameIndex){
    return frames[frameIndex].getFirstRoll() == TOTAL_NR_PINS;
  }

  private boolean isSpare(int frameIndexOne){
    return frames[frameIndexOne].getFirstRoll() + frames[frameIndexOne].getSecondRoll() >= TOTAL_NR_PINS;
  }

  String getSymbolForRegular(int roll){
    if (roll == 10){
      return "X";
    }else if(roll == 0){
      return "-";
    }else if(roll == -1){
      return "";
    }else {
      return ""+roll;
    }
  }

  String getSymbolForLastTwo(int firstroll, int secondRoll){
     if(firstroll+secondRoll == 10){
      return ""+firstroll+", /";
    }else{
      return getSymbolForRegular(firstroll)+", "+getSymbolForRegular(secondRoll);
    }
  }

  public void printAll(){
    System.out.println(" | F1   | F2   | F3   | F4   | F5   | F6   | F7   | F8   | F9   |  F10    |");
    for (int i = 0; i < frames.length; i++) {
      if(i == 9){
        if(frames[i].getFirstRoll() == 10){
          System.out.print(" | X, "+getSymbolForLastTwo(frames[i].getSecondRoll(), frames[i].getThirdRoll()));
        }else if(frames[i].getFirstRoll() + frames[i].getSecondRoll() == 10){
          System.out.print(" | "+frames[i].getFirstRoll()+", /"+getSymbolForRegular(frames[i].getThirdRoll()));
        }else{
          System.out.print(" | "+getSymbolForRegular(frames[i].getFirstRoll())+", "+getSymbolForRegular(frames[i].getSecondRoll()));
        }
      }else{
        if(frames[i].getFirstRoll() == 10){
          System.out.print(" | "+getSymbolForRegular(frames[i].getFirstRoll()));
        }else{
          System.out.print(" | "+getSymbolForRegular(frames[i].getFirstRoll())+", "+getSymbolForRegular(frames[i].getSecondRoll()));
        }
      }
    }
    System.out.println();
    System.out.println("Score: "+score());
  }
}
