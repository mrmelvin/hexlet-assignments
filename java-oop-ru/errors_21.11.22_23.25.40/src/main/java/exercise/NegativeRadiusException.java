package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {

    private String errorMessage;

    NegativeRadiusException(String code) {
        errorMessage = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
// END
