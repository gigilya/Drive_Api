package org.kptc.drive.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FileDto {

    private UUID id;
    private String name;
    private String suffix;

}
