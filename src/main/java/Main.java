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
        double[] p3 = network.learn(m1.data,m1.actualValues,0.01);
        double[] p4 = network.predict(m2.data);

        System.out.println(m1.actualvalue);

        for (int i =0; i<10; i++) {
            System.out.print("Číslo " + i + ":  ");
            System.out.printf("%.8f", p1[i]*100);
            System.out.println("%");
        }
        System.out.println("\n");

        for (int i =0; i<10; i++) {
            System.out.print("Číslo " + i + ":  ");
            System.out.printf("%.8f", p2[i]*100);
            System.out.println("%");
        }
        System.out.println("\n");

        for (int i =0; i<10; i++) {
            System.out.print("Číslo " + i + ":  ");
            System.out.printf("%.8f", p3[i]*100);
            System.out.println("%");
        }
        System.out.println("\n");
        for (int i =0; i<10; i++) {
            System.out.print("Číslo " + i + ":  ");
            System.out.printf("%.8f", p4[i]*100);
            System.out.println("%");
        }





    }
}
