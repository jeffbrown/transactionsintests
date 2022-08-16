package transactionsintests;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Map;

import static io.micronaut.http.HttpStatus.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WidgetTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    WidgetRepository widgetRepository;

    @Test
    @Order(1)
    public void createWidget() throws Exception {
        HttpRequest<?> request = HttpRequest.POST("/widgets", Map.of("name", "Widget Name"));
        HttpResponse<Widget> response = client.toBlocking().exchange(request, Widget.class);
        assertEquals(OK, response.getStatus());
        assertEquals("Widget Name", response.getBody().get().getName());
    }

    @Test
    @Order(2)
    public void testNoWidgetsExist() throws Exception {
        assertEquals(0, widgetRepository.count());
    }
}
