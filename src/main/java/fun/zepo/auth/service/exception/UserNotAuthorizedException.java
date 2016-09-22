package fun.zepo.auth.service.exception;


/**
 * @author Koustav <br>
 *         Year: 2016
 *         <hr>
 */
public class UserNotAuthorizedException extends Exception
{
   

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = -2314603902915412412L;

    /**
     * 
     */
    public UserNotAuthorizedException()
    {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public UserNotAuthorizedException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * @param message
     */
    public UserNotAuthorizedException(String message)
    {
        super(message);
    }
}
