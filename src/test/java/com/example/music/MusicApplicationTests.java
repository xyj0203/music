package com.example.music;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.executable.ExecutableValidator;

@SpringBootTest
class MusicApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test1(){
        System.out.println();
    }

    public void quickSort(int[] arr, int left, int right){
        if(left < right){
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot-1);
            quickSort(arr, pivot+1, right);
        }
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for(int j = left; j < right; j++){
            if(arr[j] < pivot){
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    private void swap(int[] arr, int i, int right) {
        int temp = arr[i];
        arr[i] = arr[right];
        arr[right] = temp;
    }


}