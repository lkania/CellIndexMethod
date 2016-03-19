function plotSimulationTimes(stressTestPath,outputFolder)
  
  stressTestPath
  simulationTimes = dlmread(stressTestPath,' ',0,0);

  allCells=cell(19,2);
  for i = 1:rows(simulationTimes)
    allCells{simulationTimes(i,1),1}=[allCells{simulationTimes(i,1),1} ,simulationTimes(i,2)];
    allCells{simulationTimes(i,1),2}=[allCells{simulationTimes(i,1),2} ,simulationTimes(i,3)];

    endfor
  plot(allCells{1,1},allCells{1,2},'*');

  
  print -djpg particlesVsTime.jpg;
 
 
 nCells=cell(2,1);
 for i = 1:19:rows(simulationTimes)
    nCells{(i+18)/19,1}=simulationTimes(i:(18+i),3);   
 endfor
 
 plot(1:19,nCells{1,1},'+');
 print -djpg timeVsparticles;

endfunction