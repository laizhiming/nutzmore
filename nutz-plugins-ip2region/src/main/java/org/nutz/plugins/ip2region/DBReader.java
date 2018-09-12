package org.nutz.plugins.ip2region;

import java.io.IOException;

public interface DBReader {
    
    byte[] full() throws IOException;
    
    void readFully(long pos, byte[] buf, int offset, int length) throws IOException ;
    
    void close() throws IOException ;
}
