package snakesandladders;

import java.io.IOException;
import java.io.Writer;

public class WriterExceptionStub extends Writer {
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        throw new IOException("throws exception for test");
    }

    @Override
    public void flush() throws IOException {

    }

    @Override
    public void close() throws IOException {

    }
}
