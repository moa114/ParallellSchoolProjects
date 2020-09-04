package Model;

import java.util.Random;

public class ArrayRandomizer<T> {
    private T[] array;

    public void randomize(){
        T tmp;
        int randNumber;
        Random rn = new Random();
        for (int i = array.length-1; 0<=i; i--){
            tmp = array[i];
            randNumber = rn.nextInt(i);
            array[i] = array[randNumber];
            array[randNumber] = tmp;
        }
    }

    public ArrayRandomizer(T[] array) {
        this.array = array;
    }
}
