#! /bin/bash
echo "1 - simulation\n2 - plot cells vs time\n3 - plot particles vs time\n4 - plot positions"
read action

case $action in 
	1 )
		mkdir testFiles
		echo "length radio timeStep interactionRadio timesPerSimulation fromParticleQuantity toParticleQuantity outputTestPath"
		read length radio timeStep interactionRadio timesPerSimulation fromParticleQuantity toParticleQuantity outputTestPath
		outputTestPath="$outputTestPath/testFiles/"
		java -jar target/cellIndexMethod.jar $length $radio $timeStep $interactionRadio $timesPerSimulation $fromParticleQuantity $toParticleQuantity $outputTestPath
		;;
	2 )
		mkdir images
		echo "stressTestPath fromParticleQuantity toParticleQuantity length interactionRadio radio outputImagesPath"
		read stressTestPath fromParticleQuantity toParticleQuantity length interactionRadio radio outputImagesPath
		outputImagesPath="$outputImagesPath/images/"
		outputImagesPath=`cd "$outputImagesPath"; pwd`
		stressTestPath=`cd "$stressTestPath"; pwd`
		echo $outputImagesPath
		cd src/
		octave -q --eval "plotCellsVTime('$stressTestPath',$fromParticleQuantity:100:$toParticleQuantity, $length, $interactionRadio,$radio, '$outputImagesPath')"
		;;
	3 )
		mkdir images
		echo "stressTestPath cellQuantity length interactionRadio radio outputImagesPath"
		read stressTestPath cellQuantity length interactionRadio radio outputImagesPath
		outputImagesPath="$outputImagesPath/images/"
		outputImagesPath=`cd "$outputImagesPath"; pwd`
		cd src/
		octave -q --eval "plotParticlesVTime('$stressTestPath',1:1:$cellQuantity, $length, $interactionRadio,$radio,'$outputImagesPath')"
		;;
	4 )
		mkdir images
		echo "plotPositions dynamicPath neightboursPath particleId outputImagesPath"
		read plotPositions dynamicPath neightboursPath particleId outputImagesPath
		outputImagesPath="$outputImagesPath/images/"
		outputImagesPath=`cd "$outputImagesPath"; pwd`
		cd src/
		octave -q --eval "plotPositions('$dynamicPath', '$neightboursPath', $particleId,'$outputImagesPath')"
		;;
esac
