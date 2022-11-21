package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {

    private String errorMessage;

    NegativeRadiusException(String code) {
        errorMessage = code;
    }
}
// END
