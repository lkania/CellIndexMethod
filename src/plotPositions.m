function plotPositions(dynamicPath, neightboursPath, id)
xLabel = "Particle X position";
yLabel = "Particle Y position" ;
strTitle = strcat ("All particles and particle [", num2str(id), "] neightbours");

positions = dlmread(dynamicPath,'',1,0);
neightbours = dlmread(neightboursPath,',',0,0);

neightboursId=[];

for i = 1:rows(neightbours)
  if neightbours(i,1)==id
    for j = 2:columns(neightbours)
       if neightbours(i,j)==0
        neightboursId=neightbours(i,2:j-1);
        break
       endif     
     endfor
  break
  endif 
endfor

clf;
figure("position",get(0,"screensize"));
plot(positions(:,1),positions(:,2),'o',positions(id,1),positions(id,2),'*r',positions(neightboursId,1),positions(neightboursId,2),'*g');
xlabel(xLabel);
ylabel(yLabel);
title (strTitle);

endfunction