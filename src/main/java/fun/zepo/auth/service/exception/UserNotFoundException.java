package fun.zepo.auth.service.exception;


/**
 * @author Koustav <br>
 *         Year: 2016
 *         <hr>
 */
public class UserNotFoundException extends Exception
{

    /**
     * Default serialVersionUID
     */
    private static final long serialVersionUID = 692955019752431945L;

    /**
     * 
     */
    public UserNotFoundException()
    {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public UserNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * @param message
     */
    public UserNotFoundException(String message)
    {
        super(message);
    }

}
