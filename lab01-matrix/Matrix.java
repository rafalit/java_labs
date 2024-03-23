import java.text.DecimalFormat;
import java.util.function.Predicate;
import java.util.Random;
import static java.lang.Math.sqrt;

public class Matrix {
    double[] data;
    int rows;
    int cols;


    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }

    Matrix(double[][] d) {
        rows = d.length;
        int maxCols = 0;

        for (int i = 0; i < rows; i++) {
            if (d[i].length > maxCols) {
                maxCols = d[i].length;
            }
        }

        cols = maxCols;
        data = new double[rows * cols];

        for (int i = 0; i < rows; i++) {
            int c = d[i].length;

            for (int j = 0; j < c; j++) {
                data[i * cols + j] = d[i][j];
            }
            for (int j = c; j < maxCols; j++) {
                data[i * cols + j] = 0;
            }
        }
    }

    double[][] asArray() {
        double[][] double_data = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double_data[i][j] = data[i * cols + j];
            }
        }
        return double_data;
    }

    double get(int r, int c) {
        if (r >= rows || r < 0 || c >= cols || c < 0) {
            throw new RuntimeException("próbujesz pobrać element spoza rozmiaru matrixa!");
        }
        return data[r * cols + c];
    }

    void set(int r, int c, double value) {
        if (r >= rows || r < 0 || c >= cols || c < 0) {
            throw new RuntimeException("próbujesz pobrać element spoza rozmiaru matrixa!");
        }
        data[r * cols + c] = value;
    }

    int get_row() {
        return rows;
    }

    int get_col() {
        return cols;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("\n[");
        DecimalFormat df = new DecimalFormat("#.##");
        for (int i = 0; i < rows; i++) {
            buf.append("[");
            for (int j = 0; j < cols; j++) {
                String formattedValue;
                if(Math.abs(data[i * cols + j]) < 0.001)
                {
                    formattedValue = "0";
                }
                else {
                    formattedValue = df.format(data[i * cols + j]);
                }

                buf.append(formattedValue);
                if (j < cols - 1) {
                    buf.append(", ");
                }
            }
            if (i < rows - 1) {
                buf.append("],\n");
            } else {
                buf.append("]");
            }
        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows, int newCols) {
        if (rows * cols != newRows * newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));

        if (rows == newRows && cols == newCols) {
            return;
        }

        Matrix new_data = new Matrix(newRows, newCols);

        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < newCols; j++) {
                int index = i * newCols + j;
                int r = index / cols;
                int c = index % cols;
                new_data.set(i, j, get(r, c));
            }
        }
        this.rows = newRows;
        this.cols = newCols;
        this.data = new_data.data;
    }

    int[] shape() {
        int[] tab = new int[2];
        tab[0] = rows;
        tab[1] = cols;
        return tab;
    }

    Matrix add(Matrix m) {
        int[] m_shape = m.shape();

        if (m_shape[0] == rows && m_shape[1] == cols) {
            double[][] add_matrixes = new double[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    add_matrixes[i][j] = data[i * cols + j] + m.data[i * cols + j];
                }
            }
            Matrix add_type = new Matrix(add_matrixes);
            return add_type;
        } else {
            throw new IllegalArgumentException("Cannot add this two matrixes");
        }
    }

    Matrix sub(Matrix m) {
        int[] tab = m.shape();
        if (tab[0] == rows && tab[1] == cols) {
            double[][] sub_matrixes = new double[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    sub_matrixes[i][j] = data[i * cols + j] - m.data[i * cols + j];
                }
            }
            Matrix sub_mat = new Matrix(sub_matrixes);
            return sub_mat;
        } else {
            throw new IllegalArgumentException("Cannot substract this two matrixes");
        }
    }

    Matrix mul(Matrix m) {
        int[] tab = m.shape();
        if (tab[0] == rows && tab[1] == cols) {
            double[][] mul_matrixes = new double[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    mul_matrixes[i][j] = data[i * cols + j] * m.data[i * cols + j];
                }
            }
            Matrix mul_mat = new Matrix(mul_matrixes);
            return mul_mat;
        } else {
            throw new IllegalArgumentException("Cannot multiply this two matrixes");
        }
    }

    Matrix div(Matrix m) {
        int[] tab = m.shape();
        if (tab[0] == rows && tab[1] == cols) {
            double[][] mul_matrixes = new double[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    mul_matrixes[i][j] = data[i * cols + j] / m.data[i * cols + j];
                }
            }
            Matrix mul_mat = new Matrix(mul_matrixes);
            return mul_mat;
        } else {
            throw new IllegalArgumentException("Cannot multiply this two matrixes");
        }
    }

    Matrix add(double w) {
        double[][] mat = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = data[i * cols + j] + w;
            }
        }
        Matrix add_mat = new Matrix(mat);
        return add_mat;
    }

    Matrix sub(double w) {
        double[][] mat = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = data[i * cols + j] - w;
            }
        }
        Matrix sub_mat = new Matrix(mat);
        return sub_mat;
    }

    Matrix mul(double w) {
        double[][] mat = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = data[i * cols + j] * w;
            }
        }
        Matrix mul_mat = new Matrix(mat);
        return mul_mat;
    }

    Matrix div(double w) {
        double[][] mat = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = data[i * cols + j] / w;
            }
        }
        Matrix div_mat = new Matrix(mat);
        return div_mat;
    }

    Matrix mull(Matrix m) {
        int[] tab = m.shape();
        if (tab[0] == cols) {
            double[][] new_matrix = new double[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    new_matrix[i][j] = 0;
                    for (int k = 0; k < cols; k++) {
                        new_matrix[i][j] += get(i, k) * m.get(k, j);
                    }
                }
            }
            return new Matrix(new_matrix);
        } else {
            throw new IllegalArgumentException("Those matrixes cannot be multiplied");
        }
    }

    double frobenius() {
        double suma = 0;
        for (int i = 0; i < data.length; i++) {
            suma += data[i] * data[i];
        }
        return sqrt(suma);
    }

    public static Matrix random(int rows, int cols) {
        Matrix n = new Matrix(rows, cols);
        Random r = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                n.set(i, j, r.nextDouble());
            }
        }
        return n;
    }

    public static Matrix eye(int n) {
        Matrix m = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    m.set(i, j, 1);
                } else {
                    m.set(i, j, 0);
                }
            }
        }
        return m;
    }

    public static Matrix gaussianElimination(Matrix m)
    {
        int cols = m.get_col();
        int rows = m.get_row();

        for(int i=0; i<rows; i++)
        {
            if(m.data[i*cols+i] == 0)
            {
                int maxRow = i;
                for(int k=i+1; k<rows; k++)
                {
                    if(Math.abs(m.data[k*cols+i]) > Math.abs(m.data[maxRow*cols+i]))
                    {
                        maxRow = k;
                    }
                }

                for(int k = i; k<cols; k++)
                {
                    double temp = m.data[i*cols+k];
                    m.data[i*cols + k] =m.data[maxRow*cols+k];
                    m.data[maxRow*cols+k]=temp;
                }
            }

            if(m.data[i*cols+i] == 0)
            {
                throw new IllegalArgumentException("Dividing by 0!");
            }

            for(int j=i+1; j<rows; j++)
            {
                double factor = m.data[j*cols+i] / m.data[i*cols+i];
                for(int k=i; k<cols; k++)
                {
                    m.data[j*cols+k] -= factor * m.data[i*cols+k];
                }
            }
        }
        return m;
    }

    double determinant()
    {
        Matrix m = gaussianElimination(this);
        double determinant = 1;

        for(int i=0; i<rows; i++)
        {
            determinant *= m.data[i*cols+i];
        }
        return determinant;
    }

    public static Matrix inverse(Matrix m)
    {
        int cols = m.get_col();
        int rows = m.get_row();

        if(cols != rows || m.determinant()==0)
        {
            throw new IllegalArgumentException("This matrix cannot be inverted!");
        }

        Matrix wider = new Matrix(rows, cols*2);
        for(int i=0; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                wider.set(i, j, m.get(i, j));
                if(i==j)
                {
                    wider.set(i, j+cols, 1);
                }
                else
                {
                    wider.set(i, j+cols, 0);
                }
            }
        }

        gaussianElimination(wider);

        for(int i=rows-1; i>=0; i--)
        {
            for(int j=i-1; j>=0; j--)
            {
                double factor = wider.get(j, i) / wider.get(i, i);
                for(int k=0; k<2*cols; k++)
                {
                    wider.set(j, k, wider.get(j, k) - factor * wider.get(i, k));
                }
            }
        }

        for(int i=0; i<rows; i++)
        {
            double divisor = wider.get(i, i);
            for(int j=i; j<cols*2; j++)
            {
                wider.set(i, j, wider.get(i, j) / divisor);
            }
        }

        Matrix inverseMatrix = new Matrix(rows, cols);
        for(int i=0; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                inverseMatrix.set(i, j, wider.get(i, j + cols));
            }
        }

        return inverseMatrix;
    }
}
