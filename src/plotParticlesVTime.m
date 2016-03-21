function plotParticlesVTime(stressTestPath, cellsQuantity, totaLength, interactionRadio, radio, outputFolder)

	cd(outputFolder);

	strCellqty = "Cell Quantity = %d";
	xLabel = "Quantity of Particles";
	yLabel = "Time" ;
	strTitle = "Time in funtion of quantity of particles for differents number of cells";

  simulationTimes = dlmread(stressTestPath,' ',0,0);
  maxCells = ceil(totaLength / (interactionRadio+2*radio)) - 1;
  allCells=cell(maxCells,2);
  index = 1 ;

  for i = 1:rows(simulationTimes)
    	allCells{simulationTimes(i,1),1}=[allCells{simulationTimes(i,1),1} ,simulationTimes(i,2)];
    	allCells{simulationTimes(i,1),2}=[allCells{simulationTimes(i,1),2} ,simulationTimes(i,3)];
	endfor

	qty = length(cellsQuantity);
	clr = lines(qty);   %# LINES colormap

	clf;
	figure("position",get(0,"screensize"));
	hold all;
	for i= cellsQuantity
		if(i<=maxCells)
  		plot(allCells{i,1},allCells{i,2},'Color',clr(index,:));
  		index = index +1;
  	endif
  endfor
  
  xlabel(xLabel);
	ylabel(yLabel);
	

	hold off;

	set(gca, 'XTick', allCells{maxCells,1});
	grid("on");
	title (strTitle);
	str = cellstr( num2str((cellsQuantity)',strCellqty ) );
	legend(str,"location","northwest");

	print -djpg plotParticlesVsTime.jpg;

endfunction