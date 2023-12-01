import java.io.*;

public class Network implements Serializable{
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
            thirdLayer.neurons[neuron].error = (thirdLayerResultData[neuron]-expectedData[neuron]) * ActivationFunction.hyperbolicTangensDerivative(thirdLayerResultData[neuron]);
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
            firstLayer.neurons[neuron].error = sum * ActivationFunction.hyperbolicTangens(firstLayerResultData[neuron]);
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
