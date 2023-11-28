import Mnist.Mnist;
import Mnist.MnistLoader;

import java.io.File;
import java.io.FileWriter;
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
        double accuracy = right/100;
        System.out.println(accuracy);
        //save(network,accuracy + ".net");







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

    private static void save(Network n, String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        StringBuilder firstLayerSB = new StringBuilder();
        for(int i =0; i<784; i++){
            firstLayerSB.append(n.firstLayer.neurons[i].weights[0]).append(":");
            firstLayerSB.append(n.firstLayer.neurons[i].bias).append(",");
        }
        
        StringBuilder secondLayerSB = new StringBuilder();
        for(int i =0; i<10; i++){
            for(int j =0; j<784; j++){
                secondLayerSB.append(n.secondLayer.neurons[i].weights[j]).append(":");
            }
            secondLayerSB.append(n.firstLayer.neurons[i].bias).append(",");


        }

        StringBuilder thirdLayerSB = new StringBuilder();
        for(int i =0; i<10; i++){
            for(int j =0; j<10; j++){
                thirdLayerSB.append(n.thirdLayer.neurons[i].weights[j]).append(":");
            }
            thirdLayerSB.append(n.thirdLayer.neurons[i].bias).append(",");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(firstLayerSB).append(";").append(secondLayerSB).append(";").append(thirdLayerSB);

        fw.write(sb.toString());
        fw.close();
    }
}
