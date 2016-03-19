package itba.edu.ar.cellIndexMethod.data;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Matrix<T> {

	private List<List<T>> matrix;
	private int xSize;
	private int ySize;
	
	public Matrix(int xSize,int ySize,Class<T> classT) throws InstantiationException, IllegalAccessException{
		matrix = new ArrayList<List<T>>(xSize);
		for(int x=0;x<xSize;x++)
		{
			matrix.add(x, new ArrayList<T>(ySize));
			for(int y=0;y<ySize;y++)
				matrix.get(x).add(classT.newInstance());
		}
		this.xSize=xSize;
		this.ySize=ySize;
		
	}
	
	public T get(int x,int y){
		return matrix.get(x).get(y);
	}
	
	public T get(Point position){
		return get(position.x,position.y);
	}
	
	public boolean exist(int x,int y){
		
		if(!insideBoundaries(x,y))
			throw new IllegalArgumentException("("+x+","+y+")");
		
		return !(matrix.size() < x || matrix.get(x).size() < y);
	}
	
	
	public boolean insideBoundaries(int x, int y) {
		return x >= 0 && x < xSize && y >=0 && y < ySize;
	}

	public boolean exist(Point position) {
		return exist((int)position.getX(),(int)position.getY());
	}
	
}
