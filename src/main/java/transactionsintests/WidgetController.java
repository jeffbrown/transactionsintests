package transactionsintests;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/widgets")
public class WidgetController {

    private final WidgetRepository widgetRepository;

    public WidgetController(WidgetRepository widgetRepository) {
        this.widgetRepository = widgetRepository;
    }

    @Post
    HttpResponse<?> save(@Body Widget widget) {
        return HttpResponse.ok(widgetRepository.save(widget));
    }
}