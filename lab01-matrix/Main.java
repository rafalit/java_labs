public class Main {
    public static void main(String[] args) {
        double[][] array = {{1, 2, 3, 4}, {5, 6}, {7, 8}, {9}};
        Matrix m = new Matrix(array);
        System.out.println(m.toString());
        m.reshape(8, 2);
        System.out.println(m.toString());
        int[] di = m.shape();
        System.out.println(di[0] + "x" + di[1]);

        double[][] array1 = {{1, 2, 3, 4}, {5, 6, 2}, {7, 8}, {9}};
        Matrix matrix1 = new Matrix(array);
        Matrix matrix2 = new Matrix(array1);

        Matrix sum = matrix1.add(matrix2);
        System.out.println(sum.toString());
    }
}