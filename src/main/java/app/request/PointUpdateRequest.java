package app.request;

import app.plot.Checker;
import lombok.Data;
import org.springframework.http.converter.HttpMessageNotReadableException;


@Data
public class PointUpdateRequest {
    private double x;
    private double y;
    private double r;
    private int id;


    public void check() {
        if (!(Checker.checkRadius(r) && Checker.checkCoordinates(x, y))) {
            throw new HttpMessageNotReadableException("Out of range");
        }
    }

}
