package kitchenpos.menus.tobe.infra;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
public class RestTemplateProductClient implements ProductClient {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String CHECK_PRODUCTS_EXISTENCE_URL = "/api/tobe/products/ids";
    private final RestTemplate restTemplate;

    public RestTemplateProductClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProductResponses listByIds(List<UUID> productIds) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            List<ProductResponse> body = restTemplate.exchange(
                    BASE_URL + CHECK_PRODUCTS_EXISTENCE_URL,
                    HttpMethod.POST,
                    new HttpEntity<>(productIds, headers),
                    new ParameterizedTypeReference<List<ProductResponse>>() {
                    }
            ).getBody();
            return new ProductResponses(body);

        } catch (RestClientException e) {
            throw new IllegalArgumentException();
        }
    }
}
