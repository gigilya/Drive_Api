package org.kptc.drive.service;

import org.kptc.drive.dto.FileCreateDto;
import org.kptc.drive.dto.FileDataDto;
import org.kptc.drive.dto.FileDto;
import org.kptc.drive.dto.FileUpdateDto;

import java.util.Collection;
import java.util.UUID;

public interface FileService {

    FileDataDto getById(UUID fileId);
    Collection<FileDto> getByFolder(UUID folderId);
    FileDto create(FileCreateDto userDto);
    void update(UUID id, FileUpdateDto userDto);
    void delete(UUID id);

}
