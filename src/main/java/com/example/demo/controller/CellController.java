package com.example.demo.controller;

import com.example.demo.service.CellService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/import")
public class CellController {

    private final CellService service;

    @PostMapping
    public String importCsv(@RequestParam String path) throws Exception {
        service.importFile(path);
        return "Import started";
    }
}
