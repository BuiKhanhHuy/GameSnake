package controller;

public class Point {
private int x, y;

public Point() {
	this.setX(0);
	this.setY(0);
}
public Point (int x, int y)
{
	this.setX(x);
	this.setY(y);
}
public Point (Point point)
{
	this.setX(point.getX());
	this.setY(point.getY());
}
// qua trai
public void quaTrai()
{
	this.setX(getX() -20);
}
// qua phai 
public void quaPhai() {
	this.setX(getX() + 20);
}
// len tren 
public void len() {
	this.setY(getY() - 20);
}
// xuong duoi
public void xuong() {
	this.setY(getY() + 20);
}

public Point traVeDiem()
{
	return new Point(this.getX(), this.getY());
}
public boolean soSanhHaiDiem(Point p)
{
	if(p.getX() == this.getX() && p.getY() == this.getY())
		return true;
	return false;
}
public  int getX() {
	return x;
}

public  int getY() {
	return y;
}

public  void setX(int x) {
	this.x = x;
}

public  void setY(int y) {
	this.y = y;
}


}
