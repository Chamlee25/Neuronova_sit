import Mnist.Mnist;
import Mnist.MnistLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Main {
    private static double time =0;
    public static void main(String[] args) throws IOException {
        for(int i =0; i< 1000; i++){
            Network n1 = new Network();
            // train(n1);
            train(n1);
            double accuracy = test(n1);

            System.out.println(time);
            save(n1,accuracy + ".net");


            {   // get not chaning file
                Network n2 = new Network(accuracy + ".net");
                File f = new File(accuracy + ".net");
                f.delete();
                accuracy = test(n2);
                save(n2, accuracy + ".net");
                System.out.println(accuracy);
            }
        }
    }

    private static double test(Network network) throws IOException {
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
        return right/100;
    }

    static void train(Network n) throws IOException {
        //train
        MnistLoader trainer = new MnistLoader(0);
        double start = System.currentTimeMillis();
        for(int i =0; i<60000; i++){
            Mnist m = trainer.getNextMnist();
            n.learn(m.data,m.actualValues,0.01);
        }


        time = System.currentTimeMillis() - start;
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
            firstLayerSB.append(n.firstLayer.neurons[i].bias).append("\n");
        }
        
        StringBuilder secondLayerSB = new StringBuilder();
        for(int neuron =0; neuron<10; neuron++){
            for(int weight =0; weight<784; weight++){
                secondLayerSB.append(n.secondLayer.neurons[neuron].weights[weight]).append(",");
            }
            secondLayerSB.append(":").append(n.firstLayer.neurons[neuron].bias).append("\n");
        }
        
        
        StringBuilder thirdLayerSB = new StringBuilder();
        for(int neuron =0; neuron<10; neuron++){
            for(int weight =0; weight<10; weight++){
                thirdLayerSB.append(n.thirdLayer.neurons[neuron].weights[weight]).append(",");
            }
            thirdLayerSB.append(":").append(n.thirdLayer.neurons[neuron].bias).append("\n");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(firstLayerSB).append(";").append(secondLayerSB).append(";").append(thirdLayerSB);

        fw.write(sb.toString());
        fw.close();
    }
}
