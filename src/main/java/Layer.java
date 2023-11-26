import java.util.HashMap;

public class Layer {
    public Neuron[] neurons;

    private static HashMap<int,Layer> layerMap = new HashMap<int, Layer>();
    public int layerID;
    /** ID
     * 0 -> input layer
     * 1 -> hidden layer
     * 2 -> output layer
     */

    public Layer(int ID, int NeuronCount, String activationFunctionName){
        layerMap.put(ID,this);
        neurons = new Neuron[NeuronCount];
        for(int i = 0; i< NeuronCount; i++){
            neurons[i] = new Neuron(activationFunctionName, getLayer(ID-1).neurons.length);
        }
        this.layerID = ID;
    }

    public static Layer getLayer(int ID){
        return layerMap.get(ID);
    }

    public double[] activateLastLayer(double[] inputs){
        if(inputs.length != neurons[0].weights.length)
            throw new IndexOutOfBoundsException();
        double[] results = new double[neurons.length];
        double[] activatedResults = new double[neurons.length];
        for(int i =0; i<neurons.length; i++){
            results[i] = neurons[i].think(inputs);
        }
        for(int i =0; i<neurons.length; i++){
            activatedResults[i] = neurons[i].activationFunction.activate(results[i],results);
        }
        return activatedResults;
    }

}
