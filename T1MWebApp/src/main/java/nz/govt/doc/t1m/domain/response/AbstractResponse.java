package nz.govt.doc.t1m.domain.response;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class AbstractResponse<T> {

    private List<ResponseMessage> responseMessages = new ArrayList<>();

    public List<ResponseMessage> getResponseMessages() {
        return responseMessages;
    }

    public void setResponseMessages(List<ResponseMessage> responseMessages) {
        this.responseMessages = responseMessages;
    }

    public void addMessage(ResponseMessage message) {
        getResponseMessages().add(message);
    }

    public void addError(String code, String message) {
        addMessage(new ResponseMessage(code, MessageSeverity.ERROR, message));
    }

    public void addWarning(String code, String message) {
        addMessage(new ResponseMessage(code, MessageSeverity.WARNING, message));
    }

    public void addInfo(String code, String message) {
        addMessage(new ResponseMessage(code, MessageSeverity.INFO, message));
    }
}
