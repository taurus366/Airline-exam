package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.plane.PlaneImportXMLDto;
import softuni.exam.models.dto.xml.plane.PlaneRootImportDto;
import softuni.exam.models.entity.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PlaneServiceImpl implements PlaneService {

    private static final String PLANE_PATH = "src/main/resources/files/xml/planes.xml";

    @Autowired
    private final PlaneRepository planeRepository;

    @Autowired
    private final XmlParser xmlParser;

    @Autowired
    private final ValidationUtil validationUtil;

    @Autowired
    private final ModelMapper modelMapper;

    public PlaneServiceImpl(PlaneRepository planeRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.planeRepository = planeRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PLANE_PATH)));
    }

    @Override
    public String importPlanes() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        PlaneRootImportDto planeRootImportDto = xmlParser.parseXml(PlaneRootImportDto.class, PLANE_PATH);

        for (PlaneImportXMLDto planeImportXMLDto : planeRootImportDto.getImportXMLDtos()) {

            Plane byRegisterNumber = planeRepository.findByRegisterNumber(planeImportXMLDto.getRegisterNumber());

            if (validationUtil.isValid(planeImportXMLDto) && byRegisterNumber == null){
                Plane plane = modelMapper.map(planeImportXMLDto, Plane.class);

                planeRepository.saveAndFlush(plane);

                sb.append(String.format("Successfully imported Plane %s", plane.getRegisterNumber()))
                        .append(System.lineSeparator());

            }else {
            sb.append("Invalid Plane").append(System.lineSeparator());

            }

        }



        return sb.toString();
    }
}
