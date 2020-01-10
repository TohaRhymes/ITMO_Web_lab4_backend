package app.controller;


import app.model.Point;
import app.model.User;
import app.request.PointAddRequest;
import app.request.PointUpdateRequest;
import app.request.UserAddRequest;
import app.response.BaseResponse;
import app.service.impl.PointServiceImpl;
import app.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Set;

@RestController
@RequestMapping("/")
@EnableWebSecurity
public class ApplicationController{

    private final PointServiceImpl pointService;
    private final UserServiceImpl userService;

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
        //todo dot's name
        Point point = pointAddRequest.createPoint(user.getLogin()+"'s superdot", user);
        pointService.addPoint(point);
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



    @PostMapping("/ok")
    public BaseResponse ok(){
        return new BaseResponse(200,"ok");
    }
    @PostMapping("/err")
    public BaseResponse err(){
        return new BaseResponse(400,"error");
    }




}