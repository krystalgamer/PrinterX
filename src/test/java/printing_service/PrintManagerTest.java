package printing_service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

public class PrintManagerTest {

    Document doc1 = new Document(1, 3, 4);
    Document doc2 = new Document(2, 1, 3);
    Document doc3 = new Document(3, 2, 7);
    Document doc4 = new Document(4, 4, 2);
    int totalPagesToBePrinted = 16;
    List<Document> docs = Arrays.asList(doc1, doc2, doc3, doc4);

    Printer printer1 = new Printer(1, 3);
    Printer printer2 = new Printer(2, 1);
    Printer printer3 = new Printer(3, 2);
    Printer printer4 = new Printer(4, 1);

    int totalPrintingCapacity = 7;
    List<Printer> printers = Arrays.asList(printer1, printer2, printer3, printer4);

    public void setup() {
        doc1 = Mockito.mock(Document.class);
        doc2 = Mockito.mock(Document.class);
        doc3 = Mockito.mock(Document.class);
        doc4 = Mockito.mock(Document.class);

        when(doc1.getNumPages()).thenReturn(4);
        when(doc2.getNumPages()).thenReturn(3);
        when(doc3.getNumPages()).thenReturn(7);
        when(doc4.getNumPages()).thenReturn(2);
        docs = Arrays.asList(doc1, doc2, doc3, doc4);
    }

    @Test
    public void testSatisfiedPrintingPercentage() {

        this.setup();
        PrintManager pm = new PrintManager();
        int actual = pm.satisfiedPrintingPercentage(docs, printers);
        int expected = totalPrintingCapacity * 100 / totalPagesToBePrinted;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSatisfiedPrintingPercentage_exceedingPrintingCapacity() {

        PrintManager pm = new PrintManager();
        int actual = pm.satisfiedPrintingPercentage(
                docs,
                Stream.concat(
                        printers.stream(),
                        Stream.of(new Printer(5, 100)))
                        .collect(Collectors.toList()));

        Assertions.assertEquals(100, actual);
    }

    @Test
    public void testSatisfiedPrintingPercentage_zeroPrintingCapacity() {

        PrintManager pm = new PrintManager();
        int actual = pm.satisfiedPrintingPercentage(docs, Collections.emptyList());

        Assertions.assertEquals(0, actual);
    }

    @Test
    public void testSatisfiedPrintingPercentage_zeroDocuments() {

        PrintManager pm = new PrintManager();
        int actual = pm.satisfiedPrintingPercentage(Collections.emptyList(), printers);

        Assertions.assertEquals(0, actual);
    }

    // Uncomment the following block for exercise 4
    @Test
    public void testMissingDocuments() {

        PrintManager pm = new PrintManager();
        List<Integer> actual = pm.missingDocuments(docs, printers);
        List<Integer> expected = Arrays.asList(3, 1, 4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMissingDocuments_withPartialPrint() {

        PrintManager pm = new PrintManager();
        List<Integer> actual = pm.missingDocuments(docs, Stream.concat(
                printers.stream(),
                Stream.of(new Printer(5, 1)))
                .collect(Collectors.toList()));
        List<Integer> expected = Arrays.asList(3, 1, 4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMissingDocuments_zeroPrintingCapacity() {

        PrintManager pm = new PrintManager();
        List<Integer> actual = pm.missingDocuments(docs, Collections.emptyList());
        List<Integer> expected = Arrays.asList(2, 3, 1, 4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMissingDocuments_zeroDocuments() {

        PrintManager pm = new PrintManager();
        List<Integer> actual = pm.missingDocuments(Collections.emptyList(), printers);

        Assertions.assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void testUnusedPrinters() {

        Printer unusedPrinter = new Printer(6, 100);
        PrintManager pm = new PrintManager();
        List<Printer> actual = pm.unusedPrinters(docs, Stream.concat(
                printers.stream(),
                Stream.of(new Printer(5, 20), unusedPrinter))
                .collect(Collectors.toList()));
        List<Printer> expected = Arrays.asList(unusedPrinter);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void sortDocumentsByPriority_with2SamePriority() {

        doc4.setPriority(1);
        PrintManager pm = new PrintManager();
        pm.sortDocumentsByPriority(docs);

        List<Document> expected = Arrays.asList(doc2, doc4, doc3, doc1);

        Assertions.assertEquals(expected, docs);
    }

    @Test
    public void testUnusedPrinters_withSamePriority() {

        doc4 = new Document(4, 4, 10);
        PrintManager pm = new PrintManager();
        List<Printer> actual = pm.unusedPrinters(docs, printers);

        Assertions.assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void testTotalPrintingCapacity() {
        PrintManager pm = new PrintManager();
        int actual = pm.calculateTotalPrintingCapacity(printers);

        Assertions.assertEquals(totalPrintingCapacity, actual);
    }

    @Test
    public void testCanBePrinted(){
        PrintManager pm = new PrintManager();
        boolean res = pm.canPrint(new Document(1, 1, 99), new Printer(1, 100));
        Assertions.assertTrue(res);
    }

    @Test
    public void testNextToBePrinted(){

        Document chosen = new Document(14, 1, 200);
        Document notChosen = new Document(40, 2, 5);

        PrintManager pm = new PrintManager();
        Document doc = pm.nextToBePrinted(Arrays.asList(chosen, notChosen));
        Assertions.assertEquals(chosen, doc);
    }
}
