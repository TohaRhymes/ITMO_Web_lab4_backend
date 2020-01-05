package app.Service;

import app.model.Point;
import app.model.User;
import app.repository.PointRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class PointService {

    private final PointRepository pointRepository;


    public PointService(PointRepository repository){
        this.pointRepository = repository;
    }


    public void addPoint(Point p){
        pointRepository.save(p);
    }

}
