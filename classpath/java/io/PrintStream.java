package java.io;

public class PrintStream extends OutputStream {
  private static final byte[] newline
    = System.getProperty("line.separator").getBytes();

  private final OutputStream out;
  private final boolean autoFlush;

  public PrintStream(OutputStream out, boolean autoFlush) {
    this.out = out;
    this.autoFlush = autoFlush;
  }

  public PrintStream(OutputStream out) {
    this(out, false);
  }

  public synchronized void print(String s) {
    try {
      out.write(s.getBytes());
    } catch (IOException e) { }
  }

  public void print(Object o) {
    print(o.toString());
  }

  public void print(char c) {
    print(String.valueOf(c));
  }

  public synchronized void println(String s) {
    try {
      out.write(s.getBytes());    
      out.write(newline);
      if (autoFlush) flush();
    } catch (IOException e) { }
  }

  public synchronized void println() {
    try {
      out.write(newline);
      if (autoFlush) flush();
    } catch (IOException e) { }
  }

  public void println(Object o) {
    println(o.toString());
  }

  public void println(char c) {
    println(String.valueOf(c));
  }
  
  public void write(int c) throws IOException {
    out.write(c);
    if (autoFlush && c == '\n') flush();
  }

  public void write(byte[] buffer, int offset, int length) throws IOException {
    out.write(buffer, offset, length);
    if (autoFlush) flush();
  }

  public void flush() {
    try {
      out.flush();
    } catch (IOException e) { }
  }

  public void close() {
    try {
      out.close();
    } catch (IOException e) { }
  }
}