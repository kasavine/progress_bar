import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProgressBar {

  private static Random rand = new Random();

  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Please provide more that 1 argument");
    } else {
      int amountOfElements = StringUtils.toInt(args[0].toCharArray());

      var start = System.currentTimeMillis();
      var currentTime = start;

      for (int j = 0; j <= amountOfElements; j++) {
        currentTime = System.currentTimeMillis();
        var timeDelta = currentTime - start;
        var timePerItem = j == 0 ? 0 : timeDelta / j;
        var remainingTimeInSec = ((amountOfElements - j) * timePerItem) / 1000;
        // (amountOfElements - j) * sec;
        int [] remainingTime = countRemainingTime(remainingTimeInSec);
        int sec = getRandElementFromArray(args);
        //                              15     0 1 .. 15
        System.out.print(printLine(amountOfElements, j) + "] "
            + j + "/" + amountOfElements +  ", ETA: "
            + String.format("%02d:%02d:%02d", remainingTime[2], remainingTime[1], remainingTime[0])
            + "\r"); //
        try {
          TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private static int[] makeTimeArray(String[] args) {
    // 0 0 0 0 0 0 0 0 0
    int[] result = new int[args.length - 1];
    int j = 0;
    for (int i = 1; i < args.length; i++) {
      result[j++] = StringUtils.toInt(args[i].toCharArray());
    }
    return result;
  }

  private static int[] countRemainingTime(long remainingTimeInSec) {
    int result[] = new int[3];
    result[0] = (int) remainingTimeInSec % 60; // hour
    result[1] = (int) (remainingTimeInSec / 60) % 60; // min
    result[2] = (int) ((remainingTimeInSec / 60) / 60) % 24; // sec
    return result;
  }

  private static int getRandElementFromArray(String[] args){
    int[] items = makeTimeArray(args);
    int randTime = rand.nextInt(items.length);
    return items[randTime];
  }
  //                                      15                0 1 .. 15
  private static String printLine(int amountOfElements, int remainIteration) {
    StringBuilder progressBar = new StringBuilder();
    int totalPercent = (100 / (amountOfElements)) * remainIteration; //
    int lengthProgressBar = totalPercent;

    if (remainIteration == amountOfElements) {
      totalPercent = 100;
    }
    progressBar.append(String.format("% " + 4 + "d", totalPercent) + "%  [");
    for (int i = 0; i <= 100; i++) {
      if (i < lengthProgressBar) {
        progressBar.append("=");
      }
      if (i > lengthProgressBar || lengthProgressBar == 0) {
        progressBar.append(" ");
      }
      if (i == lengthProgressBar && lengthProgressBar != 0) {
        progressBar.append(">");
      }
    }
    return progressBar.toString();
  }
}