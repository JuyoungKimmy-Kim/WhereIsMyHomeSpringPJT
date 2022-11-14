package com.mycom.myapp.event.controller;

<<<<<<< HEAD
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.event.dto.EventDto;

@RestController
public class EventController {
=======
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/event/*")
public class EventController extends HttpServlet {
>>>>>>> 68f4e26ac52a98999605936fae1f9b15ace28032

	
}
