package controller.registration;

import common.GetLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import request.RegistrationRequest;
import service.RegistrationService;

@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController implements GetLogger {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        getLogger().info("registering");
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        getLogger().info("confirming token {}", token);
        return registrationService.confirmToken(token);
    }
}
