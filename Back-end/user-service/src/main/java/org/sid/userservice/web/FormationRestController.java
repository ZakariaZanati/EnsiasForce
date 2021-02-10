package org.sid.userservice.web;

import lombok.AllArgsConstructor;
import org.sid.userservice.entity.Formation;
import org.sid.userservice.service.FormationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/formation")
public class FormationRestController {

    private final FormationService formationService;

    @GetMapping
    public List<Formation> getFormations(){
        return (List<Formation>) formationService.getCurrentUserFormations();
    }

    @PostMapping
    public void addFormation(@RequestBody Formation formation){
        formationService.saveUserFormation(formation);
    }

    @GetMapping("/{id}")
    public List<Formation> getUserFormations(@PathVariable Long id){
        return (List<Formation>) formationService.getUserFormations(id);
    }
}
