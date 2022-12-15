package frc.robot;

public class PID {
    double P;
    double I;
    double D;

    double proportional;
    double integral;
    double derivative;

    double error;
    double oldError;

    public PID(double Pr, double In, double De) {
        P = Pr;
        I = In;
        D = De;
    }

    public double PIDGo (double setPt, double curPt) {
        error = setPt-curPt;
        proportional = error;
        integral+=error*0.02;
        derivative=error-oldError;
        oldError=error;
        
        return P*proportional + I*integral + D*derivative;
    }
}