package ex.ex4;

class BadFoodException extends Exception {
    public BadFoodException() {}

    public BadFoodException(String msg) {
        super(msg);
    }
}
