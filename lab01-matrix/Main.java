public class Main {
    public static void main(String[] args) {
        double[][] array1 = {{1, 2, 3, 4}, {5, 6}, {7, 8}, {9}};
        double[][] array2 = {{5, 3, 1, 2}, {9, 3, 1, 4}, {1, 8}, {5, 5}};

        Matrix m = new Matrix(array1);
        System.out.println(m.toString());
        m.reshape(8, 2);
        System.out.println(m.toString());
        int[] di = m.shape();
        System.out.println(di[0] + "x" + di[1]);


        Matrix matrix1 = new Matrix(array1);
        Matrix matrix2 = new Matrix(array2);

        Matrix sum = matrix1.mul(matrix2);
        System.out.println(sum.toString());
        m.reshape(4, 4);
        System.out.println(m.div(2));

        Matrix result = matrix1.mull(matrix2);
        System.out.print(result);
    }
}