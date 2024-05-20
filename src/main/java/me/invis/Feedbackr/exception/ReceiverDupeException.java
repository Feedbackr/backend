package me.invis.Feedbackr.exception;

import lombok.Getter;

public class ReceiverDupeException extends RuntimeException {
    public ReceiverDupeException(DupeType dupeType) {
        super("Feedback page with the same " + dupeType.getPrettyName() + " already exists with !");
    }

    @Getter
    public enum DupeType {
        EMAIL("email"),
        COMPANY_NAME("company name"),
        COMPANY_WEBSITE("company website");

        private final String prettyName;

        DupeType(String prettyName) {
            this.prettyName = prettyName;
        }
    }
}
