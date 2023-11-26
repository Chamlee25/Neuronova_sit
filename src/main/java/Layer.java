import java.util.HashMap;

public class Layer {
    public Neuron[] neurons;
    public int weightCount;
    public ActivationFunction activationFunction;

    /* ID
     * 0 -> input layer
     * 1 -> hidden layer
     * 2 -> output layer
     */
    public int layerID;


    public Layer(int ID, int NeuronCount, int weightCount, String activationFunctionName){
        this.weightCount = weightCount;
        this.layerID = ID;
        this.activationFunction = new ActivationFunction(activationFunctionName);
        neurons = new Neuron[NeuronCount];
        for(int i = 0; i< NeuronCount; i++){
            neurons[i] = new Neuron(weightCount);
        }

    }
    public double[] activateLayer(double[] inputs){
        //constant
        int size = inputs.length;
        //checking if neuron has right number of inputs
        if(size != neurons[0].weights.length)
            throw new IndexOutOfBoundsException();
        double[] outputs = new double[size];
        for(int i =0; i<size; i++){
            outputs[i] = neurons[i].think(inputs);
        }
        if(activationFunction.AF_ID!=4){
            for(int i =0; i<size; i++){
                outputs[i] = activationFunction.activate(outputs[i]);
            }
            return outputs;
        }
        for(int i =0; i<size; i++){
            outputs[i] = activationFunction.activate(outputs[i],outputs);
        }
        return outputs;
    }
}
