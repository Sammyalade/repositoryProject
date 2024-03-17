package services;

import datas.models.Entry;
import services.dtos.EntryAccessRequest;
import services.dtos.EntryCreationRequest;

import javax.xml.crypto.Data;

public interface EntryService {
    
    Entry create(EntryCreationRequest entryCreationRequest);
    
    void viewEntry(EntryAccessRequest entryAccessRequest);
        


}
