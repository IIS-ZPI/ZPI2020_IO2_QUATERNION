interface IArithmeticsDiv {
    double Division(double A, double B);
}

public class Arithmetics implements IArithmeticsDiv{
    @Override
    public double Division(double A, double B) {
        if(B == 0) throw new IllegalArgumentException();
        return A/B;
    }
}
