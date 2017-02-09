package call_center_miasi;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;


public class CallCenter {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Logging logowanie = new Logging();
                logowanie.setVisible(true);
                Toolkit t = Toolkit.getDefaultToolkit();
                Dimension d = t.getScreenSize();
                logowanie.setLocation((d.width / 4), (d.height / 4));

            }
        }
        );
    }

}
