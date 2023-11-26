public class Network {
    public int layerCount;
    public Layer[] layers;
    public Network(int layerCount, int[] neuronCount){
        int layersToBeCreated = layerCount;
        layers = new Layer[layerCount];
        layers[0] = new Layer(0,neuronCount[0],"sigmoid");
        layersToBeCreated--;
        for(int i = 1; i< layersToBeCreated-1; i++){
            layers[i] = new Layer(i,neuronCount[i],"relu");
        }
        layers[layersToBeCreated] = new Layer(layersToBeCreated,neuronCount[layersToBeCreated],"softmax");
    }
}
