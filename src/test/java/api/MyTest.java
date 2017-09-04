package api;


import io.dropwizard.jersey.validation.Validators;
import org.junit.Test;

import javax.validation.Validator;
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class MyTest {

    private final Validator validator = Validators.newValidator();


    @Test
    public void testMissing() {
        CreateReceiptRequest receipt = new CreateReceiptRequest();

        validator.validate(receipt);
        assertThat(validator.validate(receipt), hasSize(1));
    }
}