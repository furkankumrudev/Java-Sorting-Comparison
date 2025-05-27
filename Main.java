package Lecture.Sorting;

import java.util.Arrays;
import java.util.Random;

public class Main {
    
    // Selection Sort: Finds min element in each iteration and swaps it to the front
    static void selectionSort(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min_idx];
            arr[min_idx] = temp;           
        }
    }

    // Bubble Sort: Repeatedly swaps adjacent elements if they're in wrong order
    static void bubbleSort(int array[]) {
        int size = array.length;
        for (int i = 0; i < size - 1; i++)
            for (int j = 0; j < size - i - 1; j++)
                if (array[j] > array[j + 1]) {
                  int temp = array[j];
                  array[j] = array[j + 1];
                  array[j + 1] = temp;
                }
    }

    // Helper function for Merge Sort: Merges two sorted subarrays
    static void merge(int array[], int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        int L[] = new int[n1];
        int M[] = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = array[p + i];
        for (int j = 0; j < n2; j++)
            M[j] = array[q + 1 + j];

        int i, j, k;
        i = 0;
        j = 0;
        k = p;

        while (i < n1 && j < n2) {
            if (L[i] <= M[j]) {
              array[k] = L[i];
              i++;
            } else {
              array[k] = M[j];
              j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = M[j];
            j++;
            k++;
        }
    }

    // Merge Sort: Divide and conquer algorithm with O(n log n) complexity
    static void mergeSort(int array[], int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    // Helper function for Quick Sort: Partitions the array
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);  
        return i + 1;
    }
    
    // Swaps two elements in an array
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Quick Sort: Efficient divide and conquer with pivot selection
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    public static void main(String[] args) {
        int[] arr = new int[10000];
        Random rd = new Random();

        for (int j = 0; j < 4; j++) {
            // Fill array with random numbers
            for (int i = 0; i < arr.length; i++) {
                arr[i] = rd.nextInt(10000);
            }

            // Create copies for each sorting algorithm
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            int[] arr2 = Arrays.copyOf(arr, arr.length);
            int[] arr3 = Arrays.copyOf(arr, arr.length);
            int[] arr4 = Arrays.copyOf(arr, arr.length);

            long startTime, endTime, executionTime;

            // Benchmark Selection Sort
            startTime = System.nanoTime();
            selectionSort(arr1);
            endTime = System.nanoTime();
            executionTime = (endTime - startTime) / 1000000;
            System.out.println("Selection sort: " + executionTime + "ms");

            // Benchmark Bubble Sort
            startTime = System.nanoTime();
            bubbleSort(arr2);
            endTime = System.nanoTime();
            executionTime = (endTime - startTime) / 1000000;
            System.out.println("Bubble sort: " + executionTime + "ms");

            // Benchmark Merge Sort
            startTime = System.nanoTime();
            mergeSort(arr3, 0, arr3.length - 1);
            endTime = System.nanoTime();
            executionTime = (endTime - startTime) / 1000000;
            System.out.println("Merge sort: " + executionTime + "ms");

            // Benchmark Quick Sort
            startTime = System.nanoTime();
            quickSort(arr4, 0, arr4.length - 1);
            endTime = System.nanoTime();
            executionTime = (endTime - startTime) / 1000000;
            System.out.println("Quick sort: " + executionTime + "ms");

            System.out.println("-----------------------------------");
        }
    }
}