public class Layer {
    public Neuron[] neurons;
    public int weightCount;


    private final int layer_size;
    private final int  total_data_required;



    public Layer(int NeuronCount, int weightCount){

        this.weightCount = weightCount;
        neurons = new Neuron[NeuronCount];
        for(int i = 0; i< NeuronCount; i++){
            neurons[i] = new Neuron(weightCount);
        }
        layer_size = neurons.length;
        total_data_required = neurons[0].weights.length;

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
    public double[] secondlayertThink(double[] inputs){
        if(total_data_required != inputs.length)
            throw new IndexOutOfBoundsException();
        double[] outputs = new double[layer_size];
        for(int i =0; i<layer_size; i++){
            outputs[i] = neurons[i].think(inputs);
        }
        for(int i =0; i<layer_size; i++){
            outputs[i] = ActivationFunction.relu(outputs[i]);
        }
        return outputs;
    }

    public double[] thirdLayerThink(double[] inputs){
        if(total_data_required != inputs.length)
            throw new IndexOutOfBoundsException();
        double[] outputs = new double[layer_size];
        for(int i =0; i<layer_size; i++){
            outputs[i] = neurons[i].think(inputs);
        }
        outputs = ActivationFunction.softmax(outputs);
        return outputs;
    }




}
