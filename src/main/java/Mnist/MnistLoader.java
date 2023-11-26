package Mnist;

import java.io.*;

public class MnistLoader {
    /* type
    0 = train
    1 = test
     */

    BufferedReader br;
    String line;

    public MnistLoader(int type) throws IOException {
        br = new BufferedReader(new FileReader("src/main/Resources/" + (type==0?"mnist_train.csv":"mnist_test.csv")));
        line = br.readLine(); //skip header line
    }

    public Mnist getNextMnist() throws IOException {
        if((line = br.readLine()) != null){
            String[] values = line.split(",");
            int actualValue = Integer.parseInt(values[0]);
            double[] data = new double[values.length - 1];
            for (int i = 1; i < values.length; i++) {
                data[i - 1] = Double.parseDouble(values[i]);
            }
            return new Mnist(data,actualValue);
        }
        return null;
    }


}


