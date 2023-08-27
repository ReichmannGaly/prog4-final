package school.hei.prog4.controller;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import school.hei.prog4.entity.configuration.CompanyConf;
import school.hei.prog4.service.CompanyConfService;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class CompanyConfController {
    private final CompanyConfService companyConfService;

    @GetMapping("/updateCompanyConf")
    public String getCompanyInfos(Model model) {
        return companyConfService.getCompanyConfWithPhoneNumbers(model);
    }

    @PostMapping("/updateCompanyConf")
    public String updateCompanyInfos(
            @ModelAttribute("company") CompanyConf companyConf,
            @RequestParam(value = "newLogo", required = false) MultipartFile newLogo,
            @RequestParam("codePays") String codePays,
            @RequestParam("phoneNumber") String phoneNumber,
            Model model) throws IOException {
        CompanyConf conf = companyConfService.saveCompanyConf(companyConf, newLogo, codePays, phoneNumber);
        model.addAttribute("company", conf);
        model.addAttribute("logo", "data:image/png;base64," +
                Base64.encodeBase64String(conf.getLogo()));

        return "redirect:/";
    }
}
