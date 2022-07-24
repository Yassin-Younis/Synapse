public class Main {
    public static void main(String[] args) {

        int[] noOfHidden = {1};

        Network test = new Network(1, 1);

        double[] values = {1};
        
        test.initInput(values);

        double[] targets = {0.2};

        test.setTargets(targets);

        test.testNetwork();

        test.setLearingRate(0.5);

        for (int i=0; i<10; i++) {
            test.train();
            test.testNetwork();
        }



    }
}
