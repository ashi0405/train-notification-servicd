package com.example.notify.pnr;

import com.example.notify.response.PnrResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PNRStatusClient {

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    public String checkPnrStatus(String pnr) {
        try {
            String url = "https://irctc-indian-railway-pnr-status.p.rapidapi.com/getPNRStatus/" + pnr;

            HttpHeaders headers = new HttpHeaders();
            headers.set("x-rapidapi-host", "irctc-indian-railway-pnr-status.p.rapidapi.com");
            headers.set("x-rapidapi-key", rapidApiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<PnrResponse> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, PnrResponse.class
            );

            PnrResponse body = response.getBody();
            if (body != null
                    && body.getData() != null
                    && body.getData().getPassengerList() != null
                    && !body.getData().getPassengerList().isEmpty()
                    && body.getData().getPassengerList().get(0).getCurrentStatus() != null) {

                return body.getData().getPassengerList().get(0).getCurrentStatus();
            } else {
                return "WAITLISTED"; // Default fallback
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch PNR status: " + e.getMessage());
        }
        return "WAITLISTED";
    }


}
