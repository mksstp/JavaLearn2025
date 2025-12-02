package edu.project3.logReaders;

import edu.project3.shared.LogRecord;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface LogReader {

    List<LogRecord> read(InputStream inputStream) throws IOException;
}
