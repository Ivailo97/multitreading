package com.example.fileexplorer.rest;

import com.example.fileexplorer.model.FileViewModel;
import com.example.fileexplorer.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping
    public Flux<FileViewModel> ls() {
        return fileService.getAll();
    }

    @GetMapping("/read")
    public ResponseEntity<Void> readFiles() {
        fileService.readFiles();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/clear")
    public ResponseEntity<Void> clearFiles() {
        fileService.clearFiles();
        return ResponseEntity.ok().build();
    }
}
