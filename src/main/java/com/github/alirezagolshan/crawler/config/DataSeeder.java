package com.github.alirezagolshan.crawler.config;

import com.github.alirezagolshan.crawler.model.AirportCode;
import com.github.alirezagolshan.crawler.model.User;
import com.github.alirezagolshan.crawler.service.AirportCodeService;
import com.github.alirezagolshan.crawler.service.auth.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.github.alirezagolshan.crawler.util.Constants.*;

@Component
public class DataSeeder {

  private final List<String> airportCodeList =
      List.of(
          "IKA", "MHD", "SYZ", "TBZ", "IFN", "GSM", "AWZ", "KIH", "THR", "BND", "KSH", "RAS", "AZD",
          "ABD", "SRY", "LRR", "OMH", "ZAH", "BUZ", "KER", "ADU", "ZBR", "BDH", "DEF", "MRX", "PGU",
          "KHD", "SDG", "BXR", "XBJ", "IST", "DXB", "ANK", "LON");

  @Bean
  public ApplicationRunner seeder(
      AirportCodeService airportCodeService,
      UserService userService,
      PasswordEncoder passwordEncoder,
      Logger logger) {
    saveAirportCodes(airportCodeService);
    saveSuperUser(userService, passwordEncoder);
    return args -> logger.log(Level.INFO, SEEDER_MESSAGE);
  }

  private void saveSuperUser(UserService userService, PasswordEncoder passwordEncoder) {
    if (userService.findAll().isEmpty()) {
      var user = new User();
      user.setUsername(SUPERUSER_USERNAME);
      user.setPassword(passwordEncoder.encode(SUPERUSER_PASSWORD));
      user.setRoles(Set.of(SUPERUSER_ROLE));
      userService.save(user);
    }
  }

  private void saveAirportCodes(AirportCodeService airportCodeService) {
    if (airportCodeService.getAll().isEmpty())
      airportCodeList.forEach(code -> airportCodeService.save(new AirportCode(code)));
  }
}
