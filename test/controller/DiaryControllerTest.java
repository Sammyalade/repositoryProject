package controller;

import controllers.DiaryController;
import exceptions.NoSuchEntryException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.DiaryService;
import services.DiaryServiceImpl;
import services.EntryService;
import services.EntryServiceImpl;
import dtos.EntryCreationRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiaryControllerTest {

    private DiaryController diaryController;
    private DiaryService diaryService = new DiaryServiceImpl();;
    private EntryService entryService = new EntryServiceImpl();
    @BeforeEach
    public void setUp(){
        diaryController = new DiaryController();
    }
    @AfterEach
    public void tearDown(){
        diaryService.removeAllDiaries();
        entryService.removeAllEntries();

    }


    @Test
    public void RegisterUser_userIsRegisteredTest(){
          RegisterRequest registerRequest = new RegisterRequest();
          registerRequest.setUsername("username");
          registerRequest.setPassword("password");
          assertEquals("Registration Successful", diaryController.registerUser(registerRequest));
      }


    @Test
    public void testThatUserRegistersTwiceWithTheSameUsername_errorMessageDisplayed(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryController.registerUser(registerRequest);
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setUsername("username");
        registerRequest1.setPassword("password");
        assertEquals("Username already taken",diaryController.registerUser(registerRequest1));
    }

    @Test
    public void userLogin_loginSuccessfulTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryController.registerUser(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");
        assertEquals("Login Successful", diaryController.login(loginRequest));
    }

    @Test
    public void userLoginWithIncorrectUsername_throwsExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryController.registerUser(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("u1sername");
        loginRequest.setPassword("password");
        assertEquals("Username Is Incorrect", diaryController.login(loginRequest));
    }

    @Test
    public void userLoginWithIncorrectPassword_throwsExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryController.registerUser(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("p1assword");
        assertEquals("Password is Incorrect", diaryController.login(loginRequest));
    }

    @Test
    public void registerUsernameWithEmptyString_throwsExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("");
        registerRequest.setPassword("password");
        assertEquals("Username or Password cannot be empty",diaryController.registerUser(registerRequest));
    }

    @Test
    public void registerPasswordWithEmptyString_throwsExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Username");
        registerRequest.setPassword("");
        assertEquals("Username or Password cannot be empty",diaryController.registerUser(registerRequest));
    }

    @Test
    public void createDiary_logoutUserTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryController.registerUser(registerRequest);
        assertEquals("Thank you for using our app",diaryController.logout("username"));
    }

    @Test
    public void createDiary_logoutUserWithIncorrectUsernameTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryController.registerUser(registerRequest);
        assertEquals("Diary not found",diaryController.logout("u1sername"));
    }

    @Test
    public void createEntryInDiary_entryIsCreatedTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryController.registerUser(registerRequest);
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setUsername("username");
        entryCreationRequest.setTitle("Title");
        entryCreationRequest.setBody("Body");
        assertEquals("Entry{id=1, title='Title', body='Body', author='username', dateCreated=2024-03-21}",diaryController.createEntry(entryCreationRequest));
    }

    @Test
    public void createDiary_createEntryWithWrongUsername_throwsException(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryController.registerUser(registerRequest);
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setUsername("use1rname");
        entryCreationRequest.setTitle("Title");
        entryCreationRequest.setBody("Body");
        assertEquals("Diary to save Entry in not found",diaryController.createEntry(entryCreationRequest));
    }

    @Test
    public void registerTwoEntry_getAListOfEntry_returnsListOfEntryTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryController.registerUser(registerRequest);
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setUsername("username");
        entryCreationRequest.setTitle("Title");
        entryCreationRequest.setBody("Body");
        diaryController.createEntry(entryCreationRequest);
        EntryCreationRequest entryCreationRequest1 = new EntryCreationRequest();
        entryCreationRequest1.setUsername("username");
        entryCreationRequest1.setTitle("Title is not null");
        entryCreationRequest1.setBody("Body of the entry");
        diaryController.createEntry(entryCreationRequest1);
        assertEquals("[Entry{id=1, title='Title', body='Body', author='username', dateCreated=2024-03-21}, Entry{id=2, title='Title is not null', body='Body of the entry', author='username', dateCreated=2024-03-21}]",diaryController.getAllEntriesBy("username"));
    }

    @Test
    public void createEntry_deleteEntry_entryIsDeletedTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryController.registerUser(registerRequest);
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setUsername("username");
        entryCreationRequest.setTitle("Title");
        entryCreationRequest.setBody("Body");
        diaryController.createEntry(entryCreationRequest);
        assertEquals("Entry successfully deleted", diaryController.deleteEntry(1, "username"));
    }

    @Test
    public void createEntry_deleteEntryWithWrongId_throwsExceptionTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setPassword("password");
        diaryController.registerUser(registerRequest);
        EntryCreationRequest entryCreationRequest = new EntryCreationRequest();
        entryCreationRequest.setUsername("1username");
        entryCreationRequest.setTitle("Title");
        entryCreationRequest.setBody("Body");
        diaryController.createEntry(entryCreationRequest);
        assertThrows(NoSuchEntryException.class,()-> diaryController.deleteEntry(1, "usernsme"));

    }
}
