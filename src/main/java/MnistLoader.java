import java.io.File;

public class MnistLoader {
    /* type
    0 = train
    1 = test
     */

    public MnistLoader(int type){
        File sourceFile = new File("resources/" + (type==0?"mnist_train.csv":"mnist_test.csv"));

    }
}
