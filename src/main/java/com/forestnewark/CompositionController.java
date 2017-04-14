package com.forestnewark;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;

/**
 * Created by forestnewark on 4/13/17.
 */
@Controller
public class CompositionController {

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


    @Autowired
    CompositionRepository repo;

    @RequestMapping("/")
    public String showCompositions(Model model, String search){
        model.addAttribute("compositions",repo.listCompositions(search));

        return "index";
    }

    @RequestMapping("/musicForm")
    public String musicForm(){
        return "musicForm";
    }

    @RequestMapping("/addCSV")
    public String addCSV() {
        return "addCSV";
    }


    /**
     * Upload single file using Spring Controller
     */
    @RequestMapping(value = "/submitCSV", method = RequestMethod.POST)
    public @ResponseBody
    RedirectView uploadFileHandler(@RequestParam("file") MultipartFile file) throws IOException {


        Reader in = new FileReader(CompositionController.convert(file));

        Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Catagory", "libnum", "Title","Composer","Arranger","Copyright","Ensemble","Notes").parse(in);
        for (CSVRecord record : records) {
            Composition composition = new Composition(
                    null,
                    record.get("Catagory"),
                    record.get("libnum"),
                    record.get("Title"),
                    record.get("Composer"),
                    record.get("Arranger"),
                    record.get("Copyright"),
                    record.get("Ensemble"),
                    record.get("Notes")
            );
            repo.addComposition(composition);
        }

       return new RedirectView("/");
    }
}
