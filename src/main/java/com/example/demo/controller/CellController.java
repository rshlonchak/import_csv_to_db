package com.example.demo.controller;

import com.example.demo.service.CellService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/import")
public class CellController {

    private final CellService service;

    @PostMapping(value = "/import/csv", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void importCsv(@RequestParam("file") MultipartFile csvFile) throws Exception {
        service.importFile(csvFile);
    }
}
