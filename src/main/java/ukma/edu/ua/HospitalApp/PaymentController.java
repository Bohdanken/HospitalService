package ukma.edu.ua.HospitalApp;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static Map<String, String> database = new HashMap<>();
    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 100;
    private static final int AUTH_FAILURE = 102;

    @GetMapping
    public BaseResponse showStatus() {
        return new BaseResponse(SUCCESS_STATUS, 1);
    }

    public static void init() {
        database.put("doctor", "1234");
    }

    @PostMapping("/login")
    public BaseResponse login( @RequestBody HospitalLogin request) {
        final BaseResponse response;

        String user = request.getUser();
        String password = request.getPassword();
        Role role = request.getRole();
            // Process the request
            // ....
            // Return success response to the client.
            response = new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS);
        return response;
    }
}