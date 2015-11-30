package snakesandladders;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReadExceptionTest {
    @Test
    public void exceptionHasOriginalMessage() {
        Throwable original = new IOException("original msg");

        ReadException readException = new ReadException("hello", original);

        assertThat(readException.getMessage(), is("hello"));

    }

    @Test
    public void exceptionHasOriginalCause() {
        Throwable original = new IOException("original msg");

        ReadException readException = new ReadException("hello", original);

        assertThat(readException.getCause(), is(original));
    }

}
