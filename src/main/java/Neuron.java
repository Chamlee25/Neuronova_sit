import java.io.Serializable;

public class Neuron implements Serializable {
    public double bias;
    public double[] weights;
    public double error;

    public Neuron(int weightCount){
        this.bias = Math.random()-0.5;
        weights = new double[weightCount];
        for(int i = 0; i<weightCount; i++){
            weights[i] = (Math.random() * 2) - 1;
        }
    }

    public double think(double[] previousInputs) throws IndexOutOfBoundsException{
        if(previousInputs.length != weights.length)
            throw new IndexOutOfBoundsException(); // if Neuron receives more or less input than can handle
        double result = bias;
        for(int i =0; i<previousInputs.length;i++){
            result += previousInputs[i] * weights[i];
        }
        return result;
    }


}