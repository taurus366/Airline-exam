package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.PassengerImportDto;
import softuni.exam.models.entity.Passenger;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    private static final String PASSENGER_PATH = "src/main/resources/files/json/passengers.json";

    @Autowired
    private final PassengerRepository passengerRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final Gson gson;

    @Autowired
    private final ValidationUtil validationUtil;

    @Autowired
    private final TownRepository townRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, TownRepository townRepository) {
        this.passengerRepository = passengerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PASSENGER_PATH)));
    }

    @Override
    public String importPassengers() throws IOException {
        StringBuilder sb = new StringBuilder();

        PassengerImportDto[] passengerImportDtos = gson.fromJson(readPassengersFileContent(), PassengerImportDto[].class);

        for (PassengerImportDto passengerImportDto : passengerImportDtos) {

            Passenger byEmail = passengerRepository.getByEmail(passengerImportDto.getEmail());

            Town town = townRepository.findByName(passengerImportDto.getTown());


            if (validationUtil.isValid(passengerImportDto) && byEmail == null && town != null){

            Passenger passenger = modelMapper.map(passengerImportDto, Passenger.class);
            passenger.setTown(town);

            passengerRepository.saveAndFlush(passenger);

            sb.append(String.format("Successfully imported Passenger %s - %s", passenger.getLastName(),passenger.getEmail()))
            .append(System.lineSeparator());


            }else {
            sb.append("Invalid Passenger").append(System.lineSeparator());
            }

        }


        return sb.toString();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        StringBuilder sb = new StringBuilder();

        List<Passenger> passengers = passengerRepository.orderThemByTicketDescThenByEmail();
        passengers.forEach(p ->{
            sb.append(String.format("Passenger %s %s", p.getFirstName(),p.getLastName())).append(System.lineSeparator());
            sb.append(String.format("\tEmail - %s", p.getEmail())).append(System.lineSeparator());
            sb.append(String.format("\tPhone - %s", p.getPhoneNumber())).append(System.lineSeparator());
            sb.append(String.format("\tNumber of tickets - %d", p.getPassengers().size())).append(System.lineSeparator());

        });

        return sb.toString();
    }
}
