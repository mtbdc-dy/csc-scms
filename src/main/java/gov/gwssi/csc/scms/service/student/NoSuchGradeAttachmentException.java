package gov.gwssi.csc.scms.service.student;

public class NoSuchGradeAttachmentException extends Exception {
    public NoSuchGradeAttachmentException() {
    }

    public NoSuchGradeAttachmentException(String message) {
        super(message);
    }

    public NoSuchGradeAttachmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchGradeAttachmentException(Throwable cause) {
        super(cause);
    }
}
