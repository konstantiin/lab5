package StoredClasses.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Boundaries {
    String lowerBound();
    String upperBound();
}

