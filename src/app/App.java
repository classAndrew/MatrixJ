package app;

public class App {
    public static void main(String[] args) throws Exception {
        double[][] mat = {{22,1,4},
        {16,28,3},
        {26,13,1}
        };
        
        Matrix m = new Matrix(mat);

        System.out.println(m);
    }
}