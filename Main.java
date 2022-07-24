public class Main {
    public static void main(String[] args) {

        int[] noOfHidden = {5};

        Network test = new Network(1, 1);

        double[] values = {5};
        
        test.initInput(values);

        double[] targets = {0.5};

        test.setTargets(targets);
        test.setLearingRate(0.5);
        test.calculateValues();


        test.toString();

        for (int i=0; i<10000; i++) {
            test.train();
        }
        test.toString();
        System.out.println("DONEEEE");



    }
}
