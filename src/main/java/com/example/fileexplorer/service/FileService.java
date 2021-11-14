package com.example.fileexplorer.service;

import com.example.fileexplorer.model.FileType;
import com.example.fileexplorer.model.FileViewModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
public class FileService {
    private final String MODEL_PATH = "src/main/java/com/example/fileexplorer/model";
    private final String REST_PATH = "src/main/java/com/example/fileexplorer/rest";
    private final String SERVICE_PATH = "src/main/java/com/example/fileexplorer/service";

    private final List<String> paths = List.of(MODEL_PATH, REST_PATH, SERVICE_PATH);

    private List<FileViewModel> files = new ArrayList<>();


    public Flux<FileViewModel> getAll() {
        return Flux.fromIterable(files);
    }

    public void readFiles() {
        paths.parallelStream()
                .forEach(this::readFilesFromPath);
    }

    private void readFilesFromPath(String location) {
        try (Stream<Path> paths = Files.walk(Paths.get(location))) {
            paths.map(path -> FileViewModel.builder()
                            .name(path.toFile().getName())
                            .type(path.toFile().isDirectory() ? FileType.FOLDER : FileType.FILE)
                            .build())
                    .forEach(files::add);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void clearFiles() {
        this.files = new ArrayList<>();
    }
}
