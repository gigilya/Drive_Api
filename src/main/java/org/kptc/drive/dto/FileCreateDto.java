package org.kptc.drive.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FileCreateDto {

    private String name;
    private String suffix;
    private byte[] data;
    private UUID folderId;

}
