package edu.project3.tableRenderers;

import java.util.List;

public class MarkdownTableRenderer implements TableRenderer {

    private static String getDelimiterLine(int[] columnWidth) {
        StringBuilder stringBuilder = new StringBuilder("|");

        for (var column : columnWidth) {
            stringBuilder.append(":");
            stringBuilder.append("-".repeat(column));
            stringBuilder.append(":|");
        }

        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private static String getTableLine(Object[] row, int[] columnWidth) {
        StringBuilder stringBuilder = new StringBuilder("|");

        for (int i = 0; i < row.length; i++) {
            String value = row[i].toString();
            int after = columnWidth[i] - value.length();

            stringBuilder.append(" ");
            stringBuilder.append(value);
            stringBuilder.append(" ".repeat(after + 1));
            stringBuilder.append("|");
        }

        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public String render(String header, int tableWidth, List<Object[]> table) {
        StringBuilder stringBuilder = new StringBuilder("#### " + header + "\n\n");

        int[] columnWidth = new int[tableWidth];
        for (var row : table) {
            for (int i = 0; i < tableWidth; i++) {
                columnWidth[i] = Math.max(columnWidth[i], row[i].toString().length());
            }
        }

        stringBuilder.append(getTableLine(table.get(0), columnWidth));
        stringBuilder.append(getDelimiterLine(columnWidth));
        for (int row = 1; row < table.size(); row++) {
            stringBuilder.append(getTableLine(table.get(row), columnWidth));
        }

        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
