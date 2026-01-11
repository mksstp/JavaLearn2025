package edu.project3.tableRenderers;

import java.util.List;

public interface TableRendererInterface {
    String render(String header, int tableWidth, List<Object[]> table);
}
