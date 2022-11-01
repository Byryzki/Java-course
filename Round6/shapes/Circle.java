public class Circle implements IShapeMetrics{
    
    double radius;

    Circle(double radius){
        this.radius = radius;
    }

    public String toString(){
        return String.format("Circle with radius: %.2f",radius);
    }

    public String name(){
        return String.format("circle");
    }

    public double area(){
        return radius*radius*PI;
    }

    public double circumference(){
        return 2*radius*PI;
    }
}
