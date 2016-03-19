package itba.edu.ar.cellIndexMethod;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import itba.edu.ar.cellIndexMethod.data.Cell;
import itba.edu.ar.cellIndexMethod.data.particle.Particle;
import itba.edu.ar.cellIndexMethod.route.Route;

public class CellIndexMethod {

	private IndexMatrix matrix;
	private Route route;
	private float interactionRadio;
	private List<CellIndexMethodObserver> subscribers = new LinkedList<CellIndexMethodObserver>();

	
	public CellIndexMethod(IndexMatrix matrix, Route route, float interactionRadio) {
		this.matrix = matrix;
		this.route = route;
		this.interactionRadio = interactionRadio;
		
		if(!satisfyConstraint())
			throw new IllegalStateException();
			
		
	}

	private boolean satisfyConstraint() {
		return matrix.getLength()/matrix.getCellQuantity() > interactionRadio;
	}

	public void execute(){
		notifyTimeStepStarted();
		
		Map<Particle,Set<Particle>> allNeightbours = new HashMap<Particle,Set<Particle>>();
		route.setMap(allNeightbours);
		for(int x=0;x<matrix.getCellQuantity();x++)
		{	
			for(int y=0;y<matrix.getCellQuantity();y++)
			{
				Cell cell = matrix.getCell(x,y);
				for(Particle particle : cell.getParticles()){
					route.fillNeightbours(x,y,matrix,particle,interactionRadio);
				}
				
			}
		}
		
		notifyTimeStepEnded(allNeightbours);
	}
	
	private void notifyTimeStepStarted() {
		for(CellIndexMethodObserver subscriber : subscribers)
			subscriber.stepStarted();
	}

	private void notifyTimeStepEnded(Map<Particle,Set<Particle>> allNeightbours) {
		for(CellIndexMethodObserver subscriber : subscribers)
			subscriber.stepEnded(allNeightbours);
	}
	
	

	public void subscribe(CellIndexMethodObserver subscriber){
		subscribers.add(subscriber);
	}


	
}
