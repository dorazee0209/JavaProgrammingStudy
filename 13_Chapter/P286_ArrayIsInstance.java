public class P286_ArrayIsInstance {
    public static void main(String[] args) {
        // Create a int-typed array which has length = 5.
        int[] arr1 = new int[5];

        // Create a double-typed array which has length = 7.
        double[] arr2 = new double[7];

        // Seperate ref var and instantiation.
        float[] arr3;
        arr3 = new float[9];

        // Access to instance var of array.
        System.out.printf("The length of arr1: %d \n", arr1.length);
        System.out.printf("The length of arr2: %d \n", arr2.length);
        System.out.printf("The length of arr3: %d \n", arr3.length);
    }
}

