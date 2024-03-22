import java.util.function.Predicate;

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

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("\n[");
        for (int i = 0; i < rows; i++) {
            buf.append("[");
            for (int j = 0; j < cols; j++) {
                buf.append(data[i * cols + j]);
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

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));

        if(rows == newRows && cols == newCols)
        {
            return;
        }

        Matrix new_data = new Matrix(newRows, newCols);

        for(int i=0; i<newRows; i++)
        {
            for(int j=0; j<newCols; j++)
            {
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

    int[] shape()
    {
        int [] tab = new int[2];
        tab[0]=rows;
        tab[1]=cols;
        return tab;
    }

    Matrix add(Matrix m)
    {
        int [] m_shape = m.shape();

        if(m_shape[0] == rows && m_shape[1] == cols)
        {
            double[][] add_matrixes = new double[rows][cols];

            for(int i=0; i<rows; i++)
            {
                for(int j=0; j<cols; j++)
                {
                    add_matrixes[i][j] = data[i*cols+j] + m.data[i*cols+j];
                }
            }
            Matrix add_type = new Matrix(add_matrixes);
            return add_type;
        }
        else
        {
            throw new IllegalArgumentException("Cannot add this two matrixes");
        }
    }

    Matrix sub(Matrix m)
    {
        int [] tab = m.shape();
        if(tab[0] == rows && tab[1] == cols)
        {
            double [][] sub_matrixes = new double [rows][cols];

            for(int i=0; i<rows; i++)
            {
                for(int j=0; j<cols; j++)
                {
                    sub_matrixes[i][j] = data[i*cols+j] - m.data[i*cols+j];
                }
            }
            Matrix sub_mat = new Matrix(sub_matrixes);
            return sub_mat;
        }
        else
        {
            throw new IllegalArgumentException("Cannot substract this two matrixes");
        }
    }
    Matrix mul(Matrix m)
    {
        int [] tab = m.shape();
        if(tab[0] == rows && tab[1] == cols)
        {
            double[][] mul_matrixes = new double [rows] [cols];
            for(int i=0; i<rows; i++)
            {
                for(int j=0; j<cols; j++)
                {
                    mul_matrixes[i][j] = data[i*cols+j] * m.data[i*cols+j];
                }
            }
            Matrix mul_mat = new Matrix(mul_matrixes);
            return mul_mat;
        }
        else
        {
            throw new IllegalArgumentException("Cannot multiply this two matrixes");
        }
    }
    Matrix div(Matrix m)
    {
        int [] tab = m.shape();
        if(tab[0] == rows && tab[1] == cols)
        {
            double[][] mul_matrixes = new double [rows] [cols];
            for(int i=0; i<rows; i++)
            {
                for(int j=0; j<cols; j++)
                {
                    mul_matrixes[i][j] = data[i*cols+j] / m.data[i*cols+j];
                }
            }
            Matrix mul_mat = new Matrix(mul_matrixes);
            return mul_mat;
        }
        else
        {
            throw new IllegalArgumentException("Cannot multiply this two matrixes");
        }
    }

    Matrix add(double w)
    {
        double [][] mat = new double [rows][cols];
        for(int i=0; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                mat[i][j] = data[i*cols+j] + w;
            }
        }
        Matrix add_mat = new Matrix(mat);
        return add_mat;
    }

    Matrix sub(double w)
    {
        double [][] mat = new double [rows][cols];
        for(int i=0; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                mat[i][j] = data[i*cols+j] - w;
            }
        }
        Matrix sub_mat = new Matrix(mat);
        return sub_mat;
    }

    Matrix mul(double w)
    {
        double [][] mat = new double [rows][cols];
        for(int i=0; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                mat[i][j] = data[i*cols+j] * w;
            }
        }
        Matrix mul_mat = new Matrix(mat);
        return mul_mat;
    }

    Matrix div(double w)
    {
        double [][] mat = new double [rows][cols];
        for(int i=0; i<rows; i++)
        {
            for(int j=0; j<cols; j++)
            {
                mat[i][j] = data[i*cols+j] / w;
            }
        }
        Matrix div_mat = new Matrix(mat);
        return div_mat;
    }

    Matrix mull(Matrix m)
    {
        int [] tab = m.shape();
        if(tab[0] == cols)
        {
            double [][] new_matrix = new double[rows][cols];
            for(int i=0; i<rows; i++)
            {
                for(int j=0; j<cols; j++)
                {
                    new_matrix[i][j] = 0;
                    for(int k=0; k<cols; k++)
                    {
                        new_matrix[i][j] += get(i, k) * m.get(k, j);
                    }
                }
            }
            return new Matrix(new_matrix);
        }
        else
        {
            throw new IllegalArgumentException("Those matrixes cannot be multiplied");
        }
    }
}
