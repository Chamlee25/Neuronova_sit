public class ActivationFunction {
    //ID of activation function
    int AF_ID = 0;
    public ActivationFunction(String name){
        switch (name.toLowerCase()){
            case "sigmoid":
                AF_ID = 1;
                break;
            case "tanh":
                AF_ID = 2;
                break;
            case "relu":
                AF_ID = 3;
                break;
            case "softmax":
                AF_ID = 4;
                break;
        }
    }

    public double activate(double neuronOutput){
        switch (AF_ID){
            case 1:
                return (1 / ( 1 + Math.exp(-neuronOutput)));
            case 2:
                return  Math.tanh(-neuronOutput);
            case 3:
                return Math.max(0,neuronOutput);

        }
        return 0;
    }



}
