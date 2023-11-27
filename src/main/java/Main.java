import Mnist.Mnist;
import Mnist.MnistLoader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Network network = new Network();
        MnistLoader trainer = new MnistLoader(0);
        //train
        for(int i =0; i<60000; i++){
            Mnist m = trainer.getNextMnist();
            network.learn(m.data,m.actualValues,0.01);
        }

        //test accuracy
        double right =0;
        MnistLoader tester = new MnistLoader(1);
        for(int i =0; i<10000; i++){
            Mnist m = tester.getNextMnist();
            double[] result = network.predict(m.data);
            if(highestValue(result) == m.actualvalue){
                right++;
            }
        }
        System.out.println("Accouracy of network : " + right/100 + "%.");






    }

    private static int highestValue(double[] result) {
        double x = result[0];
        int index = 0;
        for(int i = 1; i<result.length; i++){
            if(x < result[i]){
                x = result[i];
                index = i;
            }
        }
        return index;
    }
}
