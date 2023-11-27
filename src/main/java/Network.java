public class Network {

    public Layer firstLayer;
    public Layer secondLayer;
    public Layer thirdLayer;

    double[] data1;
    double[] data2;
    double[] data3;
    public Network(){



        /* first layer
          neuron -> only 1 weight
          activation -> sigmoid / tanh
         */
        firstLayer = new Layer(784,1);

        /* hidden layers
         * neuron -> number of weight = previous layer neuron count
         * activation -> relu
         */


        secondLayer = new Layer(10,784);


        /* last layer
         * neuron -> number of weight = previous layer neuron count
         * activation -> softmax
         */

        thirdLayer = new Layer(10,10);
    }

    public double[] predict(double[] inputData) {
        if(inputData.length != firstLayer.neurons.length)
            throw new IllegalArgumentException();
        data1 = firstLayer.firstLayerThink(inputData);
        data2 = secondLayer.secondLayerThink(data1);
        data3 = thirdLayer.thirdLayerThink(data2);
        return data3;

    }

    public double[] learn(double[] inputData, double[] expectedData, double learningRate){
        predict(inputData);
        backPropError(expectedData);
        updateWeight(learningRate,inputData);
        return predict(inputData);



    }

    public void backPropError(double[] expectedData){
        //first layer error
        for(int neuron =0; neuron<10; neuron++){
            thirdLayer.neurons[neuron].error = (data3[neuron]-expectedData[neuron]) * ActivationFunction.sigmoidDerivative(data3[neuron]);
        }
        //second layer error
        for(int neuron = 0; neuron<10; neuron++){
            double sum =0;
            for(int nextNeuron =0; nextNeuron<10; nextNeuron++){
                sum += thirdLayer.neurons[nextNeuron].weights[neuron] * thirdLayer.neurons[nextNeuron].error;
            }
            secondLayer.neurons[neuron].error = sum * ActivationFunction.reluDerivative(data2[neuron]);
        }
        //third layer error
        for(int neuron = 0; neuron<784; neuron++){
            double sum =0;
            for(int nextNeuron =0; nextNeuron<10; nextNeuron++){
                sum += secondLayer.neurons[nextNeuron].weights[neuron] * secondLayer.neurons[nextNeuron].error;
            }
            firstLayer.neurons[neuron].error = sum * ActivationFunction.sigmoidDerivative(data1[neuron]);
        }
    }

    public void updateWeight(double learningRate, double[] inputData){
        //third layer
        for(int neuron = 0; neuron<10; neuron++){
            double delta;
            for(int prevNeuron = 0; prevNeuron <10; prevNeuron++){
                delta = - learningRate * data2[prevNeuron] * thirdLayer.neurons[neuron].error;
                thirdLayer.neurons[neuron].weights[prevNeuron] += delta;
            }
            delta = -learningRate * thirdLayer.neurons[neuron].error;
            thirdLayer.neurons[neuron].bias += delta;
        }
        //second layer
        for(int neuron = 0; neuron<10; neuron++){
            double delta;
            for(int prevNeuron = 0; prevNeuron <784; prevNeuron++){
                delta = - learningRate * data1[prevNeuron] * secondLayer.neurons[neuron].error;
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
