package transactionsintests;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;

@Repository
public interface WidgetRepository extends PageableRepository<Widget, Long> {
}
