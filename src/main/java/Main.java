import Mnist.Mnist;
import Mnist.MnistLoader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Network network = new Network();
        MnistLoader ml = new MnistLoader(0);
        Mnist m1 = ml.getNextMnist();
        Mnist m2 = ml.getNextMnist();

        double[] p1 = network.predict(m1.data);
        double[] p2 = network.predict(m2.data);

        for (int i =0; i<10; i++) {
            System.out.print("Číslo " + i + ":  ");
            System.out.printf("%.5f", p1[i]*100);
            System.out.println("%");
        }
        System.out.println("\n");
        for (int i =0; i<10; i++) {
            System.out.print("Číslo " + i + ":  ");
            System.out.printf("%.5f", p2[i]*100);
            System.out.println("%");
        }


    }
}
