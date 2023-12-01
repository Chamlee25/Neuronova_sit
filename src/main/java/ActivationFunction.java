public class ActivationFunction {
    public static double relu(double x) {
        return Math.max(0, x);
    }
    public static double reluDerivative(double x){
        return x > 0 ? 1 : 0;
    }
    public static double[] softmax(double[] input) {
        double[] output = new double[input.length];
        double sum = 0.0;
        for (int i = 0; i < input.length; i++) {
            output[i] = Math.exp(input[i]);
            sum += output[i];
        }
        for (int i = 0; i < output.length; i++) {
            output[i] /= sum;
        }
        return output;
    }

    public static double sigmoid(double x){
        return (1 / (1 + Math.exp(-x)));
    }

    public static double sigmoidDerivative(double x){
        double sigmoid = (1 / (1 + Math.exp(-x)));
        return sigmoid * (1 - sigmoid);
    }

    public static double hyperbolicTangens(double x){
        return Math.tanh(x);
    }

    public static double hyperbolicTangensDerivative(double x){
        return 1 / Math.exp(2*x);
    }






}