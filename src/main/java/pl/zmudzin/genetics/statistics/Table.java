package pl.zmudzin.genetics.statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Piotr Żmudzin
 */
public class Table {

    private List<Row> rows;

    public Table(Row... rows) {
        this(new ArrayList<>(Arrays.asList(rows)));
    }

    private Table(List<Row> rows) {
        this.rows = rows;
    }

    public List<Row> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        List<Integer> lengths = new ArrayList<>();

        rows.forEach(row -> {
            List<Object> columns = row.columns;

            for (int i = 0; i < columns.size(); i++) {
                int length = columns.get(i).toString().length();

                if (i < lengths.size()) {
                    lengths.set(i, Math.max(length, lengths.get(i)));
                } else {
                    lengths.add(length);
                }
            }
        });

        return rows.stream()
                .map(row -> {
                    String string = "";
                    List<Object> columns = row.columns;

                    for (int i = 0; i < columns.size(); i++) {
                        String column = columns.get(i).toString();
                        string += column + " ".repeat(lengths.get(i) - column.length()) + "\t\t";
                    }
                    return string;
                })
                .collect(Collectors.joining("\n"));
    }

    public static class Row {

        private List<Object> columns;

        public Row(Object... columns) {
            this(Arrays.asList(columns));
        }

        private Row(List<Object> columns) {
            this.columns = columns;
        }

        public List<Object> getColumns() {
            return Collections.unmodifiableList(columns);
        }

        @Override
        public String toString() {
            return columns.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("\t\t\t"));
        }
    }
}
