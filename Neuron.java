import java.util.Random;

public class Neuron {

    private double value;
    private Neuron[] inputNeurons;
    private double[] respectiveWeights;
    private double bias;
    private boolean isInput = false;


    public boolean isInput() {
        return isInput;
    }

    public void setInput(boolean isInput) {
        this.isInput = isInput;
    }

    private int noOfInputNeurons;
 

    // if this is an input neuron set noOfInputNeurons to 0, and set its value using the setValue method.
    public Neuron(int noOfInputNeurons) {
        respectiveWeights = new double[noOfInputNeurons];
        inputNeurons = new Neuron[noOfInputNeurons];
        this.noOfInputNeurons = noOfInputNeurons;
    }

    public void calculateValue() {
        double temp = 0;
        for (int i = 0; i < noOfInputNeurons; i++) {
            temp += (inputNeurons[i].getValue()*respectiveWeights[i]);
        }
        temp+=bias;
        value = sigmoid(temp);
    }

    public void randomizeWeights() {
        Random random = new Random();
        for (int i = 0; i< noOfInputNeurons; i++) {
            respectiveWeights[i] = (random.nextInt(5000 + 5000) - 5000)/1000.000;
        }
        bias = (random.nextInt(5000 + 5000) - 5000)/1000.000;
    }

    public void setValue(double value) {
        this.value = value;
    }


    public double getValue() { 
        return value;
    }

    
    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public void setInputLayer(Neuron[] neuronLayer) {
        inputNeurons = neuronLayer;
    }

    public Neuron[] getInputNeurons() {
        return inputNeurons;
    }

    public void setInputNeurons(Neuron[] inputNeurons) {
        this.inputNeurons = inputNeurons;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public double[] getRespectiveWeights() {
        return respectiveWeights;
    }

    public void setRespectiveWeights(double[] respectiveWeights) {
        this.respectiveWeights = respectiveWeights;
    }

    public String toString() {

        System.out.println("------------------");
        System.out.println("Hi! I'm " + hashCode());

        System.out.println("Number of input neurons: " + noOfInputNeurons);
        System.out.println("Their respective weights are as follows:");

        for (int i=0; i<respectiveWeights.length; i++) {
            if (i!= respectiveWeights.length-1) System.out.print(respectiveWeights[i] + ", ");
            else System.out.print(respectiveWeights[i] );
        }

        System.out.println();
        System.out.println("I have a bias of " + bias);
        System.out.println("And finally a value of " + value);
        return "";
    }


}