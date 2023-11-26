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

}
