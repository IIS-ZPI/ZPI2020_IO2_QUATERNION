interface IArithmeticsAdd {
    double Addition(double A, double B);
  }
  
class Arithmetics implements IArithmeticsAdd {
    public double Addition(double A, double B) {
        return A+B;
    }
}