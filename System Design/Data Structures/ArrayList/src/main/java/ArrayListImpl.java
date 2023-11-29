import CustomArrayList.CustomArrayList;
import CustomArrayList.LinearArrayList;

public class ArrayListImpl {
    public static void main(String[] args) {
        CustomArrayList<Integer> customArrayList = new LinearArrayList<Integer>();

        System.out.println("Size: " + customArrayList.size());
        customArrayList.add(1);
        customArrayList.add(2);
        customArrayList.add(3);
        customArrayList.add(4);
        System.out.println("GET: " + customArrayList.get(2));
        System.out.println("GET: " + customArrayList.get(5));
        customArrayList.add(5);
        System.out.println("DELETE: " + customArrayList.delete(2));
        System.out.println("GET: " + customArrayList.get(2));
        customArrayList.add(6);
    }
}
