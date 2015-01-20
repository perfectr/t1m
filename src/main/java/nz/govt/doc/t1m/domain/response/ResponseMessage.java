package nz.govt.doc.t1m.domain.response;

/**
 */
public class ResponseMessage {

    private String code;
    private MessageSeverity severity;
    private String description;

    public ResponseMessage() {
    }

    public ResponseMessage(String code, MessageSeverity severity, String description) {
        this.code = code;
        this.severity = severity;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MessageSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(MessageSeverity severity) {
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
