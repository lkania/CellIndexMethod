package itba.edu.ar.cellIndexMethod.data.particle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Particle {

	private int id;

	private ParticleState state = new ParticleState();

	private float color;
	private float radio;

	private Set<Particle> neightbours = new HashSet<Particle>();

	public Particle(int id, float radio, float color) {
		super();
		this.id = id;
		this.color = color;
		this.radio = radio;
	}

	public FloatPoint getPosition() {
		return state.getPosition();
	}

	public float getColor() {
		return color;
	}

	public float getRadio() {
		return radio;
	}

	public void fillNeightbours(List<Particle> particles, float interactionRadio,boolean periodic,double length,int cellQuantity) {

		for (Particle particle : particles)
			if (isNeightbour(particle, interactionRadio,periodic,length,cellQuantity)) {
				addNeightbour(particle);
				particle.addNeightbour(this);
			}
	}

	public Set<Particle> getNeightbours() {
		return neightbours;
	}

	private void addNeightbour(Particle particle) {
		neightbours.add(particle);
	}

	public boolean isNeightbour(Particle particle, float interactionRadio, boolean periodic,
			double length, int cellQuantity) {
		return !this.equals(particle)
				&& (distance(getPosition(), particle.getPosition(), length, periodic, cellQuantity)
						- getRadio() - particle.getRadio()) < interactionRadio;
	}

	private double distance(FloatPoint p1, FloatPoint p2, double length, boolean periodic, int cellQuantity) {

		double distanceX = Math.abs(p1.getX() - p2.getX());
		double distanceY = Math.abs(p1.getY() - p2.getY());
		double lengthX = 0;
		double lengthY = 0;

		if (periodic) {
			if (distanceX > length / cellQuantity)
				lengthX = length;
			if (distanceY > length / cellQuantity)
				lengthY = length;

		}

		double distance = Math.hypot(lengthX - distanceX, lengthY - distanceY);

		return distance;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Particle other = (Particle) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void resetNeightbours() {
		neightbours.clear();
	}

	public double getAngle() {
		return state.getAngle();
	}

	public double getVelocityAbs() {
		return state.getVelocityAbs();
	}

	public void setPosition(FloatPoint newPosition) {
		state.setPosition(newPosition);
		
	}

	public void setAngle(Double angle) {
		state.setAngle(angle);
	}

	public void setPosition(double x, double y) {
		state.setPosition(new FloatPoint(x,y));
		
	}

	public void setVelocityAbs(double vx, double vy) {
		state.setVelocityAbs(Math.hypot(vx, vy));
	}

}
