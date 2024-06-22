package org.kptc.drive.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FileUpdateDto {

    private String name;
    private String suffix;
    private byte[] data;
    private UUID folderId;

}
