package model;

import java.util.ArrayList;
import java.util.List;

public class GetPoints {

	public static ArrayList<Point> getPoints(int length){
		ArrayList<Point> points = new ArrayList<Point>(length);
		for(int i=0;i<length;i++){
			double x=Math.random()*10;
			double y=Math.random()*10;
			Point point=new Point(x,y);
			points.add(point);
		}
		return points;
	}
	public static ArrayList<Point> getPointsT(){
		ArrayList<Point> points = new ArrayList<Point>(1000);
		for(int i=0;i<248;i++){
			double x=Math.random()*(25);
			double y=Math.random()*(25);
			Point point=new Point(x,y);
			points.add(point);
		}
		for(int i=0;i<352;i++){
			double x=Math.random()*(50);
			double y=Math.random()*(50);
			Point point=new Point(x,y);
			points.add(point);
		}
		for(int i=0;i<200;i++){
			double x=Math.random()*(75);
			double y=Math.random()*(75);
			Point point=new Point(x,y);
			points.add(point);
		}
		for(int i=0;i<300;i++){
			double x=Math.random()*(100);
			double y=Math.random()*(100);
			Point point=new Point(x,y);
			points.add(point);
		}
		
		return points;
	}
}
