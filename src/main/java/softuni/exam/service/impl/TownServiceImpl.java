package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.TownImportDto;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWN_PATH = "src/main/resources/files/json/towns.json";

    @Autowired
    private final TownRepository townRepository;

    @Autowired
    private final Gson gson;

    @Autowired
    private final ValidationUtil validationUtil;

    @Autowired
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(TOWN_PATH)));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();

        TownImportDto[] townImportDtos = gson.fromJson(readTownsFileContent(), TownImportDto[].class);

        for (TownImportDto townImportDto : townImportDtos) {

            Town town = townRepository.findByName(townImportDto.getName());

            if (validationUtil.isValid(townImportDto) && town == null){

                townRepository.saveAndFlush(modelMapper.map(townImportDto, Town.class));

                sb.append(String.format("Successfully imported Town %s - %d", townImportDto.getName(),townImportDto.getPopulation()))
                .append(System.lineSeparator());
            }else {
                sb.append("Invalid Town").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
