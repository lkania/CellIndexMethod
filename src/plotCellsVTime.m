function plotCellsVTime(stressTestPath, particleQuantity, totaLength, interactionRadio, radio,outputFolder)

  
	cd(outputFolder);

	strCellqty = "Particle Quantity = %d";
	strTitle = "Time in funtion of quantity of cells for differents number of particles";
	xLabel = "Quantity of Cells";
	yLabel = "Time" ;

  simulationTimes = dlmread(stressTestPath,' ',0,0);
  index = 1 ;
  maxCellsQty = ceil(totaLength / (interactionRadio+2*radio)) - 1;
 	totalGraphs = ceil(rows(simulationTimes) / maxCellsQty);
 	particlesGraph=zeros(1,totalGraphs);
 	counter= 1;
 	nCells=cell(totalGraphs,1);

 	for i = 1:maxCellsQty:rows(simulationTimes)
		nCells{(i+maxCellsQty-1)/maxCellsQty,1}=simulationTimes(i:(maxCellsQty-1+i),3);   
		particlesGraph(counter) = simulationTimes(i,2);
		counter = counter +1;
 	endfor

	qty = length(particleQuantity);
	clr = lines(qty);   %# LINES colormap

	clf
	figure("position",get(0,"screensize"));
	hold all;
	for i= 1:totalGraphs
		if particlesGraph(i) == particleQuantity(index)
  		plot(1:maxCellsQty,nCells{i,1},'Color',clr(index,:));
  		index = index +1;
  	endif
  endfor
  
  xlabel(xLabel);
	ylabel(yLabel);
	

	hold off;
	set(gca, 'XTick', 1:1:(maxCellsQty+1));
	grid("on");
	title (strTitle);
	str = cellstr( num2str((particleQuantity)',strCellqty ) );
	legend(str,"location","northwest");

	print -djpg CellsVsTime.jpg

endfunction