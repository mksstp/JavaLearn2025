package edu.project3;

import edu.project3.shared.LogRecord;
import edu.project3.shared.StatusCodes;
import edu.project3.tableRenderers.TableRenderer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;


@SuppressWarnings("MultipleStringLiterals")
public class LoggerStatisticsMachine {

    private final String fileName;
    private OffsetDateTime startDate;
    private final OffsetDateTime endDate;
    long queriesCount;
    long bytesCount;
    private final HashMap<Integer, Integer> statusCodes = new HashMap<>();
    private final HashMap<String, Integer> resourcesCount = new HashMap<>();
    private final HashMap<String, Integer> requestMethodsCount = new HashMap<>();
    private final HashMap<String, Integer> userAgentsCount = new HashMap<>();
    private final HashMap<LocalDate, Integer> dayMonthRequestsCount = new HashMap<>();

    public LoggerStatisticsMachine(String fileName, OffsetDateTime startDate, OffsetDateTime endDate) {
        this.fileName = fileName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.queriesCount = 0;
        this.bytesCount = 0;
    }

    public void addLogs(List<LogRecord> logRecords) throws IllegalArgumentException {
        for (var log : logRecords) {

            // Проверяем, что log был после startDate.
            if (startDate != null && log.localTime().isBefore(startDate)) {
                continue;
            }
            if (endDate != null && log.localTime().isAfter(endDate)) {
                continue;
            }

            // Calculating statistics
            statusCodes.merge(log.status(), 1, Integer::sum);
            queriesCount++;
            bytesCount += log.bytes();

            String resource = log.requestBody().split(" ")[1];
            resourcesCount.merge(resource, 1, Integer::sum);

            String requestMethod = log.requestBody().split(" ")[0];
            requestMethodsCount.merge(requestMethod, 1, Integer::sum);

            userAgentsCount.merge(log.httpsUserAgent(), 1, Integer::sum);

            LocalDate localDate = LocalDate.of(log.localTime().getYear(),
                log.localTime().getMonthValue(),
                log.localTime().getDayOfMonth()
            );
            dayMonthRequestsCount.merge(localDate, 1, Integer::sum);
        }
    }

    private String header;
    private List<Object[]> table;

    private void renderDayMonthStatistics(TableRenderer tableRenderer, FileWriter writer) throws IOException {
        header = "Даты запросов";
        table = new ArrayList<>();
        for (var entry : dayMonthRequestsCount.entrySet()) {
            table.add(new Object[] {entry.getKey(), entry.getValue()});
        }
        Stream<Object[]> tableStream = table.stream().sorted((o1, o2) -> {
            if (o1[0].equals(o2[0])) {
                return 0;
            }
            if (((LocalDate) o1[0]).isBefore((LocalDate) o2[0])) {
                return -1;
            }
            return 1;
        });

        Object[][] help = new Object[][] {new Object[] {"Дата", "Количество запросов"}};
        table = Stream.concat(Stream.of(help), tableStream).toList();

        String dayMonthRequestsStatistics = tableRenderer.render(header, 2, table);
        writer.write(dayMonthRequestsStatistics);
    }

    private void renderUserAgentsStatistics(TableRenderer tableRenderer, FileWriter writer) throws IOException {
        // Rendering user agents
        header = "Http User Agents";
        table = new ArrayList<>();
        table.add(new Object[] {"UserAgent", "Количество"});
        for (var entry : userAgentsCount.entrySet()) {
            table.add(new Object[] {entry.getKey(), entry.getValue()});
        }
        String userAgentsStatistics = tableRenderer.render(header, 2, table);
        writer.write(userAgentsStatistics);
    }

    private void renderRequestMethodsStatistics(TableRenderer tableRenderer, FileWriter writer) throws IOException {
        header = "Методы запросов";
        table = new ArrayList<>();
        table.add(new Object[] {"Метод запроса", "Количество"});

        for (var entry : requestMethodsCount.entrySet()) {
            table.add(new Object[] {entry.getKey(), entry.getValue()});
        }

        String methodsStatistics = tableRenderer.render(header, 2, table);
        writer.write(methodsStatistics);
    }

    private void renderResourcesStatistics(TableRenderer tableRenderer, Writer writer) throws IOException {
        header = "Запрашиваемые ресуры";
        table = new ArrayList<>();
        table.add(new Object[] {"Ресурс", "Количество"});
        for (var entry : resourcesCount.entrySet()) {
            table.add(new Object[] {entry.getKey(), entry.getValue()});
        }
        String resourcesStatistics = tableRenderer.render(header, 2, table);
        writer.write(resourcesStatistics);
    }

    private void renderStatusCodesStatistics(TableRenderer tableRenderer, Writer writer) throws IOException {
        header = "Коды ответа";
        table = new ArrayList<>();
        table.add(new Object[] {"Код", "Имя", "Количество"});
        for (var entry : statusCodes.entrySet()) {
            table.add(new Object[] {entry.getKey(), StatusCodes.getCode(entry.getKey()), entry.getValue()});
        }

        String statusCodesStatistics = tableRenderer.render(header, 2 + 1, table);
        writer.write(statusCodesStatistics);
    }

    private void renderCommonInfoStatistics(TableRenderer tableRenderer, Writer writer) throws IOException {
        header = "Общая информация";
        table = new ArrayList<>();
        table.add(new Object[] {"Метрика", "Значение"});
        table.add(new Object[] {"Файл(-ы)", this.fileName});
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        table.add(new Object[] {
            "Начальная дата",
            startDate == null ? "-" : startDate.format(formatter)
        });
        table.add(new Object[] {
            "Конечная дата",
            endDate == null ? "-" : endDate.format(formatter)
        });
        table.add(new Object[] {"Количество запросов", queriesCount});
        table.add(new Object[] {
            "Средний размер ответа",
            queriesCount == 0 ? "0b" : bytesCount / queriesCount + "b"
        });

        String commonStatistics = tableRenderer.render(header, 2, table);
        writer.write(commonStatistics);
    }

    public void renderToFile(String fileName, TableRenderer tableRenderer) throws IOException {
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileWriter writer = new FileWriter(file, false);

        renderCommonInfoStatistics(tableRenderer, writer);
        renderStatusCodesStatistics(tableRenderer, writer);
        renderResourcesStatistics(tableRenderer, writer);
        renderRequestMethodsStatistics(tableRenderer, writer);
        renderDayMonthStatistics(tableRenderer, writer);
        renderUserAgentsStatistics(tableRenderer, writer);

        writer.close();
    }

}
