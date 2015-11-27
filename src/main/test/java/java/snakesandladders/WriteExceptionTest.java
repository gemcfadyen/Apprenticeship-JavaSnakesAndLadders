package snakesandladders;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WriteExceptionTest {

    @Test
    public void exceptionHasOriginalMessage() {
        Throwable original = new IOException("original msg");

        WriteException writeException = new WriteException("hello", original);

        assertThat(writeException.getMessage(), is("hello"));

    }

    @Test
    public void exceptionHasOriginalCause() {
        Throwable original = new IOException("original msg");

        WriteException writeException = new WriteException("hello", original);

        assertThat(writeException.getCause(), is(original));
    }
}
