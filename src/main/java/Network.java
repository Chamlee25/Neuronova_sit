public class Network {

    public Layer firstLayer;
    public Layer secondLayer;
    public Layer thirdLayer;

    double[] data2;
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
        double[] data1 = firstLayer.firstLayerThink(inputData);
        data2 = secondLayer.secondLayerThink(data1);
        double[] data3 = thirdLayer.thirdLayerThink(data2);
        return data3;

    }

    public double[] learn(double[] inputData, double[] expectedData, double learningRate){
        double[] resultData = predict(inputData);
        double[] errors = new double[10]; // 10 = resultData.lenght
        for(int i =0; i<10; i++){
            errors[i] = Math.pow(expectedData[i] - resultData[i],2);
        }
        for(int i =0; i<10; i++){
            thirdLayer.neurons[i].updateLastNeuron(errors[i],learningRate,data2);
        }
        return predict(inputData);



    }


}
