package services;

import datas.models.Diary;
import datas.repositories.DiaryNotFound;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dtos.EntryCreationRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;
import exceptions.EmptyStringException;
import exceptions.IncorrectPasswordException;
import exceptions.IncorrectUsernameException;
import exceptions.UsernameTakenException;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServiceTest {

    private DiaryService diaryService;

    @BeforeEach
    public void initializeDiaryService() {
        diaryService = new DiaryServiceImpl();
    }

    @AfterEach
    public void collapse(){
        diaryService.removeAllDiaries();
    }

    @Test
    public void testThatUserCanRegisterCustomer(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        assertEquals(new Diary("username", "password"), diaryService.register(registerRequest));
    }

    @Test
    public void registerUser_loginWithUserName_isLockedIsFalseTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        Diary newDiary = diaryService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        diaryService.login(loginRequest);
        assertFalse(newDiary.isLocked());
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

    @Test
    public void createDiary_loginAndLogoutFromDiary_diaryIsLockedTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        Diary newDiary = diaryService.register(registerRequest);
        diaryService.logout("username");
        assertTrue(newDiary.isLocked());
    }

    @Test
    public void createDiary_createEntryWithWrongUsername_throwsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setUsername("use1rname");
        entryCreationRequest.setTitle("Title");
        entryCreationRequest.setBody("Body");
        assertThrows(DiaryNotFound.class, ()->diaryService.createEntry(entryCreationRequest));
    }



}
