package com.cryyptotalk.generated;

public class Bittrex
{
    private String message;

    private Result[] result;

    private String success;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public Result[] getResult ()
    {
        return result;
    }

    public void setResult (Result[] result)
    {
        this.result = result;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "Bittrex [message = "+message+", result = "+result+", success = "+success+"]";
    }
}
        