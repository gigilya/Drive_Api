package org.kptc.drive.dto;

import lombok.Data;

@Data
public class FileDataDto {

    private String name;
    private String suffix;
    private byte[] data;

}
