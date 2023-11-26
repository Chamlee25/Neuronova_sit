public class ActivationFunction {

    public static double relu(double x) {
        return Math.max(0f, x);
    }
    public static double[] softmax(double[] input) {
        double[] output = new double[input.length];
        double sum = 0.0f;
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


}