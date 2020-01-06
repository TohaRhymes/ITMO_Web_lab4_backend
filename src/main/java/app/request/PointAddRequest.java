package app.request;

import app.model.History;
import app.model.Point;
import app.model.User;
import app.plot.Checker;
import lombok.Data;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.io.Serializable;

@Data
public class PointAddRequest implements Serializable {
    private double x;
    private double y;
    private double r;


    public Point createPoint(String name, User user) {
        Point point = new Point(name, user);
        point.getHistory().add(new History(point, x, y, r, Checker.checkPoint(x, y, r)));
        return point;
    }

    public void check() {
        if (!(Checker.checkRadius(r) && Checker.checkCoordinates(x, y))) {
            throw new HttpMessageNotReadableException("Out of range");
        }
    }
}
