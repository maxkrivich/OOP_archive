
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyInputStream extends InputStream {

    private ByteArrayInputStream buf;

    public MyInputStream(byte buf[]) {
        this.buf = new ByteArrayInputStream(buf);
    }

    @Override
    public int read() throws IOException {
        return buf.read();
    }

    public MyInputStream getOutputStream() {
        return this;
    }

    public int available() {
        return buf.available();
    }

    public long skip(long k) {
        return buf.skip(k);
    }

    public void close() throws IOException {
        buf.close();
    }

    public void mark(int m) {
        buf.mark(m);
    }
}
