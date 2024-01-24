package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int [] numArr = {1,3,-1,-3,5,3,6,7};
        int windowSize = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(numArr, windowSize)));
    }

    static int [] maxSlidingWindow(int [] numArr, int windowSize) {

        if (windowSize > numArr.length) {
            windowSize = numArr.length;
        }

        int [] output = new int [numArr.length - windowSize + 1];
        List<Integer> currentWindow = new ArrayList<>();

        for (int i = 0; i < windowSize; i++) {
            cleanUp(i, currentWindow, numArr);
            currentWindow.add(i);
        }

        output[0] = numArr[currentWindow.getFirst()];

        for (int i = windowSize; i < numArr.length; i++) {
            cleanUp(i, currentWindow, numArr);

            if (!currentWindow.isEmpty() && currentWindow.getFirst() <= i - windowSize) {
                currentWindow.removeFirst();
            }

            currentWindow.add(i);
            output[i - windowSize + 1] = numArr[currentWindow.getFirst()];
        }

        return output;
    }

    private static void cleanUp(int index, List<Integer> currentWindow, int[] numArr) {
        while (!currentWindow.isEmpty() && numArr[index] >= numArr[currentWindow.getLast()]) {
            currentWindow.removeLast();
        }
    }
}