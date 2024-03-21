package services;

import datas.models.Diary;
import datas.repositories.DiaryNotFound;
import exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dtos.EntryCreationRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;

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

    @Test
    public void createTwoDiary_createOneEntryEachForTheDiary_findFirstDiaryEntryWithDiaryTwoUsername_throwsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);

        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setUsername("username1");
        registerRequest1.setPassword("password1");
        diaryService.register(registerRequest1);

        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setUsername("username");
        entryCreationRequest.setTitle("Title");
        entryCreationRequest.setBody("Body");

        EntryCreationRequest entryCreationRequest1 = new EntryCreationRequest();
        entryCreationRequest1.setUsername("username1");
        entryCreationRequest1.setTitle("Title1");
        entryCreationRequest1.setBody("Body1");

        diaryService.createEntry(entryCreationRequest);
        diaryService.createEntry(entryCreationRequest1);
        assertThrows(NoSuchEntryException.class, ()-> diaryService.checkEntryById("username", 2));
    }

    @Test
    public void createEntry_deleteEntryWithWrongUsername_throwsUserNotFoundExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryService.register(registerRequest);

        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setUsername("username");
        entryCreationRequest.setTitle("Title");
        entryCreationRequest.setBody("Body");

        diaryService.createEntry(entryCreationRequest);
        assertThrows(UserNotFoundException.class, ()-> diaryService.deleteEntry("u1sername", 1));
    }



}
