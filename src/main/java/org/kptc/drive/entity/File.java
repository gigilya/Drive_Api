package org.kptc.drive.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String suffix;

    private byte[] data;

    @ManyToOne(optional = false)
    @JoinColumn(name = "folder_id", nullable = false)
    private Folder folder;

}
