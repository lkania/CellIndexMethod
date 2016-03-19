function plotPositions(id)

positions = dlmread('../../ArchivosEjemplo/Dynamic100.txt','',1,0);

neightbours = dlmread('../../ArchivosEjemplo/AlgunosVecinos_100_rc6.txt',',',0,0);

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

plot(positions(:,1),positions(:,2),'o',positions(id,1),positions(id,2),'*r',positions(neightboursId,1),positions(neightboursId,2),'*g');

endfunction