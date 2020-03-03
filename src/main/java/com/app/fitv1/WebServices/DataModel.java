package com.app.fitv1.WebServices;

/**
 * created by PARAMBIR SINGH on 30/8/17.
 */

public class DataModel<G>
{
    private boolean status;
    private String message;

    private G data;

    public boolean getStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public G getData() {
        return data;
    }

    public void setData(G data) {
        this.data = data;
    }
}
