package StoredClasses.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.math.BigDecimal;


@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface Boundaries {
    String lowerBound();
    String upperBound();
}

