public class Network {
    public int layerCount;
    public Layer[] layers;
    public Network(int layerCount, int[] neuronCount){
        layers = new Layer[layerCount];
        this.layerCount = layerCount;

        /* first layer
          neuron -> only 1 weight
          activation -> sigmoid / tanh
         */
        layers[0] = new Layer(0,neuronCount[0],1,"sigmoid");

        /* hidden layers
         * neuron -> number of weight = previous layer neuron count
         * activation -> relu
         */

        for(int i =1; i<layerCount-1; i++){
            layers[i] = new Layer(i,neuronCount[i],neuronCount[i-1],"relu");
        }

        /* last layer
         * neuron -> number of weight = previous layer neuron count
         * activation -> softmax
         */

        layers[layerCount-1] = new Layer(layerCount-1,neuronCount[layerCount-1],neuronCount[layerCount-2],"softmax");
    }


}
