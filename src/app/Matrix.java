package app;

public class Matrix {
    double[][] matrix;
    private Matrix cofactors;
    public Double determinant;
    private int[] order;
    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        order = new int[] {matrix.length, matrix[0].length};
    }

    public double findDeterminantClassic() throws Exception {
        // Matrix order m x n. m must equal n
        if (order[0] != order[1]) {
            throw new Exception("Only square matrices have determinants!");
        }
        if (matrix.length == 2) {
            return matrix[0][0]*matrix[1][1] - matrix[1][0]*matrix[0][1];
        }
        double[][] cofactors = this.getCofactorMatrix().matrix;
        double sum = 0;
        for (int c = 0; c < cofactors.length; c++) {
            sum += matrix[0][c]*cofactors[0][c];
        }
        return sum;
    }

    public double findDeterminant() throws Exception{
        if (order[0] != order[1]) {
            throw new Exception("Only square matrices have determinants!");
        }
        Matrix m = rowEchelon();
        double det = m.matrix[0][0];
        for (int i = 1; i < m.order[0]; i++) {
            det *= m.matrix[i][i];
        }
        return det;
    }

    public Matrix rowEchelon() throws Exception {
        /*
        Sample matrix
        1, 2, 3
        4, 5, 6
        7, 8, 9
        */
        Matrix reForm = new Matrix(matrix.clone());
        for (int i = 0; i < order[0]-1; i++) {
            for (int j = i+1; j < order[1]; j++) {
               
                reForm.addRowMultiple(i, j, reForm.matrix[j][i]/-reForm.matrix[i][i]);
            }
        }
        return reForm;
    }
    public Matrix submatrix(int row, int col) {
        double[][] newMat = new double[order[0]-1][order[1]-1];
        for (int i = 0; i < order[0]; i++) {
            for (int j = 0; j < order[1]; j++) {
                if (i != row && j != col) {
                    newMat[i-(i > row ? 1 : 0)][j-(j > col ? 1 : 0)] = matrix[i][j];
                }
            }
        }
        return new Matrix(newMat);
    }
    private Matrix minor() throws Exception {
        double[][] minor = new double[order[0]][order[1]];
        for (int i = 0; i < order[0]; i++) {
            for (int j = 0; j < order[1]; j++) {
                minor[i][j] = this.submatrix(i, j).findDeterminant();
            }
        }
        return new Matrix(minor);
    }
    private Matrix cofactors() throws Exception {
        double[][] cof = minor().matrix;
        for (int i = 0; i < order[0]; i++) {
            for (int j = 0; j < order[1]; j++) {
                cof[i][j] = cof[i][j]*((i+j)%2 == 1 ? -1 : 1);
            }
        }
        return new Matrix(cof);
    }
    // Elementary Row Operations
    // adds r2 = r1 + r2*multiple
    public void addRowMultiple(int r1, int r2, double m1) {
        for (int c = 0; c < order[1]; c++) {
            matrix[r2][c] = matrix[r2][c]+matrix[r1][c]*m1;
        }
    }
    // Getters
    public double getDeterminant() throws Exception{
        if (this.determinant == null) {
            this.determinant = this.findDeterminant();
        }
        return this.determinant;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public Matrix getCofactorMatrix() throws Exception {
        if (this.cofactors == null) {
            this.cofactors = this.cofactors();
        }
        return this.cofactors;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        double max = 0;
        for (int i = 0; i < order[0]; i++) {
            for (int j = 0; j < order[1]; j++) {
                
            }
        }
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sb.append(String.valueOf(matrix[i][j])+(j < matrix[0].length-1 ? ", " :""));
            }
            if (i < matrix.length-1) sb.append("\n");
        }
        return sb.toString();
    }
}