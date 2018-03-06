package com.base.data.models;

/**
 * User object: contains username, user password, admin status
 */
public class User {
    private String name;
    private String password;
    private boolean isAdmin;

    /**
     * Creates a user with the specified values
     *
     * @param name String representing username
     * @param password String representing password
     * @param isAdmin boolean to determine admin status
     */
    public User (
            String name,
            String password,
            boolean isAdmin)
    {
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     * Returns the object's username
     *
     * @return the username
     */
    public String getName()
    {
        return name;
    }

    /**
     * Re-assigns the member variable username
     *
     * @param name String representing username
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the object's password
     *
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Re-assigns the member variable password
     *
     * @param password String representing user's password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Returns user admin status
     *
     * @return isAdmin boolean
     */
    public boolean getAdmin()
    {
        return isAdmin;
    }

    /**
     * Sets the user admin status
     *
     * @param isAdmin boolean representing if the user is an admin
     */
    public void setAdmin(boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    public boolean equals(User rhs)
    {
    	return(name.equals(rhs.name));
    }
}
