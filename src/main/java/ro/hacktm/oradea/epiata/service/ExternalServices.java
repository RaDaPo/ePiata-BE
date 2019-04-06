package ro.hacktm.oradea.epiata.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.hacktm.oradea.epiata.model.external_services.GeoCodeResponseDto;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExternalServices {

    private static final String GEO_CODE_URL =
            "https://geocoder.api.here.com/6.2/geocode.json" +
                    "?app_id=qBy805mD1ZqfBRcLfiKO" +
                    "&app_code=hBvOm61wN4vBXaoG0zbE6w" +
                    "&searchtext={searchtext}";

    private RestTemplate restTemplate = new RestTemplate();

    public Object getAddressGeoCode(String address) {
        Map<String, String> params = getAddressInParams(address);
        HttpEntity entity = getHttpEntity();
        ResponseEntity<GeoCodeResponseDto> response = restTemplate.exchange(GEO_CODE_URL, HttpMethod.GET, entity, GeoCodeResponseDto.class, params);
        return response.getBody();
    }

    private HttpEntity getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

    private Map<String, String> getAddressInParams(String address) {
        Map<String, String> params = new HashMap<>();
        params.put("searchtext", address);
        return params;
    }
}
