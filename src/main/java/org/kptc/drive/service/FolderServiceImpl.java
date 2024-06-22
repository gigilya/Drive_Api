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
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    @Override
    public Collection<FolderDto> getAllByUser(String username) {
        return folderRepository
                .findAll()
                .stream()
                .filter(f -> f.getUser().getUsername().equals(username))
                .map(e -> modelMapper.map(e, FolderDto.class))
                .toList();
    }

    @Override
    public FolderDto create(FolderCreateDto folderDto) {
        if (folderDto == null)
            throw new IllegalStateException("No data!");

        Folder toCreate = new Folder();

        User user = userRepository.findByUsername(folderDto.getUsername());

        if (user == null)
            throw new IllegalStateException("User can`t be null!");

        toCreate.setName(folderDto.getName());
        toCreate.setUser(user);

        folderRepository.save(toCreate);

        return modelMapper.map(toCreate, FolderDto.class);
    }

    @Override
    public void update(UUID id, FolderUpdateDto folderDto) {
        if (id == null || folderDto == null)
            throw new IllegalStateException("No data!");

        Folder toUpdate = folderRepository.findById(id).get();

        if (toUpdate == null)
            throw new IllegalStateException("Not found!");

        if (folderDto.getName() != null)
            toUpdate.setName(folderDto.getName());

        folderRepository.save(toUpdate);
    }

    @Override
    public void delete(UUID id) {
        fileRepository.findAll()
                .stream()
                .filter(f -> f.getFolder().getId().equals(id)).forEach(fileRepository::delete);
        folderRepository.deleteById(id);
    }

}
