package observable;

import java.util.Observable;
import java.util.Observer;

public class TweetSaver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
    }
}
