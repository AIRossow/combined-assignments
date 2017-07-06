package com.cooksys.ftd.assignments.control;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * The Fibonacci sequence is simply and recursively defined: the first two elements are `1`, and
 * every other element is equal to the sum of its two preceding elements. For example:
 * <p>
 * [1, 1] =>
 * [1, 1, 1 + 1]  => [1, 1, 2] =>
 * [1, 1, 2, 1 + 2] => [1, 1, 2, 3] =>
 * [1, 1, 2, 3, 2 + 3] => [1, 1, 2, 3, 5] =>
 * ...etc
 */
public class Fibonacci {

    /**
     * Calculates the value in the Fibonacci sequence at a given index. For example,
     * `atIndex(0)` and `atIndex(1)` should return `1`, because the first two elements of the
     * sequence are both `1`.
     *
     * @param i the index of the element to calculate
     * @return the calculated element
     * @throws IllegalArgumentException if the given index is less than zero
     */
    public static int atIndex(int i) throws IllegalArgumentException {
    	if(i < 0) throw new IllegalArgumentException();
    	
    	int a = 1;
    	int b = 1;
    	int val = 1;
    	
    	if(i == 0 || i == 1)
        	return 1;
    	
    	else 
    	{
    		for(int j = 2; j <= i; j++)
    		{
    			val = a + b;
    			a = b;
    			b = val;
    		}
    		return val;
    	}
//        
//        else 
//        	return (atIndex(i-1) + atIndex(i-2));
    }

    /**
     * Calculates a slice of the fibonacci sequence, starting from a given start index (inclusive) and
     * ending at a given end index (exclusive).
     *
     * @param start the starting index of the slice (inclusive)
     * @param end   the ending index of the slice(exclusive)
     * @return the calculated slice as an array of int elements
     * @throws IllegalArgumentException if either the given start or end is negative, or if the
     *                                  given end is less than the given start
     */
    public static int[] slice(int start, int end) throws IllegalArgumentException {
        int arr[] = new int[end - start];
    	
        for(int i = start; i < end; i++)
        {
        	arr[i-start] = atIndex(i);
        }
        
        return arr;
    }

    /**
     * Calculates the beginning of the fibonacci sequence, up to a given count.
     *
     * @param count the number of elements to calculate
     * @return the beginning of the fibonacci sequence, up to the given count, as an array of int elements
     * @throws IllegalArgumentException if the given count is negative
     */
    public static int[] fibonacci(int count) throws IllegalArgumentException {
        if(count < 0) throw new IllegalArgumentException();
    	int arr[] = new int[count];
        
        for(int i = 0; i < count; i++)
        {
        	arr[i] = atIndex(i);
        }
        
        return arr;
    }
    
    public static void main(String[] args) {
        //throw new NotImplementedException();
    	for(int i = 0; i < 100; i++){	
    	System.out.println(atIndex(i));
    }
    }
}
