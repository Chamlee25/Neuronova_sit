import java.util.Optional;

public class Neuron {
    public double bias;
    public double[] weights;

    boolean lastLayer;
    public ActivationFunction activationFunction; //Object reprezentation of activation function
    public Neuron(String activationFunctionName, int weightCount, boolean... lastLayer){
        this.bias = Math.random()-0.5;
        weights = new double[weightCount];
        for(int i = 0; i<weightCount; i++){
            weights[i] = (Math.random() * 2) - 1;
        }
        this.activationFunction = new ActivationFunction(activationFunctionName);
        if(lastLayer.length>0){
            this.lastLayer = lastLayer[0];
        }
    }
    public double think(double[] previousInputs) throws IndexOutOfBoundsException{
        if(previousInputs.length != weights.length)
            throw new IndexOutOfBoundsException(); // if Neuron receives more or less input than can handle

        double result = bias;
        for(int i =0; i<previousInputs.length;i++){
            result += previousInputs[i] * weights[i];
        }
        if(lastLayer){
            return result;
        }
        return activationFunction.activate(result);  //activating the result
    }
}