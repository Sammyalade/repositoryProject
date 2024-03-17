package services;

import datas.models.Diary;
import datas.repositories.DiaryRepository;
import datas.repositories.DiaryRepositoryImpl;
import datas.repositories.EntryRepository;
import datas.repositories.EntryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.dtos.LoginRequest;
import services.dtos.RegisterRequest;
import services.exceptions.EmptyStringException;
import services.exceptions.IncorrectPasswordException;
import services.exceptions.IncorrectUsernameException;
import services.exceptions.UsernameTakenException;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServiceTest {

    private DiaryService diaryService;

    @BeforeEach
    public void initializeDiaryService() {
        DiaryRepository diaryRepository = new DiaryRepositoryImpl();
        EntryRepository entryRepository = new EntryRepositoryImpl();
        EntryService entryService = new EntryServiceImpl(entryRepository);
        diaryService = new DiaryServiceImpl(diaryRepository, entryService);
    }

    @Test
    public void testThatUserCanRegisterCustomer(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        assertEquals(new Diary("username", "password"), diaryService.register(registerRequest));
    }

    @Test
    public void registerUser_loginWithUserName_loginReturnsDiaryTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        assertTrue(diaryService.login(loginRequest));
    }

    @Test
    public void registerTwoTimesWithTheSameUsername_throwsUsernameIsTakenExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        assertThrows(UsernameTakenException.class, ()->diaryService.register(registerRequest));
    }

    @Test
    public void registerADiary_loginWithIncorrectUsername_throwsIncorrectUsernameExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("IncorrectUsername");
        loginRequest.setPassword("password");
        assertThrows(IncorrectUsernameException.class, ()->diaryService.login(loginRequest));
    }

    @Test
    public void registerADiary_loginWithIncorrectPassword_throwsIncorrectPasswordExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("IncorrectPassword");
        assertThrows(IncorrectPasswordException.class, ()->diaryService.login(loginRequest));
    }

    @Test
    public void registerWithEmptyStringAsUsername_throwsEmptyUsernameException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("");
        registerRequest.setPassword("password");
        assertThrows(EmptyStringException.class, ()->diaryService.register(registerRequest));
    }

    @Test
    public void registerWithEmptyStringAsPassword_throwsEmptyUsernameException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("");
        assertThrows(EmptyStringException.class, ()->diaryService.register(registerRequest));
    }

}
