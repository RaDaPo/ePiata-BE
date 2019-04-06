package ro.hacktm.oradea.epiata.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.hacktm.oradea.epiata.exceptions.ExternalServicesException;
import ro.hacktm.oradea.epiata.model.external_services.DisplayLocationDto;
import ro.hacktm.oradea.epiata.model.external_services.DistanceContent;
import ro.hacktm.oradea.epiata.model.external_services.DistanceDto;
import ro.hacktm.oradea.epiata.model.external_services.GeoCodeResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ExternalServices {

    private static final String GEO_CODE_URL =
            "https://geocoder.api.here.com/6.2/geocode.json" +
                    "?app_id={api_id}" +
                    "&app_code={api_code}" +
                    "&searchtext={searchtext}";

    private static final String DISTANCE_URL =
            "https://wse.api.here.com/2/findsequence.json" +
                    "?start={start_location}" +
                    "&end=HERE-{end_location}" +
                    "&mode=fastest;car&app_id={api_id}"
                    + "&app_code={api_code}";

    private RestTemplate restTemplate = new RestTemplate();

    public DistanceDto getDistanceBetweenLocations(String startAddress, String endAddress) {
        Map<String, String> params = getDistanceBetweenAddresses(startAddress, endAddress);
        HttpEntity entity = getHttpEntity();
        ResponseEntity<DistanceContent> response = restTemplate
                .exchange(DISTANCE_URL, HttpMethod.GET, entity, DistanceContent.class, params);
        DistanceDto dto = getDistanceContent(response);
        return formatUnitTypes(dto);
    }

    DisplayLocationDto getAddressGeoCode(String address) {
        Map<String, String> params = getAddressInParams(address);
        HttpEntity entity = getHttpEntity();
        ResponseEntity<GeoCodeResponse> response = restTemplate
                .exchange(GEO_CODE_URL, HttpMethod.GET, entity, GeoCodeResponse.class, params);
        return getLocation(response);
    }

    private DistanceDto formatUnitTypes(DistanceDto dto) {
        dto.setTimeToDestination(formatTime(dto.getTime()));
        dto.setTimeToDestination(formatDistance(dto.getDistance()));
        return dto;
    }

    private String formatDistance(long distance) {
        return String.valueOf(distance * 0.001);
    }

    private String formatTime(long time) {
        return String.valueOf((time % 3600) / 60);
    }


    private DistanceDto getDistanceContent(ResponseEntity<DistanceContent> response) {
        try {
            return Objects.requireNonNull(response.getBody()).getDistanceContentList().get(0);
        } catch (Exception e) {
            throw new ExternalServicesException("Cannot return distance between two locations");
        }
    }

    private DisplayLocationDto getLocation(ResponseEntity<GeoCodeResponse> response) {
        try {
            return Objects.requireNonNull(response.getBody()).getGeoCodeResponseContent().getGeoCodeView()
                    .get(0).getResults().get(0).getLocation().getDisplayLocation();
        } catch (Exception e) {
            throw new ExternalServicesException("Cannot return coordinates");
        }
    }

    private HttpEntity getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

    private Map<String, String> getAddressInParams(String address) {
        Map<String, String> params = new HashMap<>();
        params.put("searchtext", address);
        params.put("api_id", "qBy805mD1ZqfBRcLfiKO");
        params.put("api_code", "hBvOm61wN4vBXaoG0zbE6w");
        return params;
    }

    private Map<String, String> getDistanceBetweenAddresses(String startAddress, String endAddress) {
        Map<String, String> params = new HashMap<>();
        params.put("start_location", formatAddress(startAddress));
        params.put("end_location", formatAddress(endAddress));
        params.put("api_id", "qBy805mD1ZqfBRcLfiKO");
        params.put("api_code", "hBvOm61wN4vBXaoG0zbE6w");
        return params;
    }

    private String formatAddress(String address) {
        DisplayLocationDto geoCode = getAddressGeoCode(address);
        return String.format("%s;%s,%s", address, geoCode.getLatitude(), geoCode.getLongitude());
    }
}