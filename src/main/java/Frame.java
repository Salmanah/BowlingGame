public class Frame {
  private int firstRoll;
  private int secondRoll;
  private int thirdRoll;

  Frame(int firstRoll){
    this.firstRoll = firstRoll;
    this.secondRoll = -1;
    this.thirdRoll = -1;
  }

  Frame(int firstRoll, int secondRoll){
    this.firstRoll = firstRoll;
    this.secondRoll = secondRoll;
    this.thirdRoll = -1;
  }
  Frame(int firstRoll, int secondRoll, int thirdRoll){
    this.firstRoll = firstRoll;
    this.secondRoll = secondRoll;
    this.thirdRoll = thirdRoll;
  }

  int getFirstRoll(){
      return firstRoll;
  }
  int getSecondRoll(){

      return secondRoll;

  }
  int getThirdRoll(){
      return thirdRoll;
  }

  int getFirstRollCalculations(){
    if (firstRoll != -1){
      return firstRoll;
    }
    return 0;
  }
  int getSecondRollCalculations(){
    if (secondRoll != -1){
      return secondRoll;
    }
    return 0;
  }
  int getThirdRollCalculations(){
    if(thirdRoll != -1){
      return thirdRoll;
    }
    return 0;
  }

  int getFrameScore(){
    if(secondRoll != -1){
      return firstRoll+secondRoll;
    }else{
      return firstRoll;
    }

  }
}
