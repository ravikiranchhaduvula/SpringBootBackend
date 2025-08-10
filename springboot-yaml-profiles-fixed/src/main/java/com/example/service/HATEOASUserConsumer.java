package com.example.service;

import com.example.entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class HATEOASUserConsumer {

    private final RestTemplate restTemplate = new RestTemplate();

    public void consumeUserLinks(Long userId) {
        String baseUrl = "http://localhost:8081/api/users/" + userId;

        try {
            // ✅ Correct deserialization of EntityModel<User>
            ResponseEntity<EntityModel<User>> response = restTemplate.exchange(
                    baseUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );

            EntityModel<User> userModel = response.getBody();

            if (userModel != null) {
                User user = userModel.getContent();
                System.out.println("👤 User: " + user);

                Link updateLink = userModel.getLink("update").orElse(null);
                Link deleteLink = userModel.getLink("delete").orElse(null);

                if (updateLink != null) {
                    System.out.println("🔗 Found update link: " + updateLink.getHref());

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    String userJson = """
                            {
                              "id": %d,
                              "name": "Updated Ravi",
                              "email": "updated@email.com"
                            }
                            """.formatted(userId);

                    HttpEntity<String> entity = new HttpEntity<>(userJson, headers);
                    ResponseEntity<String> updateResponse = restTemplate.exchange(
                            updateLink.getHref(), HttpMethod.PUT, entity, String.class);

                    System.out.println("✅ Update response: " + updateResponse.getBody());
                }

                if (deleteLink != null) {
                    System.out.println("🔗 Found delete link: " + deleteLink.getHref());

                    ResponseEntity<String> deleteResponse = restTemplate.exchange(
                            deleteLink.getHref(), HttpMethod.DELETE, null, String.class);

                    System.out.println("🗑️ Delete response: " + deleteResponse.getBody());
                }

            } else {
                System.err.println("❌ No user model returned.");
            }

        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}