public class ActivationFunction {
    //ID of activation function
    int AF_ID = 0;
    public ActivationFunction(String name){
        switch (name.toLowerCase()) {
            case "sigmoid" -> AF_ID = 1;
            case "tanh" -> AF_ID = 2;
            case "relu" -> AF_ID = 3;
            case "softmax" -> AF_ID = 4;
        }
    }
    public double activate(double neuronOutput, double... neuronsOutput){
        if(neuronsOutput.length>0){
            double normalizedSum = 0;
            for(int i = 0; i< neuronsOutput.length; i++){
                normalizedSum += Math.exp(neuronsOutput[i]);
            }
            return Math.exp(neuronOutput) / normalizedSum;
        }
        return switch (AF_ID) {
            case 1 -> (1 / (1 + Math.exp(-neuronOutput)));
            case 2 -> Math.tanh(-neuronOutput);
            case 3 -> Math.max(0, neuronOutput);
            case 4 -> throw new IllegalArgumentException();
            default -> throw new IllegalStateException();
        };
    }



}