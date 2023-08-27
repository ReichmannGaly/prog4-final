package school.hei.prog4.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import school.hei.prog4.service.PdfGenerationService;

@Controller
@AllArgsConstructor
public class PdfController {
    private PdfGenerationService pdfGenerationService;

    @GetMapping(value = "/employees/{id}/generate-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf(@PathVariable String id) throws Exception {
        byte[] pdfBytes = pdfGenerationService.generatePdf(id);
        return ResponseEntity.ok().body(pdfBytes);
    }
}
