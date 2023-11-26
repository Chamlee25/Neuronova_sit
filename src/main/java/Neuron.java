public class Neuron {
    public double bias;
    public double[] weights;

    public double[][] weightsIncreaseData;
                    // [ weight number ] [10 values which average am gonna use]
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

    public void updateLastNeuron(double error, double learningRate, double[] input){
        for(int i =0; i< 10; i++){
            weights[i] += error*input[i]*learningRate;
        }
        bias += error*learningRate;
    }
}