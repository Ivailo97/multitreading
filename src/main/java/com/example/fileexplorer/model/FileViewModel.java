package com.example.fileexplorer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileViewModel {

    private String name;

    private FileType type;
}
