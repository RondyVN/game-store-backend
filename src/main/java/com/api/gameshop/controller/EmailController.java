package com.api.gameshop.controller;
import com.api.gameshop.DTO.GameDTO;
import com.api.gameshop.DTO.UserDTO;
import com.api.gameshop.entity.GameEntity;
import com.api.gameshop.exception.NoSuchElementFoundException;
import com.api.gameshop.repository.GameRepo;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
@Service
public class EmailController {
    @Autowired
    private final JavaMailSender javaMailSender;
    @Autowired
    private GameRepo gameRepo;
    @PostMapping
    public void sendEmail(@RequestBody UserDTO userDTO) throws MessagingException {
        var message = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message);
        helper.setFrom("rondelman15@gmail.com");
        helper.setTo(userDTO.getUserEmail());
        helper.setSubject("Elixir Steam Store");
        String htmlTable = "<table style='border: 1px solid black; border-collapse: collapse;'>";
        List<Map<String, Long>> games = userDTO.getGames();
        for (Map<String, Long> game: games) {
            GameEntity gameEntity = gameRepo.findById(game.get("gameId"))
                .orElseThrow(() -> new NoSuchElementFoundException("Not found Game with id = " + game.get("gameId")));
            for (int i = 0; i < game.get("count"); i++) {
                htmlTable += "<tr style='border: 1px solid black; border-collapse: collapse;'>";
                htmlTable += "<td style='border: 1px solid black; border-collapse: collapse;'>";
                htmlTable += gameEntity.getName();
                htmlTable += "</td>";
                htmlTable += "<td style='border: 1px solid black; border-collapse: collapse;'>";
                htmlTable += UUID.randomUUID().toString();
                htmlTable += "</td>";
                htmlTable += "</tr>";
            }
        }
        htmlTable += "</table>";
        helper.setText("<h1>Thank you for shopping in our store</h1> <p>" + htmlTable, true);
        javaMailSender.send(message);
    }
}
