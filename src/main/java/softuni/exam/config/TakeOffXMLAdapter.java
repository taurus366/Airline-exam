package softuni.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class TakeOffXMLAdapter extends XmlAdapter<String,LocalDateTime> {


//    @Bean
//    public LocalDateTime unmarshal(String s){
//        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }
//
//    @Bean
//    public String marshal(LocalDateTime localDateTime){
//        return localDateTime.toString();
//    }

    @Override
    public LocalDateTime unmarshal(String s) throws Exception {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        return localDateTime.toString();
    }
}
