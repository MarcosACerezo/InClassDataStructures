package SearchingBag;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.text.Position;

public class ArrayListExample {
    
    public static void main(String[] args) {
        
        ArrayList<Integer> numbers = new ArrayList<>();
        boolean containsTwenty = numbers.contains(20);
        int size = numbers.size();
        
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println("First element: " + numbers.get(0));
        
        numbers.remove(1);

        System.out.println("Contains 20? " + containsTwenty);
        System.out.println("Size of the list: " + size);

        Collections.sort(numbers);
        for (Integer num : numbers) {
            System.out.println("Number: " + num);
        }

        ArrayList<Position3D> points = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            points.add(new Position3D());
        }
        
        System.out.println("First: " + points.get(0) + ", Last: " + points.get(points.size()-1));
        

        points.remove(1);
        
        if(!points.contains(Position3D.ORIGIN))
            points.add(1, Position3D.ORIGIN);
        
        System.out.println("Size: " + points.size());
        
        double distance = -1;
        Position3D farthest = points.get(0);
        
        for(int i = 0; i < points.size(); i++){
            if(distance< Position3D.distanceTo(Position3D.ORIGIN, points.get(i))){
                distance = Position3D.distanceTo(Position3D.ORIGIN, points.get(i));
                farthest = points.get(i);
            }
            
            System.out.println(Position3D.distanceTo(Position3D.ORIGIN, points.get(i)));
           
        }
        System.out.println("\n\n" +farthest);
        System.out.println(Position3D.distanceTo(Position3D.ORIGIN, farthest));

    }
    
}