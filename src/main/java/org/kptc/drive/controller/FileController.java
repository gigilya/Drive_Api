package org.kptc.drive.controller;

import lombok.RequiredArgsConstructor;
import org.kptc.drive.dto.FileCreateDto;
import org.kptc.drive.dto.FileDataDto;
import org.kptc.drive.dto.FileDto;
import org.kptc.drive.dto.FileUpdateDto;
import org.kptc.drive.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("{fileId}")
    public ResponseEntity<FileDataDto> getById(@PathVariable UUID fileId) {
        try {
            return ResponseEntity.ok(fileService.getById(fileId));
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("folder/{folderId}")
    public ResponseEntity<Collection<FileDto>> getAll(@PathVariable UUID folderId) {
        try {
            return ResponseEntity.ok(fileService.getByFolder(folderId));
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<FileDto> create(@RequestBody FileCreateDto fileDto) {
        try {
            return ResponseEntity.ok(fileService.create(fileDto));
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping("{fileId}")
    public HttpStatus update(@PathVariable UUID fileId, @RequestBody FileUpdateDto fileDto) {
        try {
            fileService.update(fileId, fileDto);
            return HttpStatus.OK;
        } catch (IllegalStateException e) {
            return HttpStatus.NOT_MODIFIED;
        }
    }

    @DeleteMapping("{fileId}")
    public HttpStatus delete(@PathVariable UUID fileId) {
        try {
            fileService.delete(fileId);
            return HttpStatus.OK;
        } catch (IllegalStateException e) {
            return HttpStatus.NOT_MODIFIED;
        }
    }

}
