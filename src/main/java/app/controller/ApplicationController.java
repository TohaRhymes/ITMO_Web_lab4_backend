package app.controller;

import app.model.Point;
import app.model.User;
import app.request.PointAddRequest;
import app.request.PointUpdateRequest;
import app.request.UserAddRequest;
import app.response.BaseResponse;
import app.service.PointServiceImpl;
import app.service.impl.UserServiceImpl;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Set;


@RequestMapping("/")
@RestController
@EnableWebSecurity
public class ApplicationController {
    private final UserServiceImpl userService;
    private final PointServiceImpl pointService;

    public ApplicationController(PointServiceImpl pointService, UserServiceImpl userService) {
        this.pointService = pointService;
        this.userService = userService;
    }



    @GetMapping
    public RedirectView redirectToIndex() {
        return new RedirectView("/index.html");
    }

    @PostMapping("/add")
    public BaseResponse addPoint(@RequestBody PointAddRequest pointAddRequest){
        pointAddRequest.check();
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        //todo getNormalName
        Point point = pointAddRequest.createPoint("defaultname", user);
        pointService.addPoint(point);
        return new BaseResponse(200, user.getPoints());
    }

    @PostMapping("/register")
    public BaseResponse addUser(@RequestBody UserAddRequest userAddRequest){
        boolean isOk = userService.addUser(userAddRequest.createUser());
        return new BaseResponse(isOk?200:400, isOk);
    }

    @GetMapping("/get")
    public BaseResponse getElements(){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        return new BaseResponse(200, user.getPoints());
    }

    @PostMapping("/update")
    public BaseResponse updatePoint(@RequestBody PointUpdateRequest request){
        request.check();
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Set<Point> points = pointService.updatePoint(request.getId(), user, request.getX(), request.getY(), request.getR());
        int status = points==null?400:200;
        return new BaseResponse(status, points);
    }

    @PostMapping("/delete")
    public BaseResponse deletePoint(@RequestBody PointUpdateRequest request){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Set<Point> points = pointService.deletePoint(request.getId(), user);
        int status = points==null?400:200;
        return new BaseResponse(status, points);
    }

    @PostMapping("/ok")
    public BaseResponse ok(){
        return new BaseResponse(200,"ok");
    }
    @PostMapping("/err")
    public BaseResponse err(){
        return new BaseResponse(400,"error");
    }

}
