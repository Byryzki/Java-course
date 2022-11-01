public class Rectangle implements IShapeMetrics {
    
    double height;
    double width;

    Rectangle(double height, double width){
        this.height = height;
        this.width = width;
    }

    public String toString(){
        return String.format("Rectangle with height %.2f and width %.2f",height,width);
    }

    public String name(){
        return String.format("rectangle");
    }
    public double area(){
        return height*width;
    }
    public double circumference(){
        return height*2+width*2;
    }
}
