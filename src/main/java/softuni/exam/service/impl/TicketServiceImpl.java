package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.ticket.TicketImportXMLDto;
import softuni.exam.models.dto.xml.ticket.TicketRootImportDto;
import softuni.exam.models.entity.Passenger;
import softuni.exam.models.entity.Plane;
import softuni.exam.models.entity.Ticket;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TicketServiceImpl implements TicketService {
        private static final String TICKET_PATH = "src/main/resources/files/xml/tickets.xml";

    @Autowired
    private final TicketRepository ticketRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final XmlParser xmlParser;

    @Autowired
    private final ValidationUtil validationUtil;

    @Autowired
    private final TownRepository townRepository;

    @Autowired
    private final PassengerRepository passengerRepository;

    @Autowired
    private final PlaneRepository planeRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, TownRepository townRepository, PassengerRepository passengerRepository, PlaneRepository planeRepository) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.townRepository = townRepository;
        this.passengerRepository = passengerRepository;
        this.planeRepository = planeRepository;
    }


    @Override
    public boolean areImported() {
        return ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(TICKET_PATH)));
    }

    @Override
    public String importTickets() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        TicketRootImportDto ticketRootImportDto = xmlParser.parseXml(TicketRootImportDto.class, TICKET_PATH);

        for (TicketImportXMLDto ticketImportXMLDto : ticketRootImportDto.getTicketImportXMLDtos()) {

            Ticket bySerialNumber = ticketRepository.findBySerialNumber(ticketImportXMLDto.getSerialNumber());

            Town fromTown = townRepository.findByName(ticketImportXMLDto.getFromTownImportXMLDto().getName());
            Town toTown = townRepository.findByName(ticketImportXMLDto.getToTownImportXMLDto().getName());
            Passenger passenger = passengerRepository.getByEmail(ticketImportXMLDto.getPassengerEmail().getEmail());
            Plane plane = planeRepository.findByRegisterNumber(ticketImportXMLDto.getRegisterNumber().getRegisterNumber());
            System.out.println();
            if (validationUtil.isValid(ticketImportXMLDto)&& bySerialNumber == null && fromTown != null && toTown != null && passenger != null && plane != null){

//                TypeMap<Employee, ManagerDto> managerTypeMap = mapper.createTypeMap(Employee.class, ManagerDto.class)
//                        .addMappings(m -> {
//                            m.map(Employee::getSubordinates,ManagerDto::setEmployees);
//                            m.map(src -> src.getAddress().getCity(),ManagerDto::setCity);
//                            //  m.skip(ManagerDto::setCity);
//                        });

                Ticket ticket = modelMapper.map(ticketImportXMLDto, Ticket.class);
               // Ticket ticket = modelMapper.addMappings(ticketImportXMLDto::getPrice,)
                ticket.setFromTown(toTown);
                ticket.setToTown(fromTown);
                ticket.setPassenger(passenger);
                ticket.setPlane(plane);

                ticketRepository.saveAndFlush(ticket);

                sb.append(String.format("Successfully imported Ticket %s - %s", ticket.getToTown().getName(),ticket.getFromTown().getName()))
                        .append(System.lineSeparator());
            }else {
                sb.append("Invalid Ticket").append(System.lineSeparator());
            }

        }




        return sb.toString();
    }
}
