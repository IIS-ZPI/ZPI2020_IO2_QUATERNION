interface IArthemeticMult{
    double Multiplication(double A,double B);
}
public class Arithmetics implements IArthemeticMult{

    @Override
    public double Multiplication(double A, double B) {
        return A*B;
    }

}
