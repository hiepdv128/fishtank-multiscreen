package control;

import model.Fish;

import java.util.List;

/**
 * Created by hiepdv on 12/12/2016.
 */
public class FishRunner implements Runnable {

    private List<Fish> fishs;

    public FishRunner(List<Fish> fishs) {
        this.fishs = fishs;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (Fish fish : fishs) {
                    fish.swim();
                }

                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
