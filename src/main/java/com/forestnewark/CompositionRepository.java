package com.forestnewark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by forestnewark on 4/13/17.
 */
@Component
public class CompositionRepository {

    @Autowired
    JdbcTemplate template;


    public List<Composition> listCompositions(String search){
        if(search == null || search.isEmpty() ){
            return template.query("Select * FROM composition",
                    (resultSet, i) -> new Composition(
                            resultSet.getInt("compositionid"),
                            resultSet.getString("catagory"),
                            resultSet.getString("libnum"),
                            resultSet.getString("title"),
                            resultSet.getString("composer"),
                            resultSet.getString("arranger"),
                            resultSet.getString("copyright"),
                            resultSet.getString("ensemble"),
                            resultSet.getString("notes")
                    ));


        }else {
            return template.query("Select * FROM composition WHERE lower(catagory) LIKE ? OR lower(title) LIKE ? OR lower(composer) LIKE ? OR lower(arranger) LIKE ? or lower(ensemble) LIKE ?",
                    new Object[]{"%"+search.toLowerCase()+"%","%"+search.toLowerCase()+"%","%"+search.toLowerCase()+"%","%"+search.toLowerCase()+"%","%"+search.toLowerCase()+"%"},
                    (resultSet, i) -> new Composition(
                            resultSet.getInt("compositionid"),
                            resultSet.getString("catagory"),
                            resultSet.getString("libnum"),
                            resultSet.getString("title"),
                            resultSet.getString("composer"),
                            resultSet.getString("arranger"),
                            resultSet.getString("copyright"),
                            resultSet.getString("ensemble"),
                            resultSet.getString("notes")
                    ));
        }

    }

    public void addComposition(Composition composition){

        template.update(
                "INSERT INTO composition(libnum, title, composer, arranger, copyright, ensemble, notes, catagory) VALUES (?,?,?,?,?,?,?,?)",
                composition.getLibraryNumber(),
                composition.getTitle(),
                composition.getComposer(),
                composition.getArranger(),
                composition.getCopyRight(),
                composition.getEnsemble(),
                composition.getNotes(),
                composition.getCatagory()
        );
    }

}
