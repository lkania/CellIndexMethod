#! /bin/bash

mvn package
path=$(pwd)/
stressTestFilename="stressTest"
stressTestPath="$path$stressTestFilename"
outputImagesPath="$pathimages/"

mkdir images
java -jar target/cellIndexMethod.jar 20 0.25 0 1 3 $path
cd src/ && octave --eval "plotSimulationTimes('$stressTestPath','$outputImagesPath')"