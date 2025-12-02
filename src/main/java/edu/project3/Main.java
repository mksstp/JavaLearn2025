package edu.project3;

import edu.project3.logReaders.InputStreamLogReader;
import edu.project3.tableRenderers.AdocTableRenderer;
import edu.project3.tableRenderers.MarkdownTableRenderer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("MultipleStringLiterals")
public class Main {

    protected Main() {
    }

    // Must start and end with an empty string
    private static String[] relativePath = new String[] {"", "src", "main", "resources", "project3", ""};

    private static List<String> getFilesList(String glob) throws IOException {
        List<String> files = new ArrayList<>();
        String resultDirectory;
        String pathMatcherString;
        String absolutePath = new File("").getAbsolutePath();

        String os = System.getProperty("os.name");
        if (os.contains("win") || os.contains("Win")) {
            resultDirectory = absolutePath + String.join("\\", relativePath);
            pathMatcherString = "glob:" + resultDirectory + glob;

            pathMatcherString = pathMatcherString.replace("\\", "\\\\");
            resultDirectory = resultDirectory.replace("\\", "\\\\");
        } else {
            resultDirectory = absolutePath + String.join("/", relativePath);
            pathMatcherString = "glob:" + resultDirectory + glob;
        }

        final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(pathMatcherString);

        Files.walkFileTree(Paths.get(resultDirectory), new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (pathMatcher.matches(path)) {
                    files.add(path.toString());
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });

        return files;
    }

    private static HashMap<String, String> parseCommandLineParams(String[] args) throws IllegalArgumentException {
        HashMap<String, String> cmdParams = new HashMap<>();
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("Wrong cmd parameters");
        }
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].length() <= 2 || args[i].charAt(0) != '-' || args[i].charAt(1) != '-') {
                throw new IllegalArgumentException("Wrong cmd parameters");
            }
            cmdParams.put(args[i].substring(2), args[i + 1]);
        }
        if (!cmdParams.containsKey("path")) {
            throw new IllegalArgumentException("Wrong cmd parameters");
        }
        return cmdParams;
    }

    private static OffsetDateTime getParamsOffsetDateTime(HashMap<String, String> cmdParams, String key) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (cmdParams.containsKey(key)) {
            return OffsetDateTime.of(
                LocalDate.parse(cmdParams.get(key), formatter),
                LocalTime.now(),
                OffsetDateTime.now().getOffset()
            );
        }

        return null;
    }

    public static void main(String[] args) {
        try {
            // Parsing cmd parameters
            HashMap<String, String> cmdParams = parseCommandLineParams(args);
            OffsetDateTime startDate = getParamsOffsetDateTime(cmdParams, "from");
            OffsetDateTime endDate = getParamsOffsetDateTime(cmdParams, "to");
            String path = cmdParams.get("path");

            LoggerStatisticsMachine lsm = new LoggerStatisticsMachine(path, startDate, endDate);
            InputStreamLogReader inputStreamLogReader = new InputStreamLogReader();

            if (path.startsWith("https://")) {
                HttpRequest request = HttpRequest.newBuilder(new URI(path)).GET().build();
                HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

                HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
                lsm.addLogs(inputStreamLogReader.read(response.body()));
                response.body().close();
            } else {
                var files = getFilesList(path);

                for (var file : files) {
                    InputStream inputStream = new FileInputStream(file);
                    lsm.addLogs(inputStreamLogReader.read(inputStream));
                    inputStream.close();
                }
            }

            if ("adoc".equals(cmdParams.get("format"))) {
                lsm.renderToFile(
                    String.join("/", relativePath).substring(1) + "result.adoc",
                    new AdocTableRenderer()
                );
            } else {
                lsm.renderToFile(
                    String.join("/", relativePath).substring(1) + "result.md",
                    new MarkdownTableRenderer()
                );
            }

        } catch (FileNotFoundException exception) {
            Logger.getLogger("logger").log(Level.INFO, "Can't find file");
        } catch (IOException e) {
            Logger.getLogger("logger").log(Level.INFO, "Error while IO work");
        } catch (IllegalArgumentException e) {
            Logger.getLogger("logger").log(Level.INFO, String.format("Can't parse: %s", e.getMessage()));
        } catch (URISyntaxException e) {
            Logger.getLogger("logger").log(Level.INFO, "Wrong http link");
        } catch (InterruptedException e) {
            Logger.getLogger("logger").log(Level.INFO, "Http connection error");
        }
    }
}
