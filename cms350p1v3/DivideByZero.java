package cms350p1v3;
public class DivideByZero extends Exception {
	/*
	 * check should be made for division by zero. Should the expression contain
	 * division by zero, a checked exception DivideByZero should be thrown by the
	 * method
	 */
    public DivideByZero(String msg) {
        super(msg);
    }
}
