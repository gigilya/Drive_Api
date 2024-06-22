package org.kptc.drive.service;

import org.kptc.drive.dto.*;

import java.util.Collection;
import java.util.UUID;

public interface FolderService {

    Collection<FolderDto> getAllByUser(String username);
    FolderDto create(FolderCreateDto folderDto);
    void update(UUID id, FolderUpdateDto folderDto);
    void delete(UUID id);

}
