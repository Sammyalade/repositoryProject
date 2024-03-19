package controllers;

import datas.models.Entry;
import services.DiaryService;
import services.DiaryServiceImpl;
import services.EntryService;
import services.EntryServiceImpl;
import services.dtos.EntryCreationRequest;
import services.dtos.LoginRequest;
import services.dtos.RegisterRequest;

import java.util.List;

public class DiaryController {

    private DiaryService diaryService = new DiaryServiceImpl();
    private EntryService entryService = new EntryServiceImpl();

    public void registerUser(RegisterRequest registerRequest){
        diaryService.register(registerRequest);
    }

    public void login(LoginRequest loginRequest){
        diaryService.login(loginRequest);
    }

    public void logout(String username){
        diaryService.logout(username);
    }

    public void createEntry(EntryCreationRequest entryCreationRequest){
        diaryService.createEntry(entryCreationRequest);
    }

    public List<Entry> getAllEntriesBy(String username){
       return entryService.getAllEntries(username);
    }

    public void deleteEntry(int id){
        entryService.delete(id);
    }

    public void
}
