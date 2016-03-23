package itba.edu.ar.cellIndexMethod.data.particle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Particle {

	private int id;

	private Map<Float, ParticleState> states = new HashMap<Float, ParticleState>();

	private float color;
	private float radio;
	private float velocity;
	private Set<Particle> neightbours = new HashSet<Particle>();

	public Particle(int id, float radio, float color) {
		super();
		this.id = id;
		this.color = color;
		this.radio = radio;
	}

	public FloatPoint getPosition(float timeStep) {
		return states.get(timeStep).getPosition();
	}

	public float getColor() {
		return color;
	}

	public float getRadio() {
		return radio;
	}

	public void fillNeightbours(List<Particle> particles, float interactionRadio, float timeStep,boolean periodic,float length,int cellQuantity) {

		for (Particle particle : particles)
			if (isNeightbour(particle, interactionRadio, timeStep,periodic,length,cellQuantity)) {
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

	public boolean isNeightbour(Particle particle, float interactionRadio, float timeStep, boolean periodic,
			float length, int cellQuantity) {
		return !this.equals(particle)
				&& (distance(getPosition(timeStep), particle.getPosition(timeStep), length, periodic, cellQuantity)
						- getRadio() - particle.getRadio()) < interactionRadio;
	}

	private double distance(FloatPoint p1, FloatPoint p2, float length, boolean periodic, int cellQuantity) {

		double distanceX = Math.abs(p1.getX() - p2.getX());
		double distanceY = Math.abs(p1.getY() - p2.getY());
		float lengthX = 0;
		float lengthY = 0;

		if (periodic) {
			if (distanceX > length / cellQuantity)
				lengthX = length;
			if (distanceY > length / cellQuantity)
				lengthY = length;

		}

		double distance = Math.hypot(lengthX - distanceX, lengthY - distanceY);

		return distance;
	}

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	public void addParticleState(float x, float y, float velocityX, float velocityY, float timeStep) {
		states.put(timeStep, new ParticleState(x, y, velocityX, velocityY));
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

	public void addPosition(float x, float y, float timeStep) {
		if (!states.containsKey(timeStep)) {
			addParticleState(x, y, 0, 0, timeStep);
			return;
		}

		states.get(timeStep).setPosition(x, y);
	}

	public void addVelocity(float velocityX, float velocityY, float timeStep) {
		if (!states.containsKey(timeStep)) {
			addParticleState(0, 0, velocityX, velocityY, timeStep);
			return;
		}

		states.get(timeStep).setVelocity(velocityX, velocityY);
		;
	}

	public int getId() {
		return id;
	}

	public void resetNeightbours() {
		neightbours.clear();
	}

}
