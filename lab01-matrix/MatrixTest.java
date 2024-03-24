import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatrixTest {

    @org.junit.jupiter.api.Test
    void asArray()
    {

    }

    @org.junit.jupiter.api.Test
    void get() {
        Matrix get_test = new Matrix(3, 5);
        double get_result_c = get_test.get(1, 1);
        double get_result_d = get_test.get(2, 2);

        assertNotEquals(3, get_result_c);
        assertEquals(0, get_result_d);
        assertThrows(IllegalArgumentException.class, () -> get_test.get(6, 1));
    }

    @org.junit.jupiter.api.Test
    void set()
    {
        Matrix set_test = new Matrix(5, 5);
        set_test.set(3, 4, 17);
        assertEquals(17, set_test.get(3, 4));
        assertNotEquals(1, set_test.get(0, 0));
    }

    @org.junit.jupiter.api.Test
    void get_row()
    {
        Matrix m = new Matrix(5, 4);
        assertEquals(5, m.get_row());
        assertNotEquals(4, m.get_row());
    }

    @org.junit.jupiter.api.Test
    void get_col()
    {
        Matrix m = new Matrix(5, 4);
        assertEquals(4, m.get_col());
        assertNotEquals(10, m.get_col());
    }

    @org.junit.jupiter.api.Test
    void testToString()
    {
        double[][] array1 = {{0, 2, 4}, {1, -1, 0}, {-2, 1, -3}};
        Matrix m = new Matrix(array1);

        String m_toString = "\n[[0, 2, 4],\n[1, -1, 0],\n[-2, 1, -3]]";
        assertEquals(m.toString(), m_toString);

    }

    @org.junit.jupiter.api.Test
    void reshape()
    {
        Matrix m = new Matrix(4, 8);
        m.reshape(16, 2);
        assertEquals(16, m.get_row());
        assertEquals(2, m.get_col());
        assertThrows(IllegalArgumentException.class, ()-> m.reshape(4, 4));
    }

    @org.junit.jupiter.api.Test
    void shape()
    {
        double [][] array1 = {{1, 2, 3}, {1, 2, 3}};
        Matrix m = new Matrix(array1);
        int [] tab = m.shape();
        assertEquals(2, tab[0]);
        assertNotEquals(5, tab[1]);
    }

    @org.junit.jupiter.api.Test
    void add()
    {
        double [][] array1 = {{1, 2, 3}, {1, 2, 3}};
        double [][] array2 = {{1, 2, 3}, {1, 2, 3}};
        double [][] expected = {{2, 4, 6}, {2, 4, 6}};
        Matrix m1 = new Matrix(array1);
        Matrix m2 = new Matrix(array2);
        Matrix res = new Matrix(expected);
        assertEquals(res.toString(), m1.add(m2).toString());

        double [][] array3 = {{1, 2}, {1, 2}, {1, 2}};
        Matrix m3 = new Matrix(array3);
        assertThrows(IllegalArgumentException.class, ()->m1.add(m3));
    }

    @org.junit.jupiter.api.Test
    void sub()
    {
        double [][] array1 = {{1, 2, 3}, {1, 2, 3}};
        double [][] array2 = {{1, 2, 3}, {1, 1, 3}};
        double [][] expected = {{0, 0, 0}, {0, 1, 0}};
        Matrix m1 = new Matrix(array1);
        Matrix m2 = new Matrix(array2);
        Matrix res = new Matrix(expected);
        assertEquals(res.toString(), m1.sub(m2).toString());

        double [][] array3 = {{1, 2}, {1, 2}, {1, 2}};
        Matrix m3 = new Matrix(array3);
        assertThrows(IllegalArgumentException.class, ()->m1.sub(m3));
    }

    @org.junit.jupiter.api.Test
    void mul()
    {
        double [][] array1 = {{1, 2, 3}, {1, 2, 3}};
        double [][] array2 = {{1, 2, 3}, {1, 2, 3}};
        double [][] expected = {{1, 4, 9}, {1, 4, 9}};
        Matrix m1 = new Matrix(array1);
        Matrix m2 = new Matrix(array2);
        Matrix res = new Matrix(expected);
        assertEquals(res.toString(), m1.mul(m2).toString());

        double [][] array3 = {{1, 2}, {1, 2}, {1, 2}};
        Matrix m3 = new Matrix(array3);
        assertThrows(IllegalArgumentException.class, ()->m1.mul(m3));
    }

    @org.junit.jupiter.api.Test
    void div()
    {
        double [][] array1 = {{1, 2, 3}, {1, 2, 3}};
        double [][] array2 = {{1, 2, 3}, {1, 2, 3}};
        double [][] expected = {{1, 1, 1}, {1, 4, 1}};
        Matrix m1 = new Matrix(array1);
        Matrix m2 = new Matrix(array2);
        Matrix res = new Matrix(expected);
        assertNotEquals(res.toString(), m1.div(m2).toString());

        double [][] array3 = {{1, 2}, {1, 2}, {1, 2}};
        Matrix m3 = new Matrix(array3);
        assertThrows(IllegalArgumentException.class, ()->m1.div(m3));
    }

    @org.junit.jupiter.api.Test
    void testAdd()
    {
        double [][]array1 = {{5, 5, 0}, {3, 4, 5}, {0, 0, 9}};
        Matrix m = new Matrix(array1);
        int value = 10;
        double [][] expected = {{15, 15, 10}, {13, 14, 15}, {10, 10, 19}};
        Matrix result = new Matrix(expected);
        assertEquals(result.toString(), m.add(value).toString());
    }

    @org.junit.jupiter.api.Test
    void testSub()
    {
        double [][]array1 = {{5, 5, 0}, {3, 4, 5}, {0, 0, 9}};
        Matrix m = new Matrix(array1);
        int value = 3;
        double [][] expected = {{2, 2, -3}, {0, 1, 2}, {-3, -3, 6}};
        Matrix result = new Matrix(expected);
        assertEquals(result.toString(), m.sub(value).toString());
    }

    @org.junit.jupiter.api.Test
    void testMul()
    {
        double [][]array1 = {{5, 5, 0}, {3, 4, 5}, {0, 0, 9}};
        Matrix m = new Matrix(array1);
        int value = 4;
        double [][] expected = {{20, 20, 0}, {12, 16, 20}, {0, 0, 36}};
        Matrix result = new Matrix(expected);
        assertEquals(result.toString(), m.mul(value).toString());
    }

    @org.junit.jupiter.api.Test
    void testDiv()
    {
        double [][]array1 = {{5, 5, 0}, {3, 4, 5}, {0, 0, 9}};
        Matrix m = new Matrix(array1);
        int value = 0;
        assertThrows(IllegalArgumentException.class, ()->m.div(value).toString());

        double [][]expected = {{1, 1, 0}, {0.6, 0.8, 1}, {0, 0, 1.8}};
        int value2 = 5;
        Matrix result = new Matrix(expected);
        assertEquals(result.toString(), m.div(value2).toString());
    }

    @org.junit.jupiter.api.Test
    void mull()
    {
        double [][] array1 = {{1, 2}, {3, 4}};
        double [][] array2 = {{1, 2, 4}, {1, 2, 4}};
        Matrix m1 = new Matrix(array1);
        Matrix m2 = new Matrix(array2);
        double [][] expected = {{3, 6}, {7, 14}};
        Matrix result = new Matrix(expected);
        assertEquals(result.toString(), m1.mull(m2).toString());

        double [][] array3 = {{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};
        Matrix m3 = new Matrix(array3);
        assertThrows(IllegalArgumentException.class, ()->m1.mull(m3));
    }

    @org.junit.jupiter.api.Test
    void frobenius()
    {
        double [][]array1 = {{1, 2}, {3, 4}};
        Matrix m1 = new Matrix(array1);
        double result = Math.sqrt(30);

        assertEquals(result, m1.frobenius(), 0.00001);
    }

    @org.junit.jupiter.api.Test
    void eye()
    {
        Matrix m = Matrix.eye(10);
        double result = Math.sqrt(10);
        assertEquals(result, m.frobenius());
    }

    @org.junit.jupiter.api.Test
    void gaussianElimination()
    {
        double[][] array1 = {{5, 3, 1, 2}, {9, 3, 1, 4}, {1, 8, 0, 0}, {5, 5, 0, 0}};
        Matrix m1 = new Matrix(array1);
        double [][] expected = {{5, 3, 1, 2}, {0, -2.4, -0.8, 0.4}, {0, 0, -2.67, 0.83}, {0, 0, 0, -2.19}};
        Matrix result = new Matrix(expected);

        Matrix m2 = Matrix.gaussianElimination(m1);

        assertEquals(result.toString(), m2.toString());

    }

    @org.junit.jupiter.api.Test
    void determinant()
    {
        double[][] array1 = {{0, 2, 4}, {1, -1, 0}, {-2, 1, -3}};
        Matrix m1 = new Matrix(array1);
        double result = -2.0;

        assertEquals(result, m1.determinant());
    }

    @org.junit.jupiter.api.Test
    void inverse()
    {
        double[][] array1 = {{0, 2, 4}, {1, -1, 0}, {-2, 1, -3}};
        Matrix m1 = new Matrix(array1);

        double [][] expected = {{-0.5, -1, 1.5}, {0, -2, 1.5}, {0, 0, -0.5}};
        Matrix result = new Matrix(expected);

        assertEquals(result.toString(), Matrix.inverse(m1).toString());

        double[][] array2 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        Matrix m2 = new Matrix(array2);
        assertThrows(IllegalArgumentException.class, ()->Matrix.inverse(m2));
    }
}