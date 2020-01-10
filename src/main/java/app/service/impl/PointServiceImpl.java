package app.service.impl;

import app.model.History;
import app.model.Point;
import app.model.User;
import app.plot.Checker;
import app.repository.HistoryRepository;
import app.repository.PointRepository;
import app.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;

    private final HistoryRepository historyRepository;


    public PointServiceImpl(PointRepository repository, HistoryRepository historyRepository) {
        this.pointRepository = repository;
        this.historyRepository = historyRepository;
    }

    @Override
    public void addPoint(Point p) {
        pointRepository.save(p);
        historyRepository.save((History) p.getHistory().toArray()[0]);
    }

    @Override
    public Set<Point> deletePoint(int id, User user) {
        Set<Point> points = user.getPoints();
        for (Point point : points) {
            if (point.getId() == id) {
                Set<Point> set = new LinkedHashSet<>(points);
                set.remove(point);
                pointRepository.delete(point);
                return set;
            }
        }
        return null;
    }

    @Override
    public Set<Point> updatePoint(int id, User user, double x, double y, double r) {
        Set<Point> points = user.getPoints();
        for (Point point : points) {
            if (point.getId() == id) {
                History element = new History(point, x, y, r, Checker.checkPoint(x, y, r));
                point.getHistory().add(element);
                historyRepository.save(element);
                return points;
            }
        }
        return null;
    }

}
