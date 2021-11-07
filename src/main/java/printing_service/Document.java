package printing_service;

/**
 * Class to represent a document to print in the printing service.
 */
public class Document implements Comparable<Document>{

    private int id;
    private int priority;
    private int numPages;

    /**
     * Class constructor specifying the document id, priority in the list and number of pages.
     */
    public Document(int id, int priority, int pages) {
        this.id = id;
        this.priority = priority;
        this.numPages = pages;
    }

    @Override public int compareTo(Document doc) {

        if (this.priority == doc.getPriority()) {
            return Integer.compare(this.id, doc.getId());
        }

        return Integer.compare(this.priority, doc.getPriority());
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public int getNumPages() {
        return numPages;
    }
}
