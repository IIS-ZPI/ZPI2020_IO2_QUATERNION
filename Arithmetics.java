interface IArithmeticsAdd {
    double Addition(double A, double B);
}

interface IArithmeticsDiff {
    double Difference(double A, double B);
}

interface IArithmeticMult{
    double Multiplication(double A,double B);
}

public class Arithmetics implements IArithmeticsAdd, IArithmeticsDiff, IArithmeticMult {
    @Override
    public double Addition(double A, double B) {
        return A+B;
    }
  
    @Override
    public double Difference(double A, double B) {
        return A - B;
    }
    
    @Override
    public double Multiplication(double A, double B) {
        return A*B;
    }
}
