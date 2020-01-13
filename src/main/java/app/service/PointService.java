package app.service;

import app.model.Point;
import app.model.User;

import java.util.Set;

public interface PointService {

    void addPoint(Point p);

    Set<Point> updatePoint(int id, User user, double x, double y);

    Set<Point> deletePoint(int id, User user);

}
