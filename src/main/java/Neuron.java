public class Neuron {
    public double bias;
    public double[] weights;
    public ActivationFunction activationFunction;
    public Neuron(String activationFunctionName, int weightCount){
        this.bias = Math.random()-0.5;
        weights = new double[weightCount];
        for(int i = 0; i<weightCount; i++){
            weights[i] = (Math.random() * 2) - 1;
        }
        this.activationFunction = new ActivationFunction(activationFunctionName);
    }
    public double think(double[] previousInputs) throws IndexOutOfBoundsException{
        if(previousInputs.length != weights.length)
            throw new IndexOutOfBoundsException();
        double result = bias;
        for(int i =0; i<previousInputs.length;i++){
            result += previousInputs[i] * weights[i];
        }
        return activationFunction.activate(result);
    }
}