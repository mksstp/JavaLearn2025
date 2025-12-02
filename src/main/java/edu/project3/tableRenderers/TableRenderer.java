package edu.project3.tableRenderers;

import java.util.List;

public interface TableRenderer {

    String render(String header, int tableWidth, List<Object[]> table);
}
