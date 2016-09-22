package fun.zepo.auth.web.dto;

import java.util.List;

/**
 * @author Koustav <br>
 *         Year: 2016
 *         <hr>
 */
public class ResponseDTO
{
    private int         httpStatusCode;

    private STATUS      status;

    private String      message;

    private Object      payload;

    private List<Links> links;

    /**
     * @return the status
     */
    public STATUS getStatus()
    {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(STATUS status)
    {
        this.status = status;
    }

    /**
     * @return the links
     */
    public List<Links> getLinks()
    {
        return links;
    }

    /**
     * @param links
     *            the links to set
     */
    public void setLinks(List<Links> links)
    {
        this.links = links;
    }

    /**
     * @return the payload
     */
    public Object getPayload()
    {
        return payload;
    }

    /**
     * @param payload
     *            the payload to set
     */
    public void setPayload(Object payload)
    {
        this.payload = payload;
    }

    /**
     * @return the httpStatusCode
     */
    public int getHttpStatusCode()
    {
        return httpStatusCode;
    }

    /**
     * @param httpStatusCode
     *            the httpStatusCode to set
     */
    public void setHttpStatusCode(int httpStatusCode)
    {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

}
