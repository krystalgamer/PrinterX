package printing_service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class to manage the printing service.
 */
public class PrintManager {

    /**
     * Sorts the documents waiting to be printed by their priority.
     */
    void sortDocumentsByPriority (List<Document> documents) {
        Collections.sort(documents);
    }

    /**
     * Calculates the total printing capacity, that is, the number of pages that can be printed attending to the
     * printers available and their capacity.
     *
     * @return The total number of pages that can be printed.
     */
    int calculateTotalPrintingCapacity (List<Printer> printers) {

        int total = 0;

        for (Printer p: printers
        ) {
            total += p.getCapacity();
        }

        return total;
    }

    /**
     * Calculates the percentage of documents that will be printed according to the printers availability.
     *
     * @return The percentage of documents that will be printed.
     */

    int satisfiedPrintingPercentage(List<Document> documents, List<Printer> printers) {

        int numPagesToPrint = documents.stream().mapToInt(doc -> doc.getNumPages()).sum();
        int capacity = printers.stream().mapToInt(printer -> printer.getCapacity()).sum();

        if(numPagesToPrint == 0) return 0;

        if(capacity >= numPagesToPrint) return 100;

        return capacity * 100 / numPagesToPrint;
    }

    /**
     * Obtain a list with the ids of the documents that the printing service will not be able to print, according
     * to the printers available.
     *
     * @return A list containing the ids of the documents that will not be printed by the printing service.
     */
    List<Integer> missingDocuments(List<Document> documents, List<Printer> printers) {

        List<Integer> result = new ArrayList<>();
        int capacity = printers.stream().mapToInt(printer -> printer.getCapacity()).sum();

        this.sortDocumentsByPriority(documents);

        for(Document doc : documents) {
            capacity -= doc.getNumPages();

            if(capacity < 0) result.add(doc.getId());
        }

        return result;
    }

    /**
     * Obtain a list with the ids of the printers that will not be used to satisfy the printing service.
     *
     * @return A list containing the ids of the printers that will not be used by the printing service.
     */
    List<Printer> unusedPrinters(List<Document> documents, List<Printer> printers) {

        int numPagesToPrint = documents.stream().mapToInt(doc -> doc.getNumPages()).sum();

        this.sortDocumentsByPriority(documents);

        for(int i = 0; i < printers.size(); i++) {
            Printer printer = printers.get(i);
            numPagesToPrint -= printer.getCapacity();

            if(numPagesToPrint < 0) return printers.subList(i+1, printers.size());
        }

        return Collections.emptyList();
    }
}
