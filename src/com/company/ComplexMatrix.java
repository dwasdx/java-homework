package com.company;

public class ComplexMatrix {
    private ComplexNumber[][] mat;
    private int n, m;

    ComplexMatrix() {
        this.n = 1;
        this.m = 1;
        mat = new ComplexNumber[this.n][this.m];
        mat[0][0] = new ComplexNumber(0, 0);
    }

    ComplexMatrix(int n, int m) throws IllegalArgumentException {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("One of the dimensions size is a negative integer");
        }
        _sizeAllocate(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ComplexNumber num = new ComplexNumber(0, 0);
                mat[i][j] = num;
            }
        }
    }

    ComplexMatrix(ComplexNumber[][] src) {
        this.n = src.length;
        this.m = src[0].length;
        this.mat = src.clone();
    }

    ComplexMatrix(ComplexMatrix src) {
        this.n = src.n;
        this.m = src.m;
        this.mat = new ComplexNumber[src.n][src.m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = new ComplexNumber(src.mat[i][j]);
            }
        }
    }

    public ComplexMatrix multByConst(double c) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j].multByConst(c);
            }
        }
        return this;
    }

    public ComplexMatrix add(ComplexMatrix mat) throws IrrelevantMatricesDimensions {
        if (this.n != mat.n || this.m != mat.m) {
            throw new IrrelevantMatricesDimensions("Matrices Dimensions do not coincide");
        }
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                this.mat[i][j].add(mat.mat[i][j]);
            }
        }
        return this;
    }

    public ComplexMatrix sub(ComplexMatrix mat) throws IrrelevantMatricesDimensions {
        return add(mat.multByConst(-1));
    }

    public ComplexMatrix mult(ComplexMatrix mat) throws IrrelevantMatricesDimensions {
        ComplexNumber[][] temp;
        if (this.n == mat.m) {
            temp = new ComplexNumber[this.n][mat.m];
        } else if (this.m == mat.n) {
            temp = new ComplexNumber[mat.n][this.n];
        } else {
            throw new IrrelevantMatricesDimensions("Matrix dimensions do not coincide");
        }

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                temp[i][j] = _multiplyRowAndColumn(getRow(i), mat.getColumn(j));
            }
        }
        _sizeAllocate(temp.length, temp[0].length);
        this.mat = temp;
        return this;
    }

    public ComplexMatrix T() {
        ComplexNumber[][] temp = new ComplexNumber[m][n];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                temp[i][j] = mat[j][i];
            }
        }
        mat = temp;
        return this;
    }

    private void _sizeAllocate(int n, int m) {
        this.n = n;
        this.m = m;
        mat = new ComplexNumber[n][m];
    }

    private ComplexNumber _multiplyRowAndColumn(ComplexNumber[] row, ComplexNumber[] column) {
        ComplexNumber sum = new ComplexNumber();
        for (int i = 0; i < row.length; i++) {
            sum.add(row[i].mult(column[i]));
        }
        return sum;
    }

    public ComplexNumber getElement(int row, int col) { return mat[row][col]; }

    public int rows_count() {
        return n;
    }

    public int columns_count() {
        return m;
    }

    public ComplexNumber[] getRow(int row_index) {
        ComplexNumber[] row = new ComplexNumber[n];
        for (int j = 0; j < m; j++) {
            row[j] = new ComplexNumber(mat[row_index][j]);
        }
        return row;
    }

    public ComplexNumber[] getColumn(int col_index) {
        ComplexNumber[] col = new ComplexNumber[m];
        for (int i = 0; i < n; i++) {
            col[i] = new ComplexNumber(mat[i][col_index]);
        }
        return col;
    }

    public void setElement(int row_ind, int col_ind, ComplexNumber num) {
        mat[row_ind][col_ind] = num;
    }

    public int getRowsCount() {
        return n;
    }

    public int getColumnCount() {
        return m;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Rows: " + n + "\nColumns: " + m + "\nMatrix:\n");
        for (ComplexNumber[] row : mat) {
            for (ComplexNumber elem : row) {
                str.append("(").append(elem.real).append(" ").append(elem.img).append(") ");
//                str.append(elem).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }
}


