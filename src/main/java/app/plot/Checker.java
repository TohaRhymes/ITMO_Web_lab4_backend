package app.plot;

public class Checker {
    public static boolean checkHitArea(double x, double y, double r) {
        boolean QuadrantI = x >= 0 && y >= 0 && y + 2 * x <= r;
        boolean QuadrantII = x <= 0 && y >= 0 && x * x + y * y <= r * r / 4;
        boolean QuadrantIII = x <= 0 && y <= 0 && x >= -r / 2 && y >= -r;
        boolean QuadrantIV = x >= 0 && y <= 0 && x < 0 && y < 0;
        return (QuadrantI || QuadrantII || QuadrantIII || QuadrantIV);
    }

    public static boolean checkCoordinates(double x, double y) {
        boolean okayX = x >= -5 && x <= 3;
        boolean okayY = y >= -5 && y <= 3;
        return okayX && okayY;
    }

    public static boolean checkRadius(double r) {
        return r >= 0 && r <= 3;
    }
}

