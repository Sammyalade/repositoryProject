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

    public void registerUser(RegisterRequest registerRequest){
        try {
            diaryService.register(registerRequest);
        } catch (DiaryAppException e){
            e.getMessage();
        }
    }

    public void login(LoginRequest loginRequest){
        diaryService.login(loginRequest);
    }

    public void logout(String username){
        diaryService.logout(username);
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
