function plotParticlesVTime(stressTestPath, cellsQuantity, outputFolder)

	strCellqty = "Cell Quantity = %d";
	xLabel = "Quantity of Particles";
	yLabel = "Time" ;
	strTitle = "Time in funtion of quantity of particles for differents number of cells";

  simulationTimes = dlmread(stressTestPath,' ',0,0);
  allCells=cell(19,2);
  index = 1 ;

  for i = 1:rows(simulationTimes)
    allCells{simulationTimes(i,1),1}=[allCells{simulationTimes(i,1),1} ,simulationTimes(i,2)];
    allCells{simulationTimes(i,1),2}=[allCells{simulationTimes(i,1),2} ,simulationTimes(i,3)];
	endfor

	qty = length(cellsQuantity);
	clr = lines(qty);   %# LINES colormap

	clf
	figure("position",get(0,"screensize"));
	hold all;
	for i= cellsQuantity
  	plot(allCells{i,1},allCells{i,2},'Color',clr(index,:));
  	index = index +1;
  endfor
  
  xlabel(xLabel);
	ylabel(yLabel);
	

	hold off;

	set(gca, 'XTick', allCells{i,1});
	grid("on");
	title (strTitle);
	str = cellstr( num2str((cellsQuantity)',strCellqty ) );
	legend(str,"location","northwest")

endfunction