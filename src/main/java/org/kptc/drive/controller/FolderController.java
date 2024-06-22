package org.kptc.drive.controller;

import lombok.RequiredArgsConstructor;
import org.kptc.drive.dto.*;
import org.kptc.drive.service.FolderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("folder")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @GetMapping("user/{username}")
    public ResponseEntity<Collection<FolderDto>> getAllByUser(@PathVariable String username) {
        try {
            return ResponseEntity.ok(folderService.getAllByUser(username));
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<FolderDto> create(@RequestBody FolderCreateDto folderDto) {
        try {
            return ResponseEntity.ok(folderService.create(folderDto));
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping("{folderId}")
    public HttpStatus update(@PathVariable UUID folderId, @RequestBody FolderUpdateDto folderDto) {
        try {
            folderService.update(folderId, folderDto);
            return HttpStatus.OK;
        } catch (IllegalStateException e) {
            return HttpStatus.NOT_MODIFIED;
        }
    }

    @DeleteMapping("{folderId}")
    public HttpStatus delete(UUID folderId) {
        try {
            folderService.delete(folderId);
            return HttpStatus.OK;
        } catch (IllegalStateException e) {
            return HttpStatus.NOT_FOUND;
        }
    }

}
