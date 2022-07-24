<img src="https://github.com/Yassin-Younis/Synapse/blob/main/synapse.png?raw=true" alt="drawing" width="700"/>

A very simple Open-source JAVA-powered machine learning library for absolute beginners and students.
## Features

- Full ability to choose and customize number of Neurons.
- Really simple and elegant code fit for begginers.

## Installation

All you need is [VSCODE](https://code.visualstudio.com/) and of course, the [JDK](https://www.oracle.com/java/technologies/downloads/) to run the network.

## How it works

First, initialize the Network. In this example, we initialize a network with 1 input neuron and 1 output neuron.

```sh
Network synapse = new Network(1, 1);
```
Now for a network with hidden layers, it's a bit less elegant:

We need to initialize an integer array with the number of neurons you want each hidden layer to have.

```sh
int[] hiddenLayerVals = {5};
```
```sh
Network synapse = new Network(4, hiddenLayerVals, 1);
```
Below is a visual of the network we just initialized:

<img src="https://github.com/Yassin-Younis/Synapse/blob/main/Schema-of-an-artificial-neural-network-Image-Source-10.png?raw=true" alt="drawing" width="700"/>

Now create a <ins>double</ins> array with your input values and pass it to the <ins>initInput</ins> method.

```sh
double[] values = {1, 23, 0.7, -5};
```
> Note: Make sure values.length is equal to the number of initialized input neurons.

```sh
synapse.initInput(values);
```



We do the same for the output

```sh
double[] target = {0.2};
synapse.setTargets(target);
```

Setting learning rate

```sh
synapse.setLearingRate(0.5);
```

Running a quick test to display starting weights and biases.

```sh
synapse.testNetwork();
```

Output:

```sh
Hi! I'm 1523554304
Number of input neurons: 0
Their respective weights are as follows:

I have a bias of 0.0
And finally a value of 1.0
------------------
Hi! I'm 1746572565
Number of input neurons: 0
Their respective weights are as follows:

I have a bias of 0.0
And finally a value of 23.0
------------------
Hi! I'm 989110044
Number of input neurons: 0
Their respective weights are as follows:

I have a bias of 0.0
And finally a value of 0.7
------------------
Hi! I'm 424058530
Number of input neurons: 0
Their respective weights are as follows:

I have a bias of 0.0
And finally a value of -5.0
------------------
Hidden Layers
------------------
Hi! I'm 321001045
Number of input neurons: 4
Their respective weights are as follows:
1.092, 2.018, 1.016, 0.688
I have a bias of 0.572
And finally a value of 1.0
------------------
Hi! I'm 834600351
Number of input neurons: 4
Their respective weights are as follows:
-4.765, 4.555, 0.579, -4.684
I have a bias of -4.241
And finally a value of 1.0
------------------
Hi! I'm 471910020
Number of input neurons: 4
------------------
Output Layer
------------------
Hi! I'm 303563356
Number of input neurons: 5
Their respective weights are as follows:
-2.397, 1.231, 4.066, -0.782, 3.039
I have a bias of -4.769
And finally a value of 0.024650468360723706
------------------
Respective Errors
0.030747458246113562
------END-------
```

## Training

Training can be achieved by the simplest command yet:

```sh
synapse.train();
```
Running the method above adjusts our parameters to satisfy our output.
> Note: Make sure you change inputs and outputs after every train method call.
