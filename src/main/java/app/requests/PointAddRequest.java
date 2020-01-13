package app.requests;

import app.model.History;
import app.model.Point;
import app.model.User;
import app.plot.Checker;
import lombok.Setter;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.io.Serializable;

public class PointAddRequest implements Serializable {

    @Setter
    private double x;
    @Setter
    private double y;
    @Setter
    private double r;


    public Point createPoint(User user) {
        Point point = new Point(r, user);
        point.getHistories().add(new History(point, x, y));
        return point;
    }

    public void check() {
        if (!(Checker.checkRadius(r) && Checker.checkCoordinates(x, y))) {
            throw new HttpMessageNotReadableException("Out of range");
        }
    }
}
