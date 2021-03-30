// interface for addition
interface IArithmeticsAdd {
    double Addition(double A, double B);
    //Comment of Wixaxis no. 1
    // Little comment there
}

interface IArithmeticsDiff {
    double Difference(double A, double B);
    //Comment from Wixaxis 2
}

// interface for multiplication
interface IArithmeticMult {
    double Multiplication(double A, double B);
    //third comment of Wixaxis
}

// interface for division
interface IArithmeticsDiv {
    double Division(double A, double B);
    //New feature for 6th excercise from Wixaxis
}

public class Arithmetics implements IArithmeticsAdd, IArithmeticsDiff, IArithmeticMult, IArithmeticsDiv {
    @Override
    public double Addition(double A, double B) {
        return A + B;
    }

    //comment 1

    @Override
    public double Difference(double A, double B) {
        return A - B;
    }

    //comment2
    @Override
    public double Multiplication(double A, double B) {
        // And little comment over there
        return A * B;
    }

    //commit3

    @Override
    public double Division(double A, double B) {
        if (B == 0)
            throw new IllegalArgumentException();
        return A / B;
    }
}
