package one.digitalinnovation.beerstock.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Object statusObj = request.getAttribute("javax.servlet.error.status_code");
        int status = 500;
        if (statusObj != null) {
            try { status = Integer.parseInt(statusObj.toString()); } catch (NumberFormatException ignored) {}
        }
        Map<String, Object> body = new HashMap<>();
        body.put("status", status);
        body.put("message", "No mapping for this path. Try /api/v1/beers or the API endpoints.");
        return ResponseEntity.status(status).body(body);
    }
}
