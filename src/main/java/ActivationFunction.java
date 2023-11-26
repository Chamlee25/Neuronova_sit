public class ActivationFunction {

    public static double relu(double x) {
        return Math.max(0f, x);
    }

    public static double reluDerivative(double x){
        return x > 0 ? 1 : 0;
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

    public static double[] softmaxDerivative(double[] softmaxOutput) {
        int N = softmaxOutput.length;
        double[] derivative = new double[N];

        for (int i = 0; i < N; i++) {
            double sumExp = 0.0;
            for (int j = 0; j < N; j++) {
                sumExp += Math.exp(softmaxOutput[j]);
            }
            double softmax_i = Math.exp(softmaxOutput[i]) / sumExp;

            derivative[i] = softmax_i * (1 - softmax_i);
        }

        return derivative;
    }

    public static double sigmoid(double x){
        return (1 / (1 + Math.exp(-x)));
    }

    public static double sigmoidDerivative(double x){
        double sigmoid = (1 / (1 + Math.exp(-x)));
        return sigmoid * (1 - sigmoid);
    }




}