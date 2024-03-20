package controller;

import controllers.DiaryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.DiaryService;
import services.DiaryServiceImpl;
import services.dtos.RegisterRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiaryControllerTest {

    private DiaryController diaryController;
    @BeforeEach
    public void setUp(){
        diaryController = new DiaryController();
    }

    @Test
    public void RegisterUser_userIsRegisteredTest(){
          RegisterRequest registerRequest = new RegisterRequest();
          registerRequest.setUsername("username");
          registerRequest.setPassword("password");
          assertEquals("Registration Successful", diaryController.registerUser(registerRequest));
      }


    @Test
    public void

}
