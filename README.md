# Human-Mouse-Movement-Simulator
This project is created to create a neural network model that would simulate and generate human mouse movements based on a dataset collected from a specific person.

## Human mouse movement
Mouse movements consist of two different aspects, the path the mouse takes and the acceleration over the path. The mouse path can be gathered by storing each position taken while the acceleration can be noticed by the distance between each position in the path.

I will theorise and try to practically implement a way to simulate both of these based on a specific person which the dataset is gathered from, the easiest part will be the path while the acceleration is a lot harder to achieve.

## Dataset
The dataset is created by running the Java application and pressing each of the "click" boxes that comes up on the screen. Each milisecond the position of your mouse is checked and the difference between the previous position is stored as a distance traveled in that milisecond.

## Neural networks
There are two neural networks that goes into this at the moment, one for path generation and one for generating the time each point in the path take.
The neural networks are created by running the jupyter notebook with a created dataset.

## Document
Link to document describing theories and results: [docs.google.com](https://docs.google.com/document/d/1FiiKzwvPjTERbfHkADIo1G-xgsjJFBhWM6y8lTEdo_4/edit?usp=sharing)
