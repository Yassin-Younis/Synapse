

public class Network {

    private Neuron[] inputLayer;
    private Neuron[] outputLayer;
    private double[] targetValues;
    private double[] errorValues;
    private Neuron[][] hiddenLayers;
    private int noOfOutputNeurons;
    private int noOfHiddenLayers;
    private int[] noOfHiddenLayersNeurons;
    private int noOfInputNeurons;
    private double learingRate;



    /**
     * @param noOfInputNeurons the requested number of input neurons.
     * @param noOfHiddenLayersNeurons an array specifying the number of each hidden layer's number of neurons. Can be an array of anysize.
     * @param noOfPossibleOutputs the number of outputs to choose from.
     **/

    public Network(int noOfInputNeurons, int[] noOfHiddenLayersNeurons, int noOfPossibleOutputs) {

        this.noOfInputNeurons = noOfInputNeurons;
        inputLayer = new Neuron[noOfInputNeurons];

        this.noOfHiddenLayers = noOfHiddenLayersNeurons.length;
        hiddenLayers = new Neuron[noOfHiddenLayers][];
        for (int i=0; i<noOfHiddenLayers; i++) {
            hiddenLayers[i] = new Neuron[noOfHiddenLayersNeurons[i]];
        }

        this.noOfOutputNeurons = noOfPossibleOutputs;
        outputLayer = new Neuron[noOfPossibleOutputs];
        errorValues = new double[noOfPossibleOutputs];
    }

    public Network(int noOfInputNeurons, int noOfPossibleOutputs) {

        this.noOfInputNeurons = noOfInputNeurons;
        inputLayer = new Neuron[noOfInputNeurons];

        this.noOfHiddenLayers = 0;

        this.noOfOutputNeurons = noOfPossibleOutputs;
        outputLayer = new Neuron[noOfPossibleOutputs];
    }


    /**
     * initialises Input Layer; creates requested number of input neurons and sets each one to its respective value from the @param values array.
     **/
    public void initInput(double[] values) {
        for (int i=0; i<noOfInputNeurons; i++) {
            inputLayer[i] = new Neuron(0);
            inputLayer[i].setValue(values[i]);
            inputLayer[i].setInput(true);
        }
        initHiddenLayers();
    }

    // is called by initInput; creates requested number of hidden layers and their respective neurons. Also Randomizes their weights and biases.
    public void initHiddenLayers() {

        for (int i=0; i<noOfHiddenLayers; i++) {
            if (i==0) {
                for (int j=0; j<hiddenLayers[0].length; j++) {
                    hiddenLayers[0][j] = new Neuron(noOfInputNeurons);
                    hiddenLayers[0][j].setInputLayer(inputLayer);
                    hiddenLayers[0][j].randomizeWeights();

                }
            }
            else {
                for (int j=0; j<hiddenLayers[i].length; j++) {
                    Neuron[] prevLayer = hiddenLayers[i-1];
                    hiddenLayers[i][j] = new Neuron(prevLayer.length);
                    hiddenLayers[i][j].setInputLayer(prevLayer);
                    hiddenLayers[i][j].randomizeWeights();

                }
            }
        }

        initOutput();
    }

    public void setLearingRate(double learingRate) {
        this.learingRate = learingRate;
    }

    // is called by initHiddenLayers; creates requested number of output neurons and randomizes their weights and biases.
    public void initOutput() {
        for (int i=0; i<noOfOutputNeurons; i++) {
            if (noOfHiddenLayers==0) {
                outputLayer[i] = new Neuron(noOfInputNeurons);
                outputLayer[i].setInputLayer(inputLayer);
                outputLayer[i].randomizeWeights();
            }
            else {
                Neuron[] lastHiddenLayer = hiddenLayers[noOfHiddenLayers-1];
                outputLayer[i] = new Neuron(lastHiddenLayer.length);
                outputLayer[i].setInputLayer(lastHiddenLayer);
                outputLayer[i].randomizeWeights();
            }
        }
    }

    // must call initInput with valid inputs  for this to work.
    public void testNetwork() {
        for (int i=0; i<noOfHiddenLayers; i++) {
            for (int j=0; j<hiddenLayers[i].length; j++) {
                hiddenLayers[i][j].calculateValue();
            }
        }
        for (int i=0; i<noOfOutputNeurons; i++) {
            outputLayer[i].calculateValue();
        }
        calculateErrors();
        toString();
    }

    public void calculateErrors() {
        errorValues = new double[noOfOutputNeurons];
        for (int i=0; i<targetValues.length; i++) {
            errorValues[i]= Math.pow(targetValues[i]-outputLayer[i].getValue(),2);
        }
    }

    public void train() {
        
        for (int i=0; i<noOfOutputNeurons; i++) {
            double dEdY = -2*(targetValues[i]-outputLayer[i].getValue());
            pushLearn(outputLayer[i], dEdY);
        }
    }

    public void pushLearn(Neuron NRN, double chainRuleProduct) {

        if (NRN.isInput()) return; //end condition, recursive end.

        double currentBias = NRN.getBias();
        NRN.setBias(currentBias-learingRate*chainRuleProduct);

        Neuron[] inputLayer = NRN.getInputNeurons();
        double[] currentWeights = NRN.getRespectiveWeights();
        double[] newWeights = new double[currentWeights.length];

        for (int i=0; i<currentWeights.length; i++) {
            newWeights[i] = currentWeights[i] - (learingRate*chainRuleProduct*inputLayer[i].getValue()); //weight - lr*-2(Y-y)*its_output_neuron
        }

        for (int i=0; i<inputLayer.length; i++) {
            pushLearn(inputLayer[i], chainRuleProduct*currentWeights[i]);
        }
        NRN.setRespectiveWeights(newWeights);
    }
    
    public void setTargets(double[] targets) {
        targetValues = targets;
    }

    public String toString() {
        System.out.println("------------------");

        System.out.println("Input Layer");
        for (int i=0; i<noOfInputNeurons; i++) {
            inputLayer[i].toString();
        }

        for (int i=0; i<noOfHiddenLayers; i++) {
            if (i==0) {
                System.out.println("------------------");
                System.out.println("Hidden Layers");
            }
            for (int j=0; j<hiddenLayers[i].length; j++) {
                hiddenLayers[i][j].toString();
            }
        }

        System.out.println("------------------");
        System.out.println("Output Layer");
        for (int i=0; i<noOfOutputNeurons; i++) {
            outputLayer[i].toString();
        }

        System.out.println("------------------");
        System.out.println("Respective Errors");
        for (int i=0; i<noOfOutputNeurons; i++) {
           System.out.println(errorValues[i]);
        }
        System.out.println("------END-------");

        return "";
    }

}
