public class Main {
    public static void main(String[] args) {
        double[][] array1 = {{0, 2, 4}, {1, -1, 0}, {-2, 1, -3}};
        double[][] array2 = {{5, 3, 1, 2}, {9, 3, 1, 4}, {1, 8}, {5, 5}};

        Matrix matrix1 = new Matrix(array1);
        Matrix matrix2 = new Matrix(array2);

        Matrix gauss = Matrix.gaussianElimination(matrix1);
        String res = gauss.toString();
        System.out.println(res);

        Matrix gauss2 = Matrix.gaussianElimination(matrix2);
        String res2 = gauss2.toString();
        System.out.println(res2);

        double det = matrix1.determinant();
        System.out.println(det);

        Matrix inverse1 = Matrix.inverse(matrix2);
        String res3 = inverse1.toString();
        System.out.println(res3);

        Matrix product = matrix2.mull(inverse1);
        String res4 = product.toString();
        System.out.println(res4);

        Matrix inverse2 = Matrix.inverse(matrix1);
        String res5 = inverse2.toString();
        System.out.println(res5);
    }
}