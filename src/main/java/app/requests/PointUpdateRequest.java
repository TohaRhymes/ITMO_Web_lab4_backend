package app.requests;

import app.plot.Checker;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.converter.HttpMessageNotReadableException;

public class PointUpdateRequest {

    @Setter
    @Getter
    private double x;
    @Setter
    @Getter
    private double y;
    @Setter
    @Getter
    private int id;


    public void check() {
        if (!Checker.checkCoordinates(x,y)) {
            throw new HttpMessageNotReadableException("Out of range");
        }
    }


}
