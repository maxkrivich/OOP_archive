
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MyOutputStream extends java.io.ByteArrayOutputStream {

    private final int BUFF_SIZE = 1 << 11;
    private ByteArrayOutputStream buf;

    public MyOutputStream() {
        this.buf = new ByteArrayOutputStream(BUFF_SIZE);
    }

    public MyOutputStream getOutputStream() {
        return this;
    }

    @Override
    public void write(byte[] b) throws IOException {
        buf.write(b);
    }

    public void close() throws IOException {
        buf.close();
    }

    public void flush() throws IOException {
        buf.flush();
    }
}
