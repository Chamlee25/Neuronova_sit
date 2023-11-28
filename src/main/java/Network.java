import java.io.*;

public class Network {
    public Layer firstLayer;
    public Layer secondLayer;
    public Layer thirdLayer;
    double[] firstLayerResultData;
    double[] secondLayerResultData;
    double[] thirdLayerResultData;
    public Network(){
        firstLayer = new Layer(784,1);
        secondLayer = new Layer(10,784);
        thirdLayer = new Layer(10,10);
    }
    //load saved network from file

    public Network(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();

        String[] layerInfo = line.split(";");
        String[] firstLayerInfo = layerInfo[0].split(",");
        String[] secondLayerInfo = layerInfo[1].split(",");
        String[] thirdLayerInfo = layerInfo[2].split(",");
        // Load first layer
        firstLayer = new Layer(784,1);
        for (int i = 0; i < 784; i++) {
            String[] neuronInfo = firstLayerInfo[i].split(":");
            double weight = Double.parseDouble(neuronInfo[0]);
            double bias = Double.parseDouble(neuronInfo[1]);
            firstLayer.neurons[i].weights[0]=weight;
            firstLayer.neurons[i].bias = bias;
        }
        // Load second layer
        secondLayer = new Layer(10,784);
        for (int neuron = 0; neuron < 10; neuron++) {
            String[] neuronInfo = secondLayerInfo[neuron].split(":");
            for (int weight = 0; weight < 784; weight++) {
                secondLayer.neurons[neuron].weights[weight] = Double.parseDouble(neuronInfo[weight]);
            }
            secondLayer.neurons[neuron].bias = Double.parseDouble(neuronInfo[784]);
        }


        // Load third layer
        thirdLayer = new Layer(10,10);
        for (int neuron = 0; neuron < 10; neuron++) {
            String[] neuronInfo = thirdLayerInfo[neuron].split(":");
            for (int j = 0; j < 10; j++) {
                thirdLayer.neurons[neuron].weights[j] = Double.parseDouble(neuronInfo[j]);
            }
            thirdLayer.neurons[neuron].bias = Double.parseDouble(neuronInfo[10]);
        }
        br.close();

    }





    public double[] predict(double[] inputData) {
        if(inputData.length != firstLayer.neurons.length)
            throw new IllegalArgumentException();
        firstLayerResultData = firstLayer.firstLayerThink(inputData);
        secondLayerResultData = secondLayer.secondLayerThink(firstLayerResultData);
        thirdLayerResultData = thirdLayer.thirdLayerThink(secondLayerResultData);
        return thirdLayerResultData;
    }

    public double[] learn(double[] inputData, double[] expectedData, double learningRate){
        predict(inputData);
        backPropError(expectedData);
        updateWeight(learningRate,inputData);
        return predict(inputData);
    }

    private void backPropError(double[] expectedData){
        //first layer error
        for(int neuron =0; neuron<10; neuron++){
            thirdLayer.neurons[neuron].error = (thirdLayerResultData[neuron]-expectedData[neuron]) * ActivationFunction.sigmoidDerivative(thirdLayerResultData[neuron]);
        }
        //second layer error
        for(int neuron = 0; neuron<10; neuron++){
            double sum =0;
            for(int nextNeuron =0; nextNeuron<10; nextNeuron++){
                sum += thirdLayer.neurons[nextNeuron].weights[neuron] * thirdLayer.neurons[nextNeuron].error;
            }
            secondLayer.neurons[neuron].error = sum * ActivationFunction.reluDerivative(secondLayerResultData[neuron]);
        }
        //third layer error
        for(int neuron = 0; neuron<784; neuron++){
            double sum =0;
            for(int nextNeuron =0; nextNeuron<10; nextNeuron++){
                sum += secondLayer.neurons[nextNeuron].weights[neuron] * secondLayer.neurons[nextNeuron].error;
            }
            firstLayer.neurons[neuron].error = sum * ActivationFunction.sigmoidDerivative(firstLayerResultData[neuron]);
        }
    }

    private void updateWeight(double learningRate, double[] inputData){
        //third layer
        for(int neuron = 0; neuron<10; neuron++){
            double delta;
            for(int prevNeuron = 0; prevNeuron <10; prevNeuron++){
                delta = - learningRate * secondLayerResultData[prevNeuron] * thirdLayer.neurons[neuron].error;
                thirdLayer.neurons[neuron].weights[prevNeuron] += delta;
            }
            delta = -learningRate * thirdLayer.neurons[neuron].error;
            thirdLayer.neurons[neuron].bias += delta;
        }
        //second layer
        for(int neuron = 0; neuron<10; neuron++){
            double delta;
            for(int prevNeuron = 0; prevNeuron <784; prevNeuron++){
                delta = - learningRate * firstLayerResultData[prevNeuron] * secondLayer.neurons[neuron].error;
                secondLayer.neurons[neuron].weights[prevNeuron] += delta;
            }
            delta = -learningRate * secondLayer.neurons[neuron].error;
            secondLayer.neurons[neuron].bias += delta;
        }
        //first layer
        for(int neuron = 0; neuron<784; neuron++){
            double delta = - learningRate * inputData[neuron] * firstLayer.neurons[neuron].error;
            firstLayer.neurons[neuron].weights[0] += delta;
            delta = -learningRate * firstLayer.neurons[neuron].error;
            firstLayer.neurons[neuron].bias += delta;
        }
    }




}
