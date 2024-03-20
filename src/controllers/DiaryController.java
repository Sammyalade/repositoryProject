package controllers;

import datas.models.Entry;
import exceptions.DiaryAppException;
import services.DiaryService;
import services.DiaryServiceImpl;
import services.EntryService;
import services.EntryServiceImpl;
import services.dtos.EntryCreationRequest;
import services.dtos.EntryUpdateRequest;
import services.dtos.LoginRequest;
import services.dtos.RegisterRequest;

import java.util.List;

public class DiaryController {

    private DiaryService diaryService = new DiaryServiceImpl();
    private EntryService entryService = new EntryServiceImpl();

    public String registerUser(RegisterRequest registerRequest){
        try {
           diaryService.register(registerRequest);
           return "Registration Successful";
        } catch (DiaryAppException e){
           return e.getMessage();
        }
    }

    public String login(LoginRequest loginRequest){
        try {
            diaryService.login(loginRequest);
            return "Login Successful";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String logout(String username){
        try{
            diaryService.logout(username);
            return "Thank you for using our app";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public Entry createEntry(EntryCreationRequest entryCreationRequest){
        return diaryService.createEntry(entryCreationRequest);
    }

    public List<Entry> getAllEntriesBy(String username){
       return entryService.getAllEntries(username);
    }

    public void deleteEntry(int id){
        entryService.delete(id);
    }

    public Entry updateEntry(EntryUpdateRequest entryUpdateRequest){
        return entryService.update(entryUpdateRequest);
    }

    public void changePassword(LoginRequest loginRequest){
        diaryService.changePassword(loginRequest);
    }
}
