package org.kptc.drive.service;

import lombok.RequiredArgsConstructor;
import org.kptc.drive.dto.*;
import org.kptc.drive.entity.File;
import org.kptc.drive.entity.Folder;
import org.kptc.drive.entity.User;
import org.kptc.drive.repository.FileRepository;
import org.kptc.drive.repository.FolderRepository;
import org.kptc.drive.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FolderRepository folderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public FileDataDto getById(UUID fileId) {
        if (fileId == null)
            throw new IllegalStateException("No data!");

        File file = fileRepository.findById(fileId).get();

        if (file == null)
            throw new IllegalStateException("Not found!");

        return modelMapper.map(file, FileDataDto.class);
    }

    @Override
    public Collection<FileDto> getByFolder(UUID folderId) {
        if (folderId == null)
            throw new IllegalStateException("Folder`s id can`t be null!");

        Folder folder = folderRepository.findById(folderId).get();

        if (folder == null)
            throw new IllegalStateException("Folder not found!");

        return fileRepository
                .findAll()
                .stream()
                .filter(f -> f.getFolder().getId().equals(folder.getId()))
                .map(e -> modelMapper.map(e, FileDto.class))
                .toList();
    }

    @Override
    public FileDto create(FileCreateDto fileDto) {
        if (fileDto == null)
            throw new IllegalStateException("File can`t be null!");

        Folder folder = folderRepository.findById(fileDto.getFolderId()).get();

        if (folder == null)
            throw new IllegalStateException("Folder can`t be null!");

        File toCreate = new File();

        toCreate.setName(fileDto.getName());
        toCreate.setSuffix(fileDto.getSuffix());
        toCreate.setData(fileDto.getData());
        toCreate.setFolder(folder);

        fileRepository.save(toCreate);

        return modelMapper.map(toCreate, FileDto.class);
    }

    @Override
    public void update(UUID id, FileUpdateDto fileDto) {
        if (fileDto == null || id == null)
            throw new IllegalStateException("No data!");

        File file = fileRepository.findById(id).get();

        if (file == null)
            throw new IllegalStateException("File can`t be null!");

        if (fileDto.getName() != null)
            file.setName(fileDto.getName());

        if (fileDto.getSuffix() != null)
            file.setSuffix(fileDto.getSuffix());

        if (fileDto.getData() != null)
            file.setData(fileDto.getData());

        if (fileDto.getFolderId() != null) {
            Folder folder = folderRepository.findById(fileDto.getFolderId()).get();

            if (folder != null)
                file.setFolder(folder);
        }

        fileRepository.save(file);
    }

    @Override
    public void delete(UUID id) {
        if (id == null)
            throw new IllegalStateException("Id can`t be null!");

        File toDelete = fileRepository.findById(id).get();

        if (toDelete == null)
            throw new IllegalStateException("File not found!");

        fileRepository.delete(toDelete);
    }

}
