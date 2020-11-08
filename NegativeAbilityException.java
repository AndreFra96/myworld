package myworld;

public class NegativeAbilityException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NegativeAbilityException() {
        super();
    }
    public NegativeAbilityException(String msg){
        super(msg);
    }
}
