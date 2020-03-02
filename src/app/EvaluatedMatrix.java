package app;

public class EvaluatedMatrix {
    Matrix matrix;

    public EvaluatedMatrix(double[][] matrix) throws Exception {
        this.matrix = new Matrix(matrix);
        getMatrix();
        getCofactorMatrix();
        getDeterminant();
    }
    public double[][] getMatrix() {
        return matrix.getMatrix();
    }

    public Matrix getCofactorMatrix() throws Exception{
        return matrix.getCofactorMatrix();
    }

    public double getDeterminant() throws Exception{
        return matrix.getDeterminant();
    }
}