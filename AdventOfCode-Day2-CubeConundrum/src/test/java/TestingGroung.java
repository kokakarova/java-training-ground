import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class TestingGround {
    public static void main(String[] args) {
        int[] arr = {1, 0, 0, 0, 2, 2, 2};
        List<Integer> listArr = new ArrayList<>();
        for (int n : arr) {
            listArr.add(n);
        }
        int firstIndexOfOne = listArr.indexOf(1);
        int lastIndexOfOne = listArr.lastIndexOf(1);
        System.out.println("first index of 1: " + firstIndexOfOne);
        System.out.println("last index of 1: " + lastIndexOfOne);

        int firstIndexOfTwo = listArr.indexOf(2);
        int lastIndexOfTwo = listArr.lastIndexOf(2);
        System.out.println("first index of 2: " + firstIndexOfTwo);
        System.out.println("last index of 2: " + lastIndexOfTwo);

    }

}
