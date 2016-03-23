#! /bin/bash
echo "1 - simulation\n2 - plot cells vs time\n3 - plot particles vs time\n4 - plot positions"
read action

case $action in 
	1 )
		mkdir -p testFiles
		echo "length radio timeStep interactionRadio timesPerSimulation fromParticleQuantity stepParticleQuantity toParticleQuantity outputTestPath"
		read length radio timeStep interactionRadio timesPerSimulation fromParticleQuantity stepParticleQuantity toParticleQuantity outputTestPath
		outputTestPath="$outputTestPath/testFiles/"
		outputTestPath=`readlink -m "$outputTestPath"`
		outputTestPath="$outputTestPath/"

		if [ "$(expr substr $(uname -s) 1 9)" == "CYGWIN_NT" ]; then
				outputTestPath="$(cygpath -w $outputTestPath)"
		fi

		java -Xmx256m -jar target/cellIndexMethod.jar $length $radio $timeStep $interactionRadio $timesPerSimulation $fromParticleQuantity $stepParticleQuantity $toParticleQuantity $outputTestPath
		;;
	2 )
		mkdir -p images
		echo "stressTestPath fromParticleQuantity toParticleQuantity length interactionRadio radio outputImagesPath"
		read stressTestPath fromParticleQuantity toParticleQuantity length interactionRadio radio outputImagesPath
		outputImagesPath="$outputImagesPath/images/"
		outputImagesPath=`readlink -m "$outputImagesPath"`
		stressTestPath=`readlink -m "$stressTestPath"`
		echo $outputImagesPath
		cd src/
		octave -q --eval "plotCellsVTime('$stressTestPath',$fromParticleQuantity:100:$toParticleQuantity, $length, $interactionRadio,$radio, '$outputImagesPath')"
		;;
	3 )
		mkdir -p images
		echo "stressTestPath cellQuantity length interactionRadio radio outputImagesPath"
		read stressTestPath cellQuantity length interactionRadio radio outputImagesPath
		outputImagesPath="$outputImagesPath/images/"
		outputImagesPath=`readlink -m "$outputImagesPath"`
		stressTestPath=`readlink -m "$stressTestPath"`
		cd src/
		octave -q --eval "plotParticlesVTime('$stressTestPath',1:1:$cellQuantity, $length, $interactionRadio,$radio,'$outputImagesPath')"
		;;
	4 )
		mkdir -p images
		echo "dynamicPath neightboursPath particleId outputImagesPath"
		read dynamicPath neightboursPath particleId outputImagesPath
		outputImagesPath="$outputImagesPath/images/"
		outputImagesPath=`readlink -m "$outputImagesPath"`
		dynamicPath=`readlink -m "$dynamicPath"`
		neightboursPath=`readlink -m "$neightboursPath"`

		cd src/
		octave -q --eval "plotPositions('$dynamicPath', '$neightboursPath', $particleId,'$outputImagesPath')"
		;;
esac
