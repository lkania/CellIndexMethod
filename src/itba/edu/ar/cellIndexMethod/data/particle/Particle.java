package itba.edu.ar.cellIndexMethod.data.particle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Particle {

	private int id;

	private ParticleState state = new ParticleState();

	private double color;
	private double radio;
	private double mass;

	private Set<Particle> neightbours = new HashSet<Particle>();

	
	public Particle(int id, double radio, double color) {
		super();
		this.id = id;
		this.color = color;
		this.radio = radio;
		this.mass = 0;
	}

	public Particle(int id, double radio, double color, double mass) {
		super();
		this.id = id;
		this.color = color;
		this.radio = radio;
		this.mass = mass;
	}

	public FloatPoint getPosition() {
		return state.getPosition();
	}

	public double getMass() {
		return mass;
	}

	public double getColor() {
		return color;
	}

	public double getRadio() {
		return radio;
	}

	public void fillNeightbours(List<Particle> particles, double interactionRadio, boolean periodic, double length,
			int cellQuantity) {

		for (Particle particle : particles)
			if (isNeightbour(particle, interactionRadio, periodic, length, cellQuantity)) {
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

	public boolean isNeightbour(Particle particle, double interactionRadio, boolean periodic, double length,
			int cellQuantity) {
		return !this.equals(particle)
				&& (distance(getPosition(), particle.getPosition(), length, periodic, cellQuantity) - getRadio()
						- particle.getRadio()) < interactionRadio;
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
		long temp;
		temp = Double.doubleToLongBits(color);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		temp = Double.doubleToLongBits(mass);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(radio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		if (Double.doubleToLongBits(color) != Double.doubleToLongBits(other.color))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(mass) != Double.doubleToLongBits(other.mass))
			return false;
		if (Double.doubleToLongBits(radio) != Double.doubleToLongBits(other.radio))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
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
		state.setPosition(new FloatPoint(x, y));

	}

	public void setVelocity(double vx, double vy) {
		state.setAngle(Math.atan2(vy, vx));
		state.setVelocityAbs(Math.hypot(vx, vy));
	}

	public FloatPoint getVelocity() {
		return state.getVelocity();
	}

}
