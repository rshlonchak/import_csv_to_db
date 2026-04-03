package com.example.demo.service;

import com.example.demo.dto.OpenCellIdDto;
import com.example.demo.model.OpenCellId;
import com.example.demo.repository.CellRepository;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.util.*;

@Service
public class CellService {

    private final CellRepository repository;

    private static final int BATCH_SIZE = 1000;

    public CellService(CellRepository repository) {
        this.repository = repository;
    }

    private OpenCellIdDto parse(String[] row) {
        OpenCellIdDto dto = new OpenCellIdDto();

        dto.setRadio(row[0]);
        dto.setMcc(Integer.parseInt(row[1]));
        dto.setMnc(Integer.parseInt(row[2]));
        dto.setLac(Integer.parseInt(row[3]));
        dto.setCid(Long.parseLong(row[4]));
        dto.setUnit(Integer.parseInt(row[5]));
        dto.setLon(Double.parseDouble(row[6]));
        dto.setLat(Double.parseDouble(row[7]));
        dto.setRange(Integer.parseInt(row[8]));
        dto.setSamples(Integer.parseInt(row[9]));
        dto.setChangeable(Integer.parseInt(row[10]));
        dto.setCreated(Long.parseLong(row[11]));
        dto.setUpdated(Long.parseLong(row[12]));
        dto.setAverageSignal(Integer.parseInt(row[13]));

        return dto;
    }

    @Transactional
    public void importFile(String path) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(path));

        List<OpenCellId> batch = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        String[] line;

        reader.readNext();

        while ((line = reader.readNext()) != null) {
            try {
                if (line.length < 14) {
                    continue;
                }

                OpenCellIdDto dto = parse(line);

                String key = dto.getMcc() + "-"
                        + dto.getMnc() + "-"
                        + dto.getLac() + "-"
                        + dto.getCid();

                if (!seen.add(key)) {
                    continue;
                }

                OpenCellId entity = new OpenCellId();

                entity.setRadio(dto.getRadio());
                entity.setMcc(dto.getMcc());
                entity.setMnc(dto.getMnc());
                entity.setLac(dto.getLac());
                entity.setCid(dto.getCid());
                entity.setUnit(dto.getUnit());
                entity.setLon(dto.getLon());
                entity.setLat(dto.getLat());
                entity.setRange(dto.getRange());
                entity.setSamples(dto.getSamples());
                entity.setChangeable(dto.getChangeable());
                entity.setCreated((int) dto.getCreated());
                entity.setUpdated((int) dto.getUpdated());
                entity.setAverageSignal(dto.getAverageSignal());

                batch.add(entity);

                if (batch.size() >= BATCH_SIZE) {
                    repository.saveAll(batch);
                    batch.clear();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!batch.isEmpty()) {
            repository.saveAll(batch);
        }
    }
}
