package services;

import datas.models.Diary;
import datas.models.Entry;
import services.dtos.EntryCreationRequest;
import services.dtos.EntryUpdateRequest;
import services.dtos.LoginRequest;
import services.dtos.RegisterRequest;

public interface DiaryService {
    Diary register(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);
    void logout(String username);
    Entry createEntry(EntryCreationRequest entryCreationRequest);
    void updateEntry(EntryUpdateRequest entryUpdateRequest);
    void getAllEntries(String username);
    void changePassword(LoginRequest loginRequest);
}
