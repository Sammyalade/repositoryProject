package controllers;

import datas.models.Entry;
import services.DiaryService;
import services.DiaryServiceImpl;
import services.EntryService;
import services.EntryServiceImpl;
import services.dtos.LoginRequest;
import services.dtos.RegisterRequest;

public class DiaryController {

    private DiaryService diaryService = new DiaryServiceImpl();
    private EntryService entryService = new EntryServiceImpl();

    public void registerUser(RegisterRequest registerRequest){
        diaryService.register(registerRequest);
    }

    public void login(LoginRequest loginRequest){
        diaryService.login(loginRequest);
    }

    public void
}
