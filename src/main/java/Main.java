import Mnist.Mnist;
import Mnist.MnistLoader;

import java.io.*;


public class Main {
    private static double time =0;
    public static void main(String[] args) throws IOException {
        for(int i =0; i<Integer.parseInt(args[0]); i++){
            Network n1 = new Network();
            train(n1);
            //Mnist m = new MnistLoader(0).getNextMnist();
            //n1.learn(m.data,m.actualValues,0.01);
            double accuracy = test(n1);
            System.out.println(time);
            save(n1,accuracy + ".net");
            System.out.println(accuracy);
            double[] a = n1.predict(new MnistLoader(1).getNextMnist().data);
            for(int j =0; j<10; j++){
                System.out.println(a[j]);
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
            n.learn(m.data,m.actualValues,0.0001);
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
        FileOutputStream fous = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fous);
        out.writeObject(n);
        out.close();
        fous.close();
    }

    private static Network load(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Network n =  (Network) in.readObject();
        in.close();
        fileIn.close();
        return n;
    }
}
