interface IArithmeticsDiff {
    double Difference(double A, double B);
}

public class Arithmetics implements IArithmeticsDiff {

    @Override
    public double Difference(double A, double B) {
        return A - B;
    }
}
