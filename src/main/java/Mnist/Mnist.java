package Mnist;
public class Mnist {
    public double[] data;
    public double[] actualValues;
    public int actualvalue;
    public Mnist(double[] data, int actualValue) {
        this.data = data;
        this.actualValues = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.actualValues[actualValue] = 1;
        this.actualvalue = actualValue;
    }
}
