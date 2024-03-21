package services;

import datas.models.Diary;
import datas.models.Entry;
import dtos.EntryCreationRequest;
import dtos.EntryUpdateRequest;
import dtos.LoginRequest;
import dtos.RegisterRequest;

public interface DiaryService {
    Diary register(RegisterRequest registerRequest);
    void login(LoginRequest loginRequest);
    void logout(String username);
    Entry createEntry(EntryCreationRequest entryCreationRequest);
    void updateEntry(EntryUpdateRequest entryUpdateRequest);
    void getAllEntries(String username);
    void changePassword(LoginRequest loginRequest);
    void removeAllDiaries();
    Entry checkEntryById(String username, long id);
    void deleteEntry(String username, long id);
}
