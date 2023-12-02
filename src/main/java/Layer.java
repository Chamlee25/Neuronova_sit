import java.io.Serializable;

public class Layer implements Serializable{
    public Neuron[] neurons;
    public int weightCount;
    private final int layerSize;

    public Layer(int NeuronCount, int weightCount){
        this.weightCount = weightCount;
        neurons = new Neuron[NeuronCount];
        for(int i = 0; i< NeuronCount; i++){
            neurons[i] = new Neuron(weightCount);
        }
        layerSize = neurons.length;
    }
    public double[] firstLayerThink(double[] inputs){
        if(inputs.length != neurons.length){
            throw new IllegalArgumentException();
        }
        double[] result = new double[inputs.length];
        for(int i =0; i<inputs.length;i++ ){
            double[] input = {inputs[i]};
            result[i] = neurons[i].think(input);
        }
        for (int i =0; i<result.length;i++ ){
            result[i] = ActivationFunction.sigmoid(result[i]);
        }
        return result;
    }
    public double[] secondLayerThink(double[] inputs){
        if(weightCount != inputs.length)
            throw new IndexOutOfBoundsException();
        double[] outputs = new double[layerSize];
        for(int i = 0; i< layerSize; i++){
            outputs[i] = neurons[i].think(inputs);
        }
        for(int i = 0; i< layerSize; i++){
            outputs[i] = ActivationFunction.relu(outputs[i]);
        }
        return outputs;
    }

    public double[] thirdLayerThink(double[] inputs){
        if(weightCount != inputs.length)
            throw new IndexOutOfBoundsException();
        double[] outputs = new double[layerSize];
        for(int i = 0; i< layerSize; i++){
            outputs[i] = neurons[i].think(inputs);
        }
        outputs = ActivationFunction.softmax(outputs);
        return outputs;
    }




}
