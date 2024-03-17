package services;

import datas.models.Diary;
import services.dtos.LoginRequest;
import services.dtos.RegisterRequest;

public interface DiaryService {
    Diary register(RegisterRequest registerRequest);
    boolean login(LoginRequest loginRequest);
}
