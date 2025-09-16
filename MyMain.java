import java.lang.Cloneable;

class Position3D implements Cloneable {
    public static final Position3D ORIGIN = new Position3D(0, 0, 0);
    private double x;
    private double y;
    private double z;

    public Position3D(){
        this.x = Math.random() * 100;
        this.y = Math.random() * 100;
        this.z = Math.random() * 100;
    }

    public Position3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public double getX() { return x; }
    public void setX(double x) {this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getZ() { return z; }
    public void setZ(double z) { this.z = z; }

    @Override
    public Position3D clone() {
      return new Position3D(this.x, this.y, this.z);
    }
  
    @Override
    public String toString() {
        return String.format("(x=%.2f, y=%.2f, z=%.2f)", x, y, z);
    }

    public static double distanceTo(Position3D pointA, Position3D pointB){
        return Math.sqrt(
             Math.pow(pointA.getX()-pointB.getX(),2)
            +Math.pow(pointA.getY()-pointB.getY(),2)
            +Math.pow(pointA.getZ()-pointB.getZ(),2)
        );
    }

    @Override
    public boolean equals (Object other){
        if(other instanceof Position3D){
            Position3D positionObject = (Position3D) other;
            return this.getX()==positionObject.getX() && this.getY()== positionObject.getY() && this.getZ() == positionObject.getZ();
        }
        return false;
    }
}

public class MyMain {
  public static void main(String[] args) {
    Position3D point1 = new Position3D(1.0, 2.0, 3.0);
    Position3D point2 = new Position3D(4.0, 5.0, 6.0);
    System.out.println("Point 1: " + point1);
    System.out.println("Point 2: " + point2);
    
    Position3D point3 = new Position3D();
    System.out.println("Point 3 (random): " + point3);
    
    Position3D origin = new Position3D(0, 0, 0);
    double distance = Position3D.distanceTo(Position3D.ORIGIN, point3);
    System.out.printf("Distance from point3 to origin: %.2f%n", distance);
    
    Position3D point4 = point2.clone();
    System.out.println("Point 4 (clone of Point 2): " + point4);

    System.out.println("Point2 equals Point4?: " + point2.equals(point4));

    point2 = null;
    // System.out.println("Point2 equals Point4 after point2=null? " + point2.equals(point4));
    //Null pointer exception
  }
}