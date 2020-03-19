package io.javabrains.coronavirustracker.controllers;

import io.javabrains.coronavirustracker.models.LocationStats;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")// localhost:8080 -> o diretorio raíz (root)
    public String home(Model model) {
        List<LocationStats> allStatsList = coronaVirusDataService.getAllStatsList();

        int totalReportedCases = allStatsList.stream().mapToInt( stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStatsList.stream().mapToInt( stat -> stat.getDiffFromPrevDay()).sum();

        model.addAttribute("locationStats", allStatsList );
        model.addAttribute("totalReportedCases", totalReportedCases );
        model.addAttribute("totalNewCases", totalNewCases );

        return "home.html";
    }


}
