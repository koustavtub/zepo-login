/**
 * 
 */
package fun.zepo.auth.web.dto;

/**
 * @author Koustav <br>
 *         Year: 2016
 *         <hr>
 */
public class Links
{
	private String	rel;
	private String	href;

	/**
	 * @param rel
	 * @param loginLink
	 */
	public Links(String rel, String loginLink)
	{
		super();
		this.rel = rel;
		this.href = loginLink;
	}

    /**
     * @return rel
     *         String relation
     */
    public String getRel()
    {
        return rel;
    }

    /**
     * @param rel String
     */
    public void setRel(String rel)
    {
        this.rel = rel;
    }

    /**
     * @return href
     *         String URL
     */
    public String getHref()
    {
        return href;
    }

    /**
     * @param href
     *            String URL
     */
    public void setHref(String href)
    {
        this.href = href;
    }




}
